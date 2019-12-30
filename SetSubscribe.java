package com.peter.TelegramBot.test;

class UpdateSubscribe {
    String ChatId = "";
    boolean isSubscribe;
    String lat = "";
    String lon = "";

    //    public UpdateSubscribe(Update update, boolean isSubscribe) {
    public UpdateSubscribe(String ChatId, boolean isSubscribe, String lat, String lon) {
//        System.out.println("Получены ChatId " + ChatId + " isSubscribe " + isSubscribe);
        this.ChatId = ChatId;
        this.isSubscribe = isSubscribe;
        this.lat = lat;
        this.lon = lon;
    }

    public UpdateSubscribe(String ChatId, boolean isSubscribe) {
        System.out.println("Получены ChatId " + ChatId + " isSubscribe " + isSubscribe);
        this.ChatId = ChatId;
        this.isSubscribe = isSubscribe;
    }

    public String getChatId() {
        return ChatId;
    }

    public boolean isSubscribe() {
        return isSubscribe;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}

public class SetSubscribe implements Runnable {

    UpdateSubscribe updateSubscribe;

    //    public SetSubscribe(Update update, boolean isSubscribe) {
    public SetSubscribe(String ChatId, boolean isSubscribe, String lat, String lon) {

        updateSubscribe = new UpdateSubscribe(ChatId, isSubscribe, lat, lon);
    }

    public SetSubscribe(String ChatId, boolean isSubscribe) {

        updateSubscribe = new UpdateSubscribe(ChatId, isSubscribe);
    }


    @Override
    public void run() {
        synchronized (updateSubscribe) {
            BotDB botDB=BotDB.getInstance();
            if (updateSubscribe.getLat().equals("") && updateSubscribe.getLon().equals(""))
                botDB.WriteUnSubscribleBD(updateSubscribe.getChatId(), updateSubscribe.isSubscribe);
            else
                botDB.WriteSubscribleBD(updateSubscribe.getChatId(), updateSubscribe.isSubscribe, updateSubscribe.getLat(), updateSubscribe.getLon());

        }

    }
}
