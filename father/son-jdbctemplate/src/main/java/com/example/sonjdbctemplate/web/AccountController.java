package com.example.sonjdbctemplate.web;


import com.example.sonjdbctemplate.entity.Account;
import com.example.sonjdbctemplate.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping(value = "/list")
    public List<Account> getAccounts(){
        return accountService.findAccountList();
    }

    @GetMapping(value = "/{id}")
    public  Account getAccountById(@PathVariable("id") int id){
        return accountService.findAccountById(id);
    }

    /**
     *  @RequestParam 和 @PathVariable 注解是用于从request中接收请求的，两个都可以接收参数
     *  关键点不同的是 @RequestParam 是从request里面拿取值
     *  而 @PathVariable 是从一个URI模板里面来填充,能够识别URL里面的一个模板
     */

    @PutMapping(value = "/{id}")
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name,
                                 @RequestParam(value = "money" ,required = true)double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t=accountService.update(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }
    }

    @PostMapping(value = "")
    public  String postAccount( @RequestParam(value = "name")String name,
                                @RequestParam(value = "money" )double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        int t= accountService.add(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }

    }


}
