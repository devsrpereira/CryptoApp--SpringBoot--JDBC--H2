package com.srdevpereira.repository;

import com.srdevpereira.entities.Coin;
import org.springframework.jdbc.core.JdbcTemplate;

public class CoinRepository {

    private static String INSERT = "insert into coin (name, price, quantity, datetime) value (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    public CoinRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coin insert (Coin coin){
        Object[] attr = new Object[]{
                coin.getName(),
                coin.getPrice(),
                coin.getQuantity(),
                coin.getDateTime()
        };
        jdbcTemplate.update(INSERT, attr);
        return coin;
    }
}
