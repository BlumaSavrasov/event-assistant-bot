package com.eventmanager.eventassistantbot.bot.handlers.admin_handlers;


import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.admin_handlers.update_handlers.AdminUpdateHandler;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.GroupButtonHandler;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler.GroupUpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class AdminHandler {
//    @Autowired
//    private List<GroupButtonHandler> buttons;
    @Autowired
    private List<AdminUpdateHandler> updateHandlers;
    public SendMessage handleMessage(Update update, ChatData chatData) {
        SendMessage message = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId.toString());
        updateHandlers.forEach(adminUpdateHandler -> adminUpdateHandler.handle(update,message,chatData));
        return message;
    }

}
