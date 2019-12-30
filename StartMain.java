package com.peter.TelegramBot.test;

public class StartMain {

    public static void main(String[] args) {

        boolean OnProxy = false;

        OnProxy = true;

//        BotDB botDB=BotDB.getInstance();
        InitBot startBot = new InitBot(OnProxy);
        TimerSubsrible timerSubsrible = new TimerSubsrible();


    }
}
