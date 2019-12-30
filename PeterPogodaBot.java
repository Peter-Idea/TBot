package com.peter.TelegramBot.test;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;


public class PeterPogodaBot extends TelegramLongPollingBot {
    //    private boolean forecast = false;
//    private boolean isSubscribe = false;
    private enum Cast {WEATHER, FORECAST, SUBCRIBLE, UNSUBSCRIBLE}

    private HashMap<Long, Cast> pairChatIdCast = new HashMap<>();

    public PeterPogodaBot() {
        System.out.println("Отработал конструктор, \n первые ступени отошли. \nПолет стабильный... \n Выходим на Орбиту");
    }

    public PeterPogodaBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields

            if (update.getMessage().getText().equals("/weather")) {
//                forecast = false;
                pairChatIdCast.put(update.getMessage().getChatId(), Cast.WEATHER);
                message.setChatId(update.getMessage().getChatId())
                        .setText("Для получения текущей погоды отправте координаты");
            }
            if (update.getMessage().getText().equals("/forecast")) {
//                forecast = true;
                pairChatIdCast.put(update.getMessage().getChatId(), Cast.FORECAST);
                message.setChatId(update.getMessage().getChatId())
                        .setText("Для получения прогноза погоды на завтра отправте координаты");
            }

            if (update.getMessage().getText().equals("/subscribe")) {
//                System.out.println("получен запрос на подписку от "+update.getMessage().getChatId()+",ждем локацию");
//                isSubscribe = !isSubscribe;
                pairChatIdCast.put(update.getMessage().getChatId(), Cast.SUBCRIBLE);

                message.setChatId(update.getMessage().getChatId())
                        .setText("Для подписки пришлите свою локацию");
            }

            if (update.getMessage().getText().equals("/unsubscribe")) {
//                System.out.println("получен запрос на отписку");
                Runnable runnable = new SetSubscribe(update.getMessage().getChatId().toString(), true);
                Thread thread = new Thread(runnable);
//                System.out.println("инициируем поток отписки");
                thread.start();
                message.setChatId(update.getMessage().getChatId())
                        .setText("Заявка на отписку принята");
            }


            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (update.getMessage().hasLocation()) {
            Runnable runnable=null;

            if (pairChatIdCast.containsKey(update.getMessage().getChatId())) {
                Cast value = pairChatIdCast.get(update.getMessage().getChatId());
                switch (value) {

                    case SUBCRIBLE:
//            if (isSubscribe) {
//                isSubscribe = !isSubscribe;
                        pairChatIdCast.remove(update.getMessage().getChatId());
                        String lat = update.getMessage().getLocation().getLatitude().toString();
                        String lon = update.getMessage().getLocation().getLongitude().toString();
                        runnable = new SetSubscribe(update.getMessage().getChatId().toString(),
                                false,
                                lat,
                                lon);
//                        System.out.println("инициируем поток подписки");
                        break;

                    case FORECAST:
                        pairChatIdCast.remove(update.getMessage().getChatId());
                         runnable = new SenderWeather(update, true);
                        break;

//                case WEATHER:
                    default:
                        pairChatIdCast.remove(update.getMessage().getChatId());
                        Runnable Runnable = new SenderWeather(update, false);
                        break;
                }
            } else {
// Если пользователь не оставлял никаких запросов и не попал в МАП,
// но первым делом отправил нам локацию покажем ему текучую погоду
                 runnable = new SenderWeather(update, false);
            }

            if (runnable!=null){
            Thread thread = new Thread(runnable);
            thread.start();
            }
        }




    }


    @Override
    public String getBotUsername() {
        // TODO
        return "Peter_Pogoda_Bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "MY-TOKEN";
    }


}
