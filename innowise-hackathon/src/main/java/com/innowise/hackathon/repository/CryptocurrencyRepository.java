package com.innowise.hackathon.repository;

import com.innowise.hackathon.domain.model.Cryptocurrency;
import com.innowise.hackathon.domain.model.response.CryptocurrencyResponse;
import com.innowise.hackathon.mappers.CryptocurrencyMapper;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CryptocurrencyRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public void saveAll(List<CryptocurrencyResponse> cryptocurrencies) {
    Map<String, Object> params = new HashMap<>();
    for (CryptocurrencyResponse cr : cryptocurrencies) {
      params.put("symbol", cr.getSymbol());
      params.put("price", new BigDecimal(cr.getPrice()));
      jdbcTemplate
          .update(
              "insert into cryptocurrencies(symbol, price) values(:symbol, :price)",
              params);
      params.clear();
    }
  }

  public List<Cryptocurrency> getAll() {
    return jdbcTemplate.query("select * from cryptocurrencies", new CryptocurrencyMapper());
  }

  public void updateAll(List<CryptocurrencyResponse> cryptocurrencies) {
    Map<String, Object> params = new HashMap<>();
    List<Cryptocurrency> cryptocurrencyForUpdate = getAll();
    for (Cryptocurrency c : cryptocurrencyForUpdate) {
      for (CryptocurrencyResponse cr : cryptocurrencies) {
        if (c.getSymbol().equals(cr.getSymbol())) {
          params.put("id", c.getId());
          params.put("symbol", cr.getSymbol());
          params.put("price", new BigDecimal(cr.getPrice()));
          jdbcTemplate
              .update(
                  "update cryptocurrencies set symbol = :symbol, price = :price where id = :id",
                  params);
          params.clear();
        }
      }
    }
  }
}
