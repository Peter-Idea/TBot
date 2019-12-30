package com.peter.TelegramBot.test;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InitBot {

    public  InitBot(boolean Proxy) {
        System.out.println("инициализация API Context");

//    public boolean InitBot(boolean Proxy) {
        // TODO Initialize Api Context
        ApiContextInitializer.init();

        System.out.println("инициализация Telegram Bots API");

        // TODO Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();
        // TODO Register our bot



        if (Proxy) {
            System.out.println("Используем прокси");
            System.getProperties().put("proxySet", "true");
            System.getProperties().put("socksProxyHost", "127.0.0.1");
            System.getProperties().put("socksProxyPort", "9150");
        }

        System.out.println("Переходим к запуску Бота");

        try {
            botsApi.registerBot(new PeterPogodaBot());
            System.out.println("Бот в эфире");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}
