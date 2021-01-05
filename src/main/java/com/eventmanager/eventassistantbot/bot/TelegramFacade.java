package com.eventmanager.eventassistantbot.bot;


import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.admin_handlers.AdminHandler;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.GroupHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatTitle;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.*;

@Component
public class TelegramFacade {
    @Autowired
    private GroupHandler groupHandler;
    @Autowired
    private AdminHandler adminHandler;
    @Autowired
    private ChatData chatData;



    public BotApiMethod updateHandler(Update update) {
        SendMessage message= new SendMessage();
        if (update.hasMessage()) {
            if (!update.getMessage().getChat().isGroupChat()) {
                adminHandler.handleMessage(update,chatData);
            }
            else if (update.getMessage().getChat().isGroupChat() ) {
                return groupHandler.handleMessage(update, chatData);
            }

        }
        else if (update.hasCallbackQuery()){
            if(update.getCallbackQuery().getMessage().getChat().isGroupChat()) {
                return groupHandler.handleButtons(update, message, chatData);
            }
        }
     return new SendMessage();
    }
}
