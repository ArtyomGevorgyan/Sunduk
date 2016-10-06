package com.javarush.test.level33.lesson15.big01;


import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String s : strings) {
            set.add(shortener.getId(s));
        }
        return set;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long l : keys) {
            set.add(shortener.getString(l));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> set = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            set.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        long beforeInvoke = new Date().getTime();
        Set<Long> ids = getIds(shortener, set);
        long afterInvoke = new Date().getTime();
        int elapsedTime = (int)(afterInvoke - beforeInvoke);
        Helper.printMessage(Integer.toString(elapsedTime));

        long beforeInvoke2 = new Date().getTime();
        Set<String> s = getStrings(shortener, ids);
        long afterInvoke2 = new Date().getTime();
        int elapsedTime2 = (int)(afterInvoke2 - beforeInvoke2);
        Helper.printMessage(Integer.toString(elapsedTime2));

        if (s.equals(set)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 1000);
        testStrategy(new OurHashMapStorageStrategy(), 1000);
        testStrategy(new FileStorageStrategy(), 50);
        testStrategy(new OurHashBiMapStorageStrategy(), 1000);
        testStrategy(new HashBiMapStorageStrategy(), 1000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 1000);
    }
}
