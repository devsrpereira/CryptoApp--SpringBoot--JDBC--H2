package com.srdevpereira.repository;

import com.srdevpereira.DTO.CoinDTO;
import com.srdevpereira.entities.Coin;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoinRepository {

    private static String INSERT = "insert into coin (name, price, quantity, datetime) values (?, ?, ?, ?)";
    private static String SELECT_ALL = "select name, sum(quantity) as quantity from coin group by name";
    private static String SELECT_BY_NAME = "select * from coin where name = ?";

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

    public List<CoinDTO> getAll(){
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CoinDTO>() {
            @Override
            public CoinDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CoinDTO coinDTO = new CoinDTO();
                coinDTO.setName(rs.getString("name"));
                coinDTO.setQuantity(rs.getBigDecimal("quantity"));
                return coinDTO;
            }
        });
    }

    public List<Coin> getByName(String name){
        Object[] attr = new Object[]{name};
        return jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coin coin = new Coin();
                coin.setId(rs.getInt("id"));
                coin.setName(rs.getString("name"));
                coin.setPrice(rs.getBigDecimal("price"));
                coin.setQuantity(rs.getBigDecimal("quantity"));
                coin.setDateTime(rs.getTimestamp("datetime"));

                return coin;
            }
        }, attr);
    }
}
