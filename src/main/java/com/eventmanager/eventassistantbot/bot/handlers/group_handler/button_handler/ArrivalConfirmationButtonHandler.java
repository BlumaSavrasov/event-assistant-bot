package com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.WAITING_FOR_CONFIRMATION;

@Component
public class ArrivalConfirmationButtonHandler implements GroupButtonHandler {
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        String callData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        if (callData.equals("arrival_confirmation")) {
            chatData.replace(chatId,WAITING_FOR_CONFIRMATION);
            message.setChatId(Long.toString(chatId));
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            row.add("Yes!!");
            row.add("Sorry but no:/");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);
            message.setText("Are you coming?");
        }

    }

    @Override
    public InlineKeyboardButton getButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Arrival Confirmation");
        button.setCallbackData("arrival_confirmation");
        return button;
    }
}
