package com.eventmanager.eventassistantbot.bot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class QuestionHandler {
    public SendMessage handleQuestion(Update update) {
        SendMessage message=new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        String question = update.getMessage().getText();
        if(newQuestion(question)){
            sendQuestionToAdmin(update);
            message.setText("Question sent to admin");
        }
        else message.setText(existingAnswers(question));
        return message;
    }

    private String existingAnswers(String question) {
        //todo
        return " existing answer";
    }

    private void sendQuestionToAdmin(Update update) {
        //todo
    }

    private boolean newQuestion(String question) {
        //todo
        return true;
    }
}
