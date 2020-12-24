package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.ChatStatus;
import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.QuestionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class QuestionGroupUpdateHandler implements GroupUpdateHandler {
    @Autowired
    private QuestionHandler questionHandler;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
          if(chatData.get(update.getMessage().getChatId()) == ChatStatus.WAITING_FOR_QUESTION){
            chatData.replace(update.getMessage().getChatId(),ChatStatus.GROUP_CHAT);
            message.setText(questionHandler.handleQuestion(update));
        }
    }
}
