package com.eventmanager.eventassistantbot.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class EventService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${event.service.url}")
    private String eventServiceUrl;

    @SneakyThrows
    public void addGuestToEvent(User user,Long eventId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String userJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        restTemplate.exchange(eventServiceUrl+"/events/guest/"+eventId, HttpMethod.POST,new HttpEntity<>(userJson,httpHeaders),User[].class);

    }
}
