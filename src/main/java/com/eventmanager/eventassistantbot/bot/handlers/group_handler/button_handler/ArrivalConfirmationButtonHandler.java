package com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.BotUtils;
import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.Collections;
import java.util.List;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.WAITING_FOR_CONFIRMATION;

@Component
public class ArrivalConfirmationButtonHandler implements GroupMenuButtonHandler {
    @Autowired
    private List<ArrivalConfirmationGroupButtonHandler> arrivalConfirmationButtonHandlers;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        String callData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        if (callData.equals("arrival_confirmation")) {
            chatData.replace(chatId,WAITING_FOR_CONFIRMATION);
            message.setChatId(Long.toString(chatId));
            message.setReplyMarkup(BotUtils.getButtons(Collections.unmodifiableList(arrivalConfirmationButtonHandlers)));
            message.setText("Are you coming?");
        }

    }
    private ReplyKeyboard getButtons() {
        return BotUtils.getInlineKeyboardMarkup(arrivalConfirmationButtonHandlers.stream()
                .map(ArrivalConfirmationGroupButtonHandler::getButton));
    }

    @Override
    public InlineKeyboardButton getButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Arrival Confirmation");
        button.setCallbackData("arrival_confirmation");
        return button;
    }
}
