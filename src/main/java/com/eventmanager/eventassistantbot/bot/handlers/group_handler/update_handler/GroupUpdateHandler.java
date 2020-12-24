package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface GroupUpdateHandler{
    void handle(Update update, SendMessage message, ChatData chatData);
}
