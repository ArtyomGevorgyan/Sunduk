package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        StringBuilder sb = new StringBuilder();
        while (file.ready()) {
            sb.append(file.readLine());
        }
        file.close();
        String line = sb.toString().replaceAll("\r\n","");
        findTags(args[0], line, 0);
    }

    private static void findTags(String tag, String line, int q) {
        int howManyTags = 0, lastTagsIndex = 0;
        int i = q;
        while (i < (line.length() - tag.length() - 1)) {
            if (line.substring(i, i + tag.length() + 1).equals("<" + tag)) {
                if (howManyTags == 0) lastTagsIndex = i;
                howManyTags++;
            }
            else if (line.substring(i, i + tag.length() + 2).equals("</" + tag)) {
                howManyTags--;
                if (howManyTags == 0) {
                    String newString = line.substring(lastTagsIndex, i + tag.length() + 3);
                    System.out.println(newString);
                    findTags(tag, newString, 1);
                }
            }
            i++;
        }
    }
}
