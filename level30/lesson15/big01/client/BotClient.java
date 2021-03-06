package com.javarush.test.level30.lesson15.big01.client;


import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random() * 99);
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends Client.SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String senderName = "";
            String senderMessageText;

            if (message.contains(": ")) {
                senderName = message.substring(0, message.indexOf(": "));
                senderMessageText = message.substring(message.indexOf(": ") + 2);
            }
            else
            {
                senderMessageText = message;
            }

            switch (senderMessageText) {
                case "дата":
                    sendTextMessage(String.format("Информация для %s: %s", senderName, new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime())));
                    break;
                case "день":
                    sendTextMessage(String.format("Информация для %s: %s", senderName, new SimpleDateFormat("d").format(Calendar.getInstance().getTime())));
                    break;
                case "месяц":
                    sendTextMessage(String.format("Информация для %s: %s", senderName, new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime())));
                    break;
                case "год":
                    sendTextMessage(String.format("Информация для %s: %s", senderName, new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime())));
                    break;
                case "время":
                    sendTextMessage(String.format("Информация для %s: %s", senderName, new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime())));
                    break;
                case "час":
                    sendTextMessage(String.format("Информация для %s: %s",senderName, new SimpleDateFormat("H").format(Calendar.getInstance().getTime())));
                    break;
                case "минуты":
                    sendTextMessage(String.format("Информация для %s: %s", senderName, new SimpleDateFormat("m").format(Calendar.getInstance().getTime())));
                    break;
                case "секунды":
                    sendTextMessage(String.format("Информация для %s: %s", senderName, new SimpleDateFormat("s").format(Calendar.getInstance().getTime())));
            }
        }
    }
}
