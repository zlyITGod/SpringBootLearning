package com.zly.sontransaction.service.impl;

import com.zly.sontransaction.service.AccountService;
import com.zly.sontransaction.service.TradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

@Service
public class TradeServiceImpl implements TradeService {

    @Resource
    private AccountService accountService;

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 转账交易：没有事务管理 trade1报错且没有回滚  trade4不报错且没有回滚  钱都扣了
     */
    @Override
    public void trade1(String outer, String inner, Integer money) {
        accountService.out(outer, money);
        //抛出异常
        int i= 1/0;
        accountService.in(inner, money);
    }//会报错 java.lang.ArithmeticException: / by zero

    /**
     * 转账交易：手动管理事务
     */
    @Override
    public void trade2(String outer, String inner, Integer money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                accountService.out(outer, money);
                //抛出异常
                int i= 1/0;
                accountService.in(inner, money);
            }
        });
    }

    /**
     *  转账交易：注解管理事务
     *  事务的传播行为
     *  PROPAGATION_REQUIRED , required , 必须  【默认值】
     *  支持当前事务，A如果有事务，B将使用该事务。如果A没有事务，B将创建一个新的事务。
     *
     *  REQUIRES_NEW：创建新的transaction并执行；如果当前已有transaction，则将当前transaction挂起；
     *
     *  @Transactional注意:
     *  1.不要在接口上声明@Transactional ，而要在具体类的方法上使用 @Transactional 注解，否则注解可能无效。
     *  2.不要图省事，将@Transactional放置在类级的声明中，放在类声明，会使得所有方法都有事务。故@Transactional应该放在方法级别，不需要使用事务的方法，就不要放置事务，比如查询方法。否则对性能是有影响的。
     *  3.使用了@Transactional的方法，对同一个类里面的方法调用， @Transactional无效。比如有一个类Test，它的一个方法A，A再调用Test本类的方法B（不管B是否public还是private），但A没有声明注解事务，而B有。则外部调用A之后，B的事务是不会起作用的。
     *  4.使用了@Transactional的方法，只能是public，@Transactional注解的方法都是被外部其他类调用才有效，故只能是public。
     */

    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED)
    @Override
    public void trade3(String outer, String inner, Integer money) {
        accountService.out(outer, money);
        // 抛出异常
        int i = 1/0;
        accountService.in(inner, money);
    }


}
/**  论REQUIRED 与 REQUIRED_NEW的区别
 *场景一:
 *ServiceA.java:
 * public class ServiceA {
 *     @Transactional
 *     public void callB() {
 *         serviceB.doSomething();
 *     }
 * }
 * ServiceB.java
 * public class ServiceB {
 *     @Transactional
 *     public void doSomething() {
 *         throw new RuntimeException("B throw exception");
 *     }
 * }
 * 这种情况下，我们只需要在调用ServiceA.callB时捕获ServiceB中抛出的运行时异常，则transaction就会正常的rollback
 *
 *
 *
 *场景二
 * public class ServiceA {
 *     @Transactional
 *     public void callB() {
 *         try {
 *             serviceB.doSomething();
 *         } catch (RuntimeException e) {
 *             System.err.println(e.getMessage());
 *         }
 *     }
 * }
 * ServiceB.java
 * public class ServiceB {
 *     @Transactional
 *     public void doSomething() {
 *         throw new RuntimeException("B throw exception");
 *     }
 * }
 *这个时候，我们再调用ServiceA的callB。程序会抛出org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only这样一个异常信息。原因是什么呢？
 * 因为在ServiceA和ServiceB中的@Transactional propagation都采用的默认值：REQUREID。根据我们前面讲过的REQUIRED特性，当ServiceA调用ServiceB的时候，他们是处于同一个transaction中
 *当ServiceB中抛出了一个异常以后，ServiceB会把当前的transaction标记为需要rollback。但是ServiceA中捕获了这个异常，并进行了处理，认为当前transaction应该正常commit。此时就出现了前后不一致，也就是因为这样，抛出了前面的UnexpectedRollbackException。
 *
 *
 *
 * public class ServiceA {
 *     @Transactional
 *     public void callB() {
 *         try {
 *             serviceB.doSomething();
 *         } catch (RuntimeException e) {
 *             System.err.println(e.getMessage());
 *         }
 *     }
 * }
 *public class ServiceB {
 *     @Transactional(propagation = Propagation.REQUIRES_NEW)
 *     public void doSomething() {
 *         throw new RuntimeException("B throw exception");
 *     }
 * }
 * 此时，程序可以正常的退出了，也没有抛出UnexpectedRollbackException。原因是因为当ServiceA调用ServiceB时，serviceB的doSomething是在一个新的transaction中执行的
 * 所以，当doSomething抛出异常以后，仅仅是把新创建的transaction rollback了，而不会影响到ServiceA的transaction。ServiceA就可以正常的进行commit。
 *
 *
*/