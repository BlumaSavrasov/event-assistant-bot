package com.eventmanager.eventassistantbot.bot.bot_utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsersHandler{
   private Map<Integer,List<Long>> usersCache = new HashMap<>();

    public boolean newUser(int id,Long chatId){
        if(!usersCache.containsKey(id)) {
            List<Long> chatList = new ArrayList<>();
            chatList.add(chatId);
            usersCache.put(id,chatList);
            return true;
        }
        else if(!usersCache.get(id).contains(chatId)){
            usersCache.get(id).add(chatId);
            return true;
        }
        return false;
    }
}
