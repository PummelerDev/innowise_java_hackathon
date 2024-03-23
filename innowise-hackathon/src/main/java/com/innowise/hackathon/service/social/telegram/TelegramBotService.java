package com.innowise.hackathon.service.social.telegram;

import static com.innowise.hackathon.service.social.telegram.BotCommands.HELP_TEXT;
import static com.innowise.hackathon.service.social.telegram.BotCommands.LIST_OF_COMMANDS;

import com.innowise.hackathon.config.telegram.TelegramBotConfig;
import com.innowise.hackathon.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBotService extends TelegramLongPollingBot {

  private final TelegramBotConfig config;
  private final UserRepository userRepository;

  public TelegramBotService(TelegramBotConfig config, UserRepository userRepository) {
    this.config = config;
    this.userRepository = userRepository;
    try {
      this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public String getBotUsername() {
    return config.getBotName();
  }

  @Override
  public String getBotToken() {
    return config.getToken();
  }


  @Override
  public void onUpdateReceived(Update update) {
    long chatId = 0;
    long userId = 0;
    String userName = null;
    String receivedMessage;

    if (update.hasMessage()) {
      chatId = update.getMessage().getChatId();
      userId = update.getMessage().getFrom().getId();
      userName = update.getMessage().getFrom().getFirstName();

      if (update.getMessage().hasText()) {
        receivedMessage = update.getMessage().getText();
        botAnswerUtils(receivedMessage, chatId, userName);
      }

    } else if (update.hasCallbackQuery()) {
      chatId = update.getCallbackQuery().getMessage().getChatId();
      userId = update.getCallbackQuery().getFrom().getId();
      userName = update.getCallbackQuery().getFrom().getFirstName();
      receivedMessage = update.getCallbackQuery().getData();

      botAnswerUtils(receivedMessage, chatId, userName);
    }
  }

  private void startBot(long chatId, String userName) {
    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText("Hello, " + userName + "! I'm a Telegram bot.");

    try {
      execute(message);
      log.info("Reply sent");
    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
  }

  private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
    switch (receivedMessage) {
      case "/start":
        startBot(chatId, userName);
        break;
      case "/help":
        sendHelpText(chatId, HELP_TEXT);
        break;
      default:
        break;
    }
  }

  private void sendHelpText(long chatId, String textToSend) {
    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText(textToSend);

    try {
      execute(message);
      log.info("Reply sent");
    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
  }
}
