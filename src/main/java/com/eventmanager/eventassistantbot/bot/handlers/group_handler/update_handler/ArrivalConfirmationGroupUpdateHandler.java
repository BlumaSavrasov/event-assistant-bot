package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.ChatStatus;
import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.ArrivalConfirmationButtonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


public class ArrivalConfirmationGroupUpdateHandler implements GroupUpdateHandler {
    @Autowired
    private List<ArrivalConfirmationButtonHandler> arrivalConfirmationButtonHandlers;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId.toString());
        if(chatData.get(chatId) == ChatStatus.WAITING_FOR_CONFIRMATION){
            chatData.replace(chatId,ChatStatus.GROUP_CHAT);
            arrivalConfirmationButtonHandlers
                    .forEach(arrivalConfirmationButtonHandler -> arrivalConfirmationButtonHandler.handle(update, message, chatData));
        }
    }
}
