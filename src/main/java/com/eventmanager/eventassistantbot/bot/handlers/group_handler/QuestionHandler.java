package com.eventmanager.eventassistantbot.bot.handlers.group_handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
public class QuestionHandler {
    private Map<Long,Long> adminMap=new HashMap<>();
     private Map<String,String> question2answer =new HashMap<>();


    public String handleQuestion(Update update) {
        String question = update.getMessage().getText();
        if(newQuestion(question)){
            sendQuestionToAdmin(update);
            return "Question sent to admin";
        }
        else return existingAnswers(question);
    }

    private String existingAnswers(String question) {
        return "existing answer";
    }

    private void sendQuestionToAdmin(Update update) {
        //todo
//        SendMessage message=new SendMessage();
//        message.setChatId(adminMap.get(update.getMessage().getChatId()).toString());
//        String firstName = update.getMessage().getFrom().getFirstName();
//        String question = update.getMessage().getText();
//        message.setText(firstName + " is asking " + question);
//        need to send it to admin
    }

    private boolean newQuestion(String question) {
        return true;
    }
}
