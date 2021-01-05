package com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.UsersHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class NoArrivalConfirmationGroupButtonHandler implements ArrivalConfirmationGroupButtonHandler {
    @Autowired
    private UsersHandler usersHandler;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        String callData = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        User user = update.getCallbackQuery().getFrom();
        if(callData.equals("no")){
            usersHandler.registerNewUserToApprovedGuestList(user,chatId);
            message.setText("Ohhh this is so sad. we'll miss you!");
        }
    }

    @Override
    public InlineKeyboardButton getButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Sorry, but no:/");
        button.setCallbackData("no");
        return button;
    }
}
