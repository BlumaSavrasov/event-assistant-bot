package com.eventmanager.eventassistantbot.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class EventAssistantBot extends TelegramWebhookBot {
    private final String BOT_USER_NAME = "@EventAssistantBot";
    private final String BOT_TOKEN = "1400174521:AAHbEIdyYn1VMc4MJiZpnDKd_c87XecrKrI";
    private final String BOT_PATH = "https://085e9cfb2eee.ap.ngrok.io";

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

//    @SneakyThrows
//    @Override
//    public void onUpdateReceived(Update update) {
//        SendMessage message=new SendMessage();
//        message=telegramFacade.updateHandler(update);
//        if(message.getChatId()!=null&& message.getText()!=null) execute(message);
//
//    }

    @SneakyThrows
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        SendMessage message=new SendMessage();
        message=telegramFacade.updateHandler(update);
        if(message.getChatId()!=null&& message.getText()!=null) execute(message);
        return null;
    }

    @Override
    public String getBotPath() {
        return BOT_PATH;
    }
}















