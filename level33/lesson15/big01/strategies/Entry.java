package com.javarush.test.level33.lesson15.big01.strategies;


import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {
    final Long key;
    String value;
    Entry next;
    final int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
