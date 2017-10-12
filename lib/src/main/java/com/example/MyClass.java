package com.example;

import java.util.Scanner;

public class MyClass {
    public static void main(String[] args) {
        eventHandl ev = new eventHandl();
        msgHandler ms = new msgHandler();
        ms.start();
        ev.eventHandler(ms);
    }
}
