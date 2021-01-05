package com.eventmanager.eventassistantbot.services;


import com.eventmanager.eventassistantbot.api.ExternalApiConstants;
import com.eventmanager.eventassistantbot.dto.UserDto;
import com.eventmanager.eventassistantbot.services.token_service.TokenHolderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.eventmanager.eventassistantbot.api.ExternalApiConstants.BY_GUEST;
import static com.eventmanager.eventassistantbot.api.ExternalApiConstants.HEADER;

@Service
public class EventService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${event.service.url}")
    private String eventServiceUrl;
//    @Autowired
//    private TokenHolderService tokenHolderService;


    @SneakyThrows
    public void addGuestToEvent(UserDto user, Long eventId) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////        httpHeaders.set(HEADER,tokenHolderService.getToken());
//        String userJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
//        restTemplate.exchange(eventServiceUrl+"/events"+BY_GUEST+"/"+eventId, HttpMethod.POST,new HttpEntity<>(userJson,httpHeaders),User[].class);
        User[] users = restTemplate.postForObject(eventServiceUrl + "/events/invited/" + eventId, user, User[].class);

    }

    public void removeGuestFromEvent(UserDto userDto, Long eventId) {
        restTemplate.delete(eventServiceUrl+"/events/guest?eventId="+eventId+"&guestId="+userDto.getId());
    }

    public void addApprovedGuestToEvent(UserDto user, Long eventId) {
        User[] users = restTemplate.postForObject(eventServiceUrl + "/events/guest/" + eventId, user, User[].class);
    }
}
