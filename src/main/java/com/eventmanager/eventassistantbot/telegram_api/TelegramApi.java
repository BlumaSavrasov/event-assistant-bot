package com.eventmanager.eventassistantbot.telegram_api;


import org.springframework.stereotype.Component;
import org.telegram.api.TLApiContext;
import org.telegram.api.account.TLAccountAuthorizations;
import org.telegram.api.auth.TLAuthorization;
import org.telegram.api.chat.TLAbsChat;
import org.telegram.api.chat.channel.TLChannel;
import org.telegram.api.functions.channels.TLRequestChannelsCreateChannel;


public class TelegramApi {
    public static void main(String[] args) {
        TLRequestChannelsCreateChannel tlRequestChannelsCreateChannel = new TLRequestChannelsCreateChannel();
        tlRequestChannelsCreateChannel.setTitle("Event Group");
        tlRequestChannelsCreateChannel.setAbout("group for the user to interact with each other and with the assistant bot");
        tlRequestChannelsCreateChannel.setFlags(1);
        TLAuthorization tlAuthorization = new TLAuthorization();
        TLAccountAuthorizations tlAccountAuthorizations = new TLAccountAuthorizations();
        TLApiContext tlApiContext = new TLApiContext();
    }
}
