package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        String productName = "";
        for (int i = 1; i < args.length - 2; i++) {
            productName = productName + args[i] + " ";
        }

        Scanner scanner = new Scanner(new File(fileName));
        int maxId = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int id = Integer.parseInt(line.substring(0, 8).trim());
            if (id > maxId)
                maxId = id;
        }
        scanner.close();

        String fileProductName = setSpaces(productName, 30);
        String filePrice = setSpaces(args[args.length - 2], 8);
        String fileQuantity = setSpaces(args[args.length - 1], 4);
        String fileId = setSpaces(String.valueOf(maxId + 1), 8);

        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        printWriter.println(fileId + fileProductName + filePrice + fileQuantity);
        printWriter.close();
    }

    private static String setSpaces(String param, int count) {
        String fileParam;
        if (param.length() > count)
            fileParam = param.substring(0, count);
        else {
            String s = "";
            for (int i = 0; i < (count  - param.length()); i++)
                s = s + " ";
            fileParam = param + s;
        }
        return fileParam;
    }
}
