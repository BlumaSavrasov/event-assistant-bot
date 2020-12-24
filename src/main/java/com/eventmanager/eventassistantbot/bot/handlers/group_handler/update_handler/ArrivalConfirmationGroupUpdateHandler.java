package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.ChatStatus;
import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.bot_utils.UsersHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class ArrivalConfirmationGroupUpdateHandler implements GroupUpdateHandler {
    @Autowired
    private UsersHandler usersHandler;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        Long chatId = update.getMessage().getChatId();
        message.setChatId(chatId.toString());
        User user = update.getMessage().getFrom();
             if(chatData.get(chatId) == ChatStatus.WAITING_FOR_CONFIRMATION){
            chatData.replace(chatId,ChatStatus.GROUP_CHAT);
            if(update.getMessage().getText().equals("Yes!!")){
                usersHandler.registerNewUserToApprovedGuestList(user,chatId);
                message.setReplyMarkup(new ReplyKeyboardRemove());
                message.setText("We are so happy you are coming!");
            }
            else if(update.getMessage().getText().equals("Sorry but no:/")){
                message.setReplyMarkup(new ReplyKeyboardRemove());
                message.setText("Ohhh this is so sad. we'll miss you!");
            }
        }
    }
}
