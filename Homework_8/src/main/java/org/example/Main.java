package org.example;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        IOService ioService = new IOService(sc);
        ioService.start();
    }
}