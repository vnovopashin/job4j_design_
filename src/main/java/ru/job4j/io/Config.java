package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(line -> !(line.isEmpty() || line.startsWith("#")))
                    .forEach(line -> {
                        String[] split = line.split("=", 2);
                        if (split.length < 2 || split[0].isEmpty() || split[1].isEmpty()) {
                            throw new IllegalArgumentException("Invalid config file");
                        }
                        values.put(split[0], split[1]);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.print(new Config("data/app.properties"));
        new Config("data/app.properties").load();
        System.out.println();
        System.out.print(new Config("data/app.properties").value("hibernate.connection.url"));
    }
}
