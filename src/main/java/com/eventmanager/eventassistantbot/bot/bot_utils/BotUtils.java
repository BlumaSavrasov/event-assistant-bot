package com.eventmanager.eventassistantbot.bot.bot_utils;

import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.GroupButtonHandler;
import com.eventmanager.eventassistantbot.bot.handlers.group_handler.button_handler.GroupMenuButtonHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class BotUtils {

    public static InlineKeyboardMarkup getInlineKeyboardMarkup(Stream<InlineKeyboardButton> inlineKeyboardButtonStream) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        inlineKeyboardButtonStream
                .forEach(rowInline::add);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
    public static InlineKeyboardMarkup getButtons(List<GroupButtonHandler> buttons){
        return BotUtils.getInlineKeyboardMarkup(buttons.stream()
                .map(GroupButtonHandler::getButton));
    }
}
