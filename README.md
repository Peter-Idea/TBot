# TBot
TelegramBot - программа реализующая подключения к боту в телеграм, которая сообщает погоду по запросу, есть возможность подписки.

Данные подписки хранятся в БД mySQL.

Погодные данные получаем с сайта https://openweathermap.org/ в виде json

Для парсинга json используются библиотека Gson.

Иногда требуется прокси сервер, его можно задать в коде.
