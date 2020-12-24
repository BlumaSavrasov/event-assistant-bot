package com.eventmanager.eventassistantbot.bot.handlers.group_handler;


import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;




public interface GroupButtonHandler {
    void handle(Update update, SendMessage message, ChatData chatData);
    InlineKeyboardButton getButton();
}
