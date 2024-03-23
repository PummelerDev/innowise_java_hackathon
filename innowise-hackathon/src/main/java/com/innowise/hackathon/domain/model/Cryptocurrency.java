package com.innowise.hackathon.domain.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cryptocurrency {

  private Long id;
  private String symbol;
  private BigDecimal price;
}
