package ru.job4j.io;

import java.io.*;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/log.txt"))) {
            return reader.lines()
                    .filter(s -> s.matches(".*\\s404\\s.*"))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTo(String out) {
        var data = filter();
        /* save DATA to out */
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            data.forEach(output::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
