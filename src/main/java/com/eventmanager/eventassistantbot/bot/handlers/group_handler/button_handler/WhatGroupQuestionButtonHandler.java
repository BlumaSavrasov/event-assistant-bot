package com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


@Component
public class WhatGroupQuestionButtonHandler implements GroupQuestionButtonHandler {
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        String callData = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        User user = update.getCallbackQuery().getFrom();
        if(callData.equals("what_question")){
            message.setText("answer to what is the event about");
        }
    }

    @Override
    public InlineKeyboardButton getButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("what is the event about?");
        button.setCallbackData("what_question");
        return button;
    }
}
