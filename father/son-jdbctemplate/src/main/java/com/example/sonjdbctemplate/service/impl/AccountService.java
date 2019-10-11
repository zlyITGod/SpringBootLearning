package com.example.sonjdbctemplate.service.impl;


import com.example.sonjdbctemplate.dao.IAccountDao;
import com.example.sonjdbctemplate.entity.Account;
import com.example.sonjdbctemplate.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountDao accountDAO;
    @Override
    public int add(Account account) {
        return accountDAO.add(account);
    }

    @Override
    public int update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return accountDAO.findAccountList();
    }
}
