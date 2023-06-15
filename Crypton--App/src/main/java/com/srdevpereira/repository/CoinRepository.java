package com.srdevpereira.repository;

import com.srdevpereira.entities.Coin;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CoinRepository {

    private static String INSERT = "insert into coin (name, price, quantity, datetime) values (?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

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
