package com.zly.sontransaction.service.impl;

import com.zly.sontransaction.service.AccountService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void out(String outer, Integer money) {
        String sql = "update account set money = money - ? where username = ?";
        jdbcTemplate.update(sql, money , outer);
    }

    @Override
    public void in(String inner, Integer money) {
        String sql = "update account set money = money + ? where username = ?";
        jdbcTemplate.update(sql, money , inner);
    }
}
