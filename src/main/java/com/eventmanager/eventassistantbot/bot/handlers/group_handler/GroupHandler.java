package com.eventmanager.eventassistantbot.bot.handlers.group_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.GroupButtonHandler;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler.GroupUpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class GroupHandler {
    @Autowired
    private List<GroupButtonHandler> buttons;
    @Autowired
    private List<GroupUpdateHandler> updateHandlers;


    public SendMessage handleMessage(Update update, ChatData chatData) {
        SendMessage message = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId.toString());
        updateHandlers.forEach(groupUpdateHandler -> groupUpdateHandler.handle(update,message,chatData));
        return message;
    }

    public SendMessage handleButtons(Update update,SendMessage message,ChatData chatData) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        message.setChatId(chatId.toString());
        buttons.forEach(button->button.handle(update,message,chatData));
        return message;

    }
}
