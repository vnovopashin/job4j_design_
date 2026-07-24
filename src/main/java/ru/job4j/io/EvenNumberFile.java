package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int num;
            while ((num = in.read()) != -1) {
                text.append((char) num);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line.trim()) % 2 == 0) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
