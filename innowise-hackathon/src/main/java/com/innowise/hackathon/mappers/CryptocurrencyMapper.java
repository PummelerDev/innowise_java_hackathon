package com.innowise.hackathon.mappers;

import com.innowise.hackathon.domain.model.Cryptocurrency;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CryptocurrencyMapper implements RowMapper<Cryptocurrency> {

  @Override
  public Cryptocurrency mapRow(ResultSet rs, int rowNum) throws SQLException {
    final Long id = rs.getLong("id");
    final String symbol = rs.getString("symbol");
    final BigDecimal price = rs.getBigDecimal("price");
    return new Cryptocurrency(id, symbol, price);
  }
}
