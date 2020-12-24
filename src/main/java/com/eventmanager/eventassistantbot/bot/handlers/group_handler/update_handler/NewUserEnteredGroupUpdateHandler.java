package com.eventmanager.eventassistantbot.bot.handlers.group_handler.update_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.bot_utils.UsersHandler;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.GroupButtonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewUserEnteredGroupUpdateHandler implements GroupUpdateHandler {
    @Autowired
    private UsersHandler usersHandler;
    @Autowired
    private List<GroupButtonHandler> buttons;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        User user = update.getMessage().getFrom();
        if (!update.getMessage().getNewChatMembers().isEmpty()) {
            usersHandler.registerNewUserToInvitedList(user, update.getMessage().getChatId());
            message.setText(welcomeMessage(user.getFirstName()));
            InlineKeyboardMarkup markupInline = getMenu();
            message.setReplyMarkup(markupInline);
        }
    }
    private InlineKeyboardMarkup getMenu(){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        buttons.stream()
                .map(GroupButtonHandler::getButton)
                .forEach(rowInline::add);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    private String welcomeMessage(String firstName) {
        return "Hi "+ firstName+"\nwelcome to the group\n";

    }
}
