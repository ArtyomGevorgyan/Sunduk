package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        int[][] searchDirections = new int[][] {
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1},
                {-1, -1},
                {-1, 0},
                {-1, 1},
        };
        for (String word : words) {
            nextWord:
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (word.charAt(0) == crossword[i][j]) {
                        for (int directions = 0; directions < searchDirections.length; directions++) {
                            int tmp_i = i,
                                    tmp_j = j,
                                    wordPos = 1;
                            while (wordPos < word.length()) {
                                tmp_i += searchDirections[directions][0];
                                tmp_j += searchDirections[directions][1];
                                if (tmp_i < 0 || tmp_i >= crossword.length || tmp_j < 0 || tmp_j >= crossword[tmp_i].length)
                                    break;
                                if (word.charAt(wordPos) != crossword[tmp_i][tmp_j])
                                    break;
                                else if (wordPos == word.length() - 1) {
                                    Word tWord = new Word(word);
                                    tWord.setStartPoint(j, i);
                                    tWord.setEndPoint(tmp_j, tmp_i);
                                    wordList.add(tWord);
                                    break nextWord;
                                }
                                wordPos++;
                            }
                        }
                    }
                }
            }
        }
        return wordList;
    }
/*
    public static List<Word> detectAllWords(int[][] crossword, String... words)
    {
        List<Word> word = new ArrayList<>();
        for (String s : words)
        {
            char[] wordSS = s.toCharArray();
            for (int i = 0; i < crossword.length; i++)
                for (int j = 0; j < crossword[i].length; j++)
                {
                    if (wordSS[0] == crossword[i][j])
                    {
                        String str = "";
                        Word keyword = new Word(str);
                        int len = crossword.length-1;
                        int wordLen = wordSS.length-1;
                         находим совпадение по вертикали вперед
                        if ((len - i) >= wordLen && crossword[i + 1][j] == wordSS[1])   //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = i;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[k][j] == wordSS[l])
                            {
                                str += (char) crossword[k][j];
                                keyword = new Word(str);
                                 System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k++;
                                l++;
                            }
                            k--;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(j, k);
                            if (str.equals(s))word.add(keyword);
                            System.out.println(keyword+"ver+");
                        }
                        проверка по вертикали назад
                        if (i >= wordLen && crossword[i - 1][j] == wordSS[1])   //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = i;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[k][j] == wordSS[l])
                            {
                                str += (char) crossword[k][j];
                                keyword = new Word(str);
                                System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k--;
                                l++;
                            }
                            k++;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(j, k);
                            if (str.equals(s))word.add(keyword);
                             System.out.println(keyword+"ver-");
                        }
                        проверка по горизонтали вперед
                        if ((crossword[i].length-1 - j) >= wordLen && crossword[i][j+1] == wordSS[1])     //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = j;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[i][k] == wordSS[l])
                            {
                                str += (char) crossword[i][k];
                                keyword = new Word(str);
                                 System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k++;
                                l++;
                            }
                            k--;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(k, i);
                            if (str.equals(s))word.add(keyword);
                             System.out.println(keyword+"hor+");
                        }
                        проверка по горизонтали назад
                        if (j >= wordLen && crossword[i][j - 1] == wordSS[1])     //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = j;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[i][k] == wordSS[l])
                            {
                                str += (char) crossword[i][k];
                                keyword = new Word(str);
                                 System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k--;
                                l++;
                            }
                            k++;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(k, i);
                            if (str.equals(s))word.add(keyword);
                             System.out.println(keyword+"hor-");
                        }

                        проверка по диагонали вправо-вниз
                        if ((len - i) >= wordLen && (crossword[i].length-1 - j) >= wordLen && crossword[i+1][j+1] == wordSS[1])     //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = i;
                            int m = j;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[k][m] == wordSS[l])
                            {
                                str += (char) crossword[k][m];
                                keyword = new Word(str);
                                 System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k++;
                                m++;
                                l++;
                            }
                            k--;
                            m--;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(m, k);
                            if (str.equals(s))word.add(keyword);
                             System.out.println(keyword+"diag_vpravo_vniz+");
                        }
                        проверка по диагонали вверх влево
                        if (i >= wordLen && j >= wordLen && crossword[i-1][j-1] == wordSS[1])     //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = i;
                            int m = j;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[k][m] == wordSS[l])
                            {
                                str += (char) crossword[k][m];
                                keyword = new Word(str);
                                System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k--;
                                m--;
                                l++;
                            }
                            k++;
                            m++;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(m, k);
                            if (str.equals(s)) word.add(keyword);
                             System.out.println(keyword+"diag_vverh_vlevo-");
                        }
                        проверка по диагонали вверх вправо
                        if (i >= wordLen && (crossword[i].length-1 - j) >= wordLen && crossword[i-1][j+1] == wordSS[1])     //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = j;
                            int m = i;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[k][m] == wordSS[l])
                            {
                                str += (char) crossword[k][m];
                                keyword = new Word(str);
                                System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k--;
                                m++;
                                l++;
                            }
                            k++;
                            m--;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(m, k);
                            if (str.equals(s)) word.add(keyword);
                             System.out.println(keyword+"diag_vverh_vpravo+");
                        }
                        проверка по диагонали вверх вправо
                        if ((len - i) >= wordLen && j >= wordLen && crossword[i+1][j-1] == wordSS[1])     //проверка на длину символов и совпадение с последующим
                         символом
                        {
                            int k = i;
                            int m = j;
                            int l = 0;
                            str = "";
                            while (l <= wordLen && crossword[k][m] == wordSS[l])
                            {
                                str += (char) crossword[k][m];
                                keyword = new Word(str);
                                System.out.println("k = " + k + "\t" + "j = " + j + "\t");
                                k++;
                                m--;
                                l++;
                            }
                            k--;
                            m++;
                            keyword.setStartPoint(j, i);
                            keyword.setEndPoint(m, k);
                            if (str.equals(s)) word.add(keyword);
                             System.out.println(keyword+"diag_vniz_vlevo+");
                        }
                    }                                        //wordSS
                }                                           //for crossword
        }                                                  //for words
        return word;
   }
*/
    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
