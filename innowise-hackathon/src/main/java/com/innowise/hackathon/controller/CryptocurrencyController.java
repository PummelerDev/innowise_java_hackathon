package com.innowise.hackathon.controller;

import com.innowise.hackathon.domain.model.Cryptocurrency;
import com.innowise.hackathon.service.CryptocurrencyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/cryptocurrencies")
public class CryptocurrencyController {


  private final CryptocurrencyService service;

  @GetMapping

  public List<Cryptocurrency> getCryptocurrencies() {
    return service.getAll();
  }

  @PostMapping
  public void saveAll() {
    service.saveAll();
  }

  @PutMapping
  public void updateAll() {
    service.updateAll();
  }
}
