package com.eventmanager.eventassistantbot.bot;


public enum ChatStatus {
    GROUP_CHAT,
    WAITING_FOR_QUESTION,
    WAITING_FOR_CONFIRMATION,
    ADMIN_CHAT,
    WAITING_FOR_ANSWER;


    public boolean isGroup() {
        return this.name().equals("GROUP_CHAT") || this.name().equals("WAITING_FOR_QUESTION") || this.name().equals("WAITING_FOR_CONFIRMATION");
    }
}
