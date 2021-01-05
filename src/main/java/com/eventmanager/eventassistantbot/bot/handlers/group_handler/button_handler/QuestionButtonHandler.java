package com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler;

import com.eventmanager.eventassistantbot.bot.bot_utils.BotUtils;
import com.eventmanager.eventassistantbot.bot.bot_utils.ChatData;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.GroupButtonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;

import static com.eventmanager.eventassistantbot.bot.ChatStatus.WAITING_FOR_QUESTION;

@Component
public class QuestionButtonHandler implements GroupMenuButtonHandler {
    @Autowired
    List<GroupQuestionButtonHandler> questionButtons;
    @Override
    public void handle(Update update, SendMessage message, ChatData chatData) {
        String callData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        if (callData.equals("ask_question")) {
            chatData.replace(chatId,WAITING_FOR_QUESTION);
            message.setChatId(Long.toString(chatId));
            message.setText("Please choose a question");
            message.setReplyMarkup(BotUtils.getButtons(Collections.unmodifiableList(questionButtons)));
        }
    }

    @Override
    public InlineKeyboardButton getButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Ask a question");
        button.setCallbackData("ask_question");
        return button;
    }
}
