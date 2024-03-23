package com.innowise.hackathon.service;

import com.innowise.hackathon.client.MexcApi;
import com.innowise.hackathon.domain.model.Cryptocurrency;
import com.innowise.hackathon.domain.model.response.CryptocurrencyResponse;
import com.innowise.hackathon.repository.CryptocurrencyRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CryptocurrencyService {

  private final MexcApi mexcApi;
  private final CryptocurrencyRepository repository;

  public List<Cryptocurrency> getAll() {
    return repository.getAll();
  }

  public void saveAll() {
    List<CryptocurrencyResponse> cryptocurrencies = mexcApi.getPrices();
    repository.saveAll(cryptocurrencies);
  }

  public void updateAll() {
    List<CryptocurrencyResponse> cryptocurrencies = mexcApi.getPrices();
    repository.updateAll(cryptocurrencies);
  }
}
