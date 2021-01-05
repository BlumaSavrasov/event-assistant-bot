package com.eventmanager.eventassistantbot.bot.handlers.group_handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class QuestionHandler {
    private static final double DELTA = 0.2;
    private Map<Long,Long> adminMap=new HashMap<>();
     private Map<String,String> question2answer =new HashMap<>();
    List<String> keyWords = List.of( "where", "when", "why");


    public String handleQuestion(Update update) {
        String question = update.getMessage().getText();
        String answer = questionProcessing(question);
        if(newQuestion(question)){
            sendQuestionToAdmin(update);
            return "Question sent to admin";
        }
        else return existingAnswers(question);
    }

    private String questionProcessing(String question) {
        String questionTemplate = findTemplateEquals(question);
        if (questionTemplate==null){
            questionTemplate=findByQuestionKeyword(keyWords, question);
        }
        else if (questionTemplate==null){
            questionTemplate= findTemplateByLevenshtein(question);
        }
        if(questionTemplate!=null)
         return question2answer.get(questionTemplate);
        return null;
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
    private String findTemplateByLevenshtein(String newQuestion) {
        return question2answer
                .keySet()
                .stream()
                .collect(Collectors.toMap((q)->distance((String) q,newQuestion)/newQuestion.length(), Function.identity()))
                .entrySet().stream().filter(e->e.getKey()<=DELTA).sorted().findFirst().orElse(null).getValue();
    }


    private  String findByQuestionKeyword(List<String> integerStringMap, String newQuestion) {
        String questionKey=integerStringMap.stream()
                .filter(newQuestion::contains)
                .findFirst()
                .orElse(null);
        return question2answer
                .keySet()
                .stream()
                .filter(q->q.contains(questionKey))
                .findFirst()
                .orElse(null);
    }


    private  String findTemplateEquals(String newQuestion) {
        return  question2answer
                .keySet()
                .stream()
                .filter(q -> q.equals(newQuestion)).findFirst()
                .orElse(null);
    }
    private boolean newQuestion(String question) {
        return true;
    }

    public static int distance(String basic, String current)
    {
        basic = basic.toLowerCase();
        current = current.toLowerCase();
        int[] costs = new int[current.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= basic.length(); i++)
        {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= current.length(); j++)
            {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        basic.charAt(i - 1) == current.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[current.length()];
    }
}
