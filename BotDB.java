package com.peter.TelegramBot.test;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.sql.*;


public class BotDB {
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stmt = null;


    private static volatile BotDB instance; // #1

    public static BotDB getInstance() { // #3
        if (instance == null)        //если объект еще не создан
            synchronized (BotDB.class) {
                if (instance == null)
                    instance = new BotDB();    //создать новый объект
            }
        return instance;        // вернуть ранее созданный объект
    }

    private BotDB() {// #2

        try {
            ConnectBDServer();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\n*** SQLException caught ***\n");
            while (e != null) {
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                System.out.println("Vendor: " + e.getErrorCode());
                e = e.getNextException();
            }
        }
    }


    public void ConnectBDServer() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String userName = "root";
        String password = "MY-PASSWORD";
        String url = "jdbc:MySQL://localhost/store" +
                "?&serverTimezone=Europe/Moscow";

        conn = DriverManager.getConnection(url, userName, password);
        // getting Statement object to execute query
        stmt = conn.createStatement();
        System.out.println("Database Connection Established...");
    }

    public void TerminateBDServer() {
        if (conn != null) {
            System.out.println("n***** Let terminate the Connection *****");
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null)
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
    }

    public void WriteSubscribleBD(String chatId, boolean isSubscribe, String lat, String lon) {

        String query = "INSERT INTO store.chatid (ChatId, Lat, Lon) \n" +
                " VALUES (" + chatId + "," + lat + "," + lon + ");";
//        System.out.println(query);
        int rows = 0;
        try {
            rows = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SendMessage message;
        message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(chatId);

        if (rows > 0)
            message.setText("Заявка на подписку принята");
        else
            message.setText("Заявка на подписку отклонена, вероятно Вы подписаны. Отпишитесь и поторно отправте запрос на подписку");
        try {
            //вызываем класс  PeterPogodaBot() с методом execute
            new PeterPogodaBot().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void WriteUnSubscribleBD(String chatId, boolean isSubscribe) {

        String query = "DELETE FROM store.chatid WHERE ChatId = \"" + chatId + "\"";
        SendMessage message;
        message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(chatId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rows > 0)
            message.setText("Вы отписаны");

        else
            message.setText("Заявка на отписку отклонена, вероятно ее не было");
        try {
            //вызываем класс  PeterPogodaBot() с методом execute
            new PeterPogodaBot().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void SenderForecst() throws SQLException {
        String chatId = "";
        String latitude = "";
        String longitude = "";
        String query = "SELECT * FROM store.chatid";

        // execute the query, and get a java resultset
        rs = stmt.executeQuery(query);
        while (rs.next()) {
//            "INSERT INTO store.chatid (ChatId, Lat, Lon) \n" +
//                " VALUES (" + chatId + "," + lat + "," + lon + ");";

            int id = rs.getInt("id");
            chatId = rs.getString("ChatId");
            latitude = rs.getString("Lat");
            longitude = rs.getString("Lon");
            // print the results
//            System.out.println("Получено из БД");
//            System.out.format("%s, %s, %s, %s\n", id, chatId, latitude, longitude);
            Runnable runnable = new SenderWeather(chatId, latitude, longitude, true);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

}
