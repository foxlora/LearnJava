package com.foxlora.io;

import java.io.File;
import java.nio.file.Paths;

public class MainClass {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\18351\\Desktop\\test.txt");
        System.out.println(f);
        System.out.println(f.getParent());

    }
}
