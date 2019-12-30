package com.peter.TelegramBot.test;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

class ShedulerForecast extends TimerTask {
    public static int i = 0;

    public void run() {

//         подкл к БД
//         получит все строки ChatId + location
//         отпрравить прогноз погоды
//         реализуем в классе БД
        BotDB botDB = BotDB.getInstance();
        try {
            botDB.SenderForecst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class TimerSubsrible {
    public TimerSubsrible() {
        Timer timer = new Timer("BotSchedule", true); // Создаем таймер
        TimerTask task = new ShedulerForecast();

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 12);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        // every day at 12am you run your task
     timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
//    every 10 second
//        timer.schedule(task, today.getTime(), 30 * 1000); // period: 10 сек

    }

}
