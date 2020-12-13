package com.eventmanager.eventassistantbot.bot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class QuestionButton implements GroupButtonHandler {
    @Override
    public SendMessage handle() {
        return null;
    }

    @Override
    public InlineKeyboardButton getButton() {
        return null;
    }
}
