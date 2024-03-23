package com.innowise.hackathon.client;

import com.innowise.hackathon.domain.model.response.CryptocurrencyResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "mexc.com", url = "https://api.mexc.com/api/v3/ticker/price")
public interface MexcApi {

  @GetMapping
  List<CryptocurrencyResponse> getPrices();

}
