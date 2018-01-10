package ru.svetopolk.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.LongPollingBot;

/**
 * Created by Smik on 02.01.2018.
 */
public class Main {

    public static void main (String [] args){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBatApi= new TelegramBotsApi();
        LongPollingBot bot = new ru.svetopolk.bot.Bot2();

        try {
            telegramBatApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
