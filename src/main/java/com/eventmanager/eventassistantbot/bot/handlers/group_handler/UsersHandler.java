package com.eventmanager.eventassistantbot.bot.handlers.group_handler;

import com.eventmanager.eventassistantbot.dto.UserDto;
import com.eventmanager.eventassistantbot.services.EventService;
import com.eventmanager.eventassistantbot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsersHandler{
   private Map<Integer,List<Long>> usersCache = new HashMap<>();

//   @Autowired
//   private UserService userService;
   @Autowired
   private EventService eventService;

    public void registerNewUserToInvitedList(User user, Long chatId){
        eventService.addGuestToEvent(new UserDto(user.getId(),user.getFirstName()),2L);
    }

    public void removeUser(User user, Long chatId) {
        eventService.removeGuestFromEvent(new UserDto(user.getId(),user.getFirstName()),2L);
    }

    public void registerNewUserToApprovedGuestList(User user, Long chatId) {

        eventService.addApprovedGuestToEvent(new UserDto(user.getId(),user.getFirstName()),2L);

    }
}
