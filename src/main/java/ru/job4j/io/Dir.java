package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw  new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        String green = "\u001B[32m";
        String reset = "\u001B[0m";
        String bold = "\u001B[1m";
        System.out.printf("Размер директории: %s%n", file.getTotalSpace());
        int countDirs = 0;
        int countFiles = 0;
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isDirectory()) {
                countDirs++;
                String dirName = subfile.getName();
                System.out.printf("\t\t%s%s%s%s\n", green, bold, dirName, reset);
                File[] files = subfile.listFiles();
                for (File file1 : Objects.requireNonNull(files)) {
                    countFiles++;
                    System.out.printf("\t\t├Имя файла %s : Размер файла %s байт\n", file1.getName(), file1.length());
                }
            }
        }
        System.out.printf("%d directories, %d files", countDirs, countFiles);
    }
}
