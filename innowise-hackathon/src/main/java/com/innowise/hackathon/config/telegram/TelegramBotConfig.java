package com.innowise.hackathon.config.telegram;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class TelegramBotConfig {

  @Value("${bot.name}")
  String botName;
  @Value("${bot.token}")
  String token;
  @Value("${bot.chatId}")
  String chatId;
}
