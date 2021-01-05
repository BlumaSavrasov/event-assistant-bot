package com.eventmanager.eventassistantbot.bot.handlers.admin_handlers.update_handlers;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public interface AdminUpdateHandler{
    void handle(Update update, SendMessage message, ChatData chatData);
}
