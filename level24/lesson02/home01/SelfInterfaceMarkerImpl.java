package com.javarush.test.level24.lesson02.home01;


public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {
    public SelfInterfaceMarkerImpl() {
    }

    public void print() {
        System.out.println("any class");
    }

    public void run() {
        System.out.println("any class is running");
    }
}
