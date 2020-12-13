package com.eventmanager.eventassistantbot.bot.handlers;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public interface GroupButtonHandler {
    SendMessage handle();
    InlineKeyboardButton getButton();
}
