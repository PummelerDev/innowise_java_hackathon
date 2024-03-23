package com.innowise.hackathon.repository;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public void update(long id) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    jdbcTemplate
        .update(
            "update users u set u.msg_numb = u.msg_numb + 1 where u.id is not null and u.id = :id",
            params);
    params.clear();
  }
}
