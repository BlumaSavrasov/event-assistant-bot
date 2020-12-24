package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.bot_utils.UsersHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class UserLeftGroupUpdateHandler implements GroupUpdateHandler {
    @Autowired
    private UsersHandler usersHandler;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        User user = update.getMessage().getFrom();
        if(update.getMessage().getLeftChatMember()!=null)
            usersHandler.removeUser(user, update.getMessage().getChatId());
    }
}
