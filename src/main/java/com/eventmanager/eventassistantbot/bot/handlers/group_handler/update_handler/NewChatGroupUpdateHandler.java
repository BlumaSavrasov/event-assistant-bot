package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.GROUP_CHAT;

@Component
public class NewChatGroupUpdateHandler implements GroupUpdateHandler {
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        if(chatData.newChat(update.getMessage().getChatId())) {
            chatData.registerChat(update.getMessage().getChatId(), GROUP_CHAT);
        }
    }
}
