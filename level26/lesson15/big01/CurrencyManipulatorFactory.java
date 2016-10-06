package com.javarush.test.level26.lesson15.big01;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {

    private static HashMap<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator manipulator=null;
        for (Map.Entry<String, CurrencyManipulator> pair : map.entrySet()){
            if (pair.getKey().equals(currencyCode)){
                manipulator = pair.getValue(); break;
            }
        }
        if (manipulator==null){
            manipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, manipulator);
        }
        return manipulator;
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()    {
        return map.values();
    }
}
