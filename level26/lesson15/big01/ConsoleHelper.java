package com.javarush.test.level26.lesson15.big01;


import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException {
        String s = "";
        try {
            s = reader.readLine();
            if (s.equalsIgnoreCase(res.getString("operation.EXIT")))
            throw new InterruptOperationException();
        }catch(IOException ignore) {}

        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String s;
        while (true) {
            s = readString();
            if (s.length() == 3) {
                break;
            }else {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return s.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] result;
        while (true) {
            result = readString().split(" ");
            int denom;
            int count;
            try
            {
                denom = Integer.parseInt(result[0]);
                count = Integer.parseInt(result[1]);
            }
            catch (Exception e)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (denom <= 0 || count <= 0 || result.length > 2)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        int i;
        while (true) {
            i = Integer.parseInt(readString());
            if (i > 0 && i < 5) {
                break;
            }else {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return Operation.getAllowableOperationByOrdinal(i);
    }
}
