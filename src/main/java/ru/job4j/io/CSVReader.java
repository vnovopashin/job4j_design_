package ru.job4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(new File(argsName.get("path")))
                .useDelimiter(argsName.get("delimiter"));
        while (scanner.hasNextLine()) {
            String line = scanner.next();
            if (argsName.get("out").equals("stdout")) {
                System.out.println(line);
            } else {
                try (FileWriter fw = new FileWriter(argsName.get("out"), true)) {
                    fw.write(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
