package com.javarush.test.level38.lesson10.home02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Changelog {
    Revision[] value();
}
