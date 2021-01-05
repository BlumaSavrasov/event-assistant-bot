package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.GroupMenuButtonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Collections;
import java.util.List;

import static com.eventmanager.eventassistantbot.bot.bot_utils.BotUtils.getButtons;

@Component
public class HelpGroupUpdateHandler implements GroupUpdateHandler {
    @Autowired
    private List<GroupMenuButtonHandler> menuButtons;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        User user = update.getMessage().getFrom();
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        if(text.toLowerCase().equals("help")){
            message.setText(helpMessage(user.getFirstName()));
            message.setReplyMarkup(getButtons(Collections.unmodifiableList(menuButtons)));
        }
    }

    private String helpMessage(String firstName) {
        return "Hi "+firstName+"\nIf you need any help, please use the menu.";
    }
}
