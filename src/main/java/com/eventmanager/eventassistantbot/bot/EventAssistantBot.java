package com.eventmanager.eventassistantbot.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatDescription;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatTitle;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
public class EventAssistantBot extends TelegramWebhookBot {
    @Value("$telegram.BOT_USER_NAME")
    private String BOT_USER_NAME;
    @Value("$telegram.BOT_TOKEN")
    private  String BOT_TOKEN ;
    @Value("$telegram.external-url")
    private String BOT_PATH;

    @Autowired
    private TelegramFacade telegramFacade;




    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        System.out.println(update);
        return telegramFacade.updateHandler(update);
    }


    @Override
    public String getBotPath() {
        return BOT_PATH;
    }
}















