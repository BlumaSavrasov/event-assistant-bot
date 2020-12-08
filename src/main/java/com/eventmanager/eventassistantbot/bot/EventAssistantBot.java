package com.eventmanager.eventassistantbot.bot;

import com.eventmanager.eventassistantbot.bot.bot_utils.UserHandler;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;


@Component
public class EventAssistantBot extends TelegramLongPollingBot {
    private final String BOT_USER_NAME = "EventAssistantBot";
    private final String TOKEN = "1400174521:AAHbEIdyYn1VMc4MJiZpnDKd_c87XecrKrI";
    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        User user = update.getMessage().getFrom();
        String text=null ;
        if(UserHandler.newUser(user.getId())==true)
            text=welcomeMessage(user.getFirstName());
        message.setText(text);
        execute(message);
    }

    private String welcomeMessage(String firstName) {
        return "Hi "+ firstName+"\nwelcome to the group\n";
    }
}
