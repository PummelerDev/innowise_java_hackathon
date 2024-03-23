package com.innowise.hackathon.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SchedulerService {

  private static final long updatePeriod = 20000L;  //per 20 sec

  private final CryptocurrencyService cryptocurrencyService;

  @Scheduled(fixedDelay = updatePeriod)
  public void updateCryptocurrenciesPrises() {
    cryptocurrencyService.updateAll();
  }
}
