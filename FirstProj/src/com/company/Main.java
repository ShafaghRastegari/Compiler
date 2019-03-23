package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Main {
    public static void main(String[] args) {

        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("D:\\University\\Term 4\\Compiler\\Sh.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.print(data);
        System.out.println();
    }
}