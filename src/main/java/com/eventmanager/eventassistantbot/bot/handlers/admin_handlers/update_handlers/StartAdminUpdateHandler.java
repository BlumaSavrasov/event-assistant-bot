package com.eventmanager.eventassistantbot.bot.handlers.admin_handlers.update_handlers;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.ADMIN_CHAT;

@Component
public class StartAdminUpdateHandler implements AdminUpdateHandler {
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        if (update.getMessage().hasText()&&update.getMessage().getText().equals("/start")) {
            chatData.registerChat(update.getMessage().getChatId(), ADMIN_CHAT);
        }
    }
}
