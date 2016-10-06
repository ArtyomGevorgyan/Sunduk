package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        BufferedReader filereader = new BufferedReader(new FileReader(filename));
        String firstString = "";
        try
        {
            firstString = filereader.readLine();
            StringBuilder sb1 = new StringBuilder(firstString);
            while (filereader.ready())
            {
                sb1.append(" ");
                sb1.append(filereader.readLine());
            }
            filereader.close();
            String resultText = sb1.toString();

            String[] resultTextWords = resultText.split(" ");
            for (int i = 0; i < resultTextWords.length - 1; i++)
            {
                String word = resultTextWords[i];
                for (int j = i + 1; j < resultTextWords.length; j++)
                {
                    StringBuilder sb2 = new StringBuilder(word);
                    sb2 = sb2.reverse();
                    String s = sb2.toString();
                    if ((s.equals(resultTextWords[j])) && (i != j) && (!s.equals("")) && (!resultTextWords[i].equals("")) && (!resultTextWords[j].equals("")))
                    {
                        Pair pair = new Pair();
                        pair.first = word;
                        pair.second = resultTextWords[j];
                        result.add(pair);
                        resultTextWords[i] = "";
                        resultTextWords[j] = "";
                        word = "";
                    }
                }
            }
            for (int i = 0; i < result.size(); i++)
            {
                System.out.println(result.get(i).toString());
            }
        }
        catch (Exception e) {

        }

    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
