package com.peter.TelegramBot.test;

import org.telegram.telegrambots.meta.api.objects.Update;
import com.peter.TelegramBot.test.WeatherData.WeatherData;
import com.peter.TelegramBot.test.ForecastData.ForecastData;
import com.peter.TelegramBot.test.AbstractForest.AbstractData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

class UpdateForecast {
    private Update update;
    private boolean forecast;
    private String chatId;
    private String latitude;
    private String longitude;

    public UpdateForecast(Update update, boolean forecast) {
        this.chatId = update.getMessage().getChatId().toString();
        this.latitude = update.getMessage().getLocation().getLatitude().toString();
        this.longitude = update.getMessage().getLocation().getLongitude().toString();
        this.update = update;
        this.forecast = forecast;
    }

    public UpdateForecast(String chatId, String latitude, String longitude, boolean forecast) {
        this.chatId = chatId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.forecast = forecast;
    }

    public Update getUpdate() {
        return update;
    }

    public boolean isForecast() {
        return forecast;
    }

    public String getChatId() {
        return chatId;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}

public class SenderWeather implements Runnable {
    private UpdateForecast updateForecast;
    private StringBuffer AppidKEY = new StringBuffer("MY-AppidKEY");

    public SenderWeather(Update update, boolean forecast) {
        updateForecast = new UpdateForecast(update, forecast);
    }

    public SenderWeather(String chatId, String latitude, String longitude, boolean forecast) {
        updateForecast = new UpdateForecast(chatId, latitude, longitude, forecast);
    }


    public static String DegreesToCardinal(double degrees) {
//        String[] caridnals = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};
        String[] caridnals = {"С", "СВ", "В", "ЮВ", "Ю", "ЮЗ", "З", "СЗ", "С"};
        return caridnals[(int) Math.round(((double) degrees % 360) / 45)];
    }

    private StringBuffer TextLineMSG(AbstractData abstractData) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Температура ").append(abstractData.getMain().getTemp()).append("C \n")
                .append("Влажность ").append(abstractData.getMain().getHumidity()).append("% \n")
                .append("Атмосферное давление ").append(abstractData.getMain().getPressure()).append(" ГПА \n")
                .append("Скорость ветра ").append(abstractData.getWind().getSpeed()).append(" м/с \n")
                .append("Направление ветра ").append(DegreesToCardinal(abstractData.getWind().getDeg())).append(" \n");

        return stringBuffer;
    }

    @Override
    public void run() {
        synchronized (updateForecast) {
            StringBuffer s = new StringBuffer("http://api.openweathermap.org/data/2.5/");
            StringBuffer APPID = new StringBuffer("&APPID=");
            APPID.append(AppidKEY);
            StringBuffer units = new StringBuffer("&units=metric");
            StringBuffer lang = new StringBuffer("&lang=ru");
            String lat = "&lat=" + updateForecast.getLatitude();
            String lon = "&lon=" + updateForecast.getLongitude();
            StringBuffer msgText = new StringBuffer();

            if (updateForecast.isForecast()) {
                //            запрос прогноза на день
                s.append("forecast?cnt=1").append(lat).append(lon).append(units).append(lang).append(APPID);
//            https://api.openweathermap.org/data/2.5/forecast?cnt=1&lat=35&lon=139&appid=d5684de093cd0555d4c59fa3ea625e9f

                GetJson<ForecastData> getWeahtherForecastData = new GetJson(s, ForecastData.class);
                ForecastData abstractData = getWeahtherForecastData.getAbstractData();
                msgText.append("Прогноз погоды на завтра \n Район: ")
                        .append(abstractData.getCity().getName()).append(" \n")
                        .append(abstractData.getList().iterator().next().getWeather().iterator().next().getDescription()).append(" \n")
                        .append("Температура ").append(abstractData.getList().iterator().next().getMain().getTemp()).append("C \n")
                        .append("Влажность ").append(abstractData.getList().iterator().next().getMain().getHumidity()).append("% \n")
                        .append("Атмосферное давление ").append(abstractData.getList().iterator().next().getMain().getPressure()).append(" ГПА \n")
                        .append("Скорость ветра ").append(abstractData.getList().iterator().next().getWind().getSpeed()).append(" м/с \n")
                        .append("Направление ветра ").append(DegreesToCardinal(abstractData.getList().iterator().next().getWind().getDeg())).append(" \n");

//                        .append(TextLineMSG(abstractData));

            } else {
                //                запрос текущей погды
                s.append("weather?").append(lat).append(lon).append(units).append(lang).append(APPID);
//                System.out.println(s.toString());

                GetJson<WeatherData> getWeahtherForecastData = new GetJson(s, WeatherData.class);
                WeatherData abstractData = getWeahtherForecastData.getAbstractData();
                msgText.append("Погода сегодня \n Район: ")
                        .append(abstractData.getName()).append(" \n")
                        .append(abstractData.getWeather().iterator().next().getDescription()).append(" \n")
                        .append("Температура ").append(abstractData.getMain().getTemp()).append("C \n")
                        .append("Влажность ").append(abstractData.getMain().getHumidity()).append("% \n")
                        .append("Атмосферное давление ").append(abstractData.getMain().getPressure()).append(" ГПА \n")
                        .append("Скорость ветра ").append(abstractData.getWind().getSpeed()).append(" м/с \n")
                        .append("Направление ветра ").append(DegreesToCardinal(abstractData.getWind().getDeg())).append(" \n");

//                        .append(TextLineMSG(abstractData));
            }

//            System.out.println(msgText.toString());
            SendMessage message;
            message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(updateForecast.getChatId())
                    .setText(msgText.toString());

            try {
                //вызываем класс  PeterPogodaBot() с методом execute
                new PeterPogodaBot().execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }
}
