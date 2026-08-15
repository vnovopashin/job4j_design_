package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * 2. Добавьте тесты: на отсутствие ключа "-=значение", на отсутствие значения "-ключ=",
 * на отсутствие символа "=" "-ключ:значение", на отсутствие символа "-" "ключ=значение".
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        /* TODO add the necessary checks. */
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        /* TODO parse args to values. */
        for (int i = 0; i < args.length; i++) {
            if (!args[i].contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + args[i] + "' does not contain an equal sign");
            }
            String[] arg = args[i].split("=", 2);
            if (!arg[0].startsWith("-")) {
                throw new IllegalArgumentException(
                        "Error: This argument '" + args[i] + "' does not start with a '-' character");
            }
            String key = arg[0].substring(1);
            if (key.isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + args[i] + "' does not contain a key");
            }
            if (arg[1].isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + args[i] + "' does not contain a value");
            }
            values.put(key, arg[1]);
        }
    }

    public static ArgsName of(String[] args) {
        /* TODO add the necessary checks. */
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
