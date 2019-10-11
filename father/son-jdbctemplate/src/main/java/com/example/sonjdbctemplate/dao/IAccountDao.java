package com.example.sonjdbctemplate.dao;

import com.example.sonjdbctemplate.entity.Account;

import java.util.List;

public interface IAccountDao {

    int add(Account acccout);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
