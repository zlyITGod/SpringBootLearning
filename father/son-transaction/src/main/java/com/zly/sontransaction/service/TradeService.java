package com.zly.sontransaction.service;

public interface TradeService {

    /**
     * 转账交易：没有事务管理
     */
    void trade1(String outer ,String inner ,Integer money);
    /**
     * 转账交易：手动管理事务
     */
    void trade2(String outer ,String inner ,Integer money);
    /**
     * 转账交易：注解管理事务
     */
    void trade3(String outer ,String inner ,Integer money);

}
