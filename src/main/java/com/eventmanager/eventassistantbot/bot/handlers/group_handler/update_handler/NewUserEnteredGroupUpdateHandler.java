package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.UsersHandler;
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
public class NewUserEnteredGroupUpdateHandler implements GroupUpdateHandler {
    @Autowired
    private UsersHandler usersHandler;
    @Autowired
    private List<GroupMenuButtonHandler> menuButtons;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        User user = update.getMessage().getFrom();
        if (!update.getMessage().getNewChatMembers().isEmpty()) {
            usersHandler.registerNewUserToInvitedList(user, update.getMessage().getChatId());
            message.setText(welcomeMessage(user.getFirstName()));
            message.setReplyMarkup(getButtons(Collections.unmodifiableList(menuButtons)));
        }
    }

    private String welcomeMessage(String firstName) {
        return "Hi "+ firstName+"\nwelcome to the group\n";

    }
}
