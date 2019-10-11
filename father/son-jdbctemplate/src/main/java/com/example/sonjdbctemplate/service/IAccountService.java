package com.example.sonjdbctemplate.service;

import com.example.sonjdbctemplate.entity.Account;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
public interface IAccountService {


    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();

}
