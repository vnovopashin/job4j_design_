package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        /* Получаем параметры*/
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        /* Парсим поля для фильтрации (те, что нужно оставить) - сохраняем порядок*/
        List<String> fieldsToKeep = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            String[] filterFields = filter.split(",");
            for (String field : filterFields) {
                fieldsToKeep.add(field.trim());
            }
        }

        List<String> allLines = Files.readAllLines(Path.of(path));
        if (allLines.isEmpty()) {
            throw new IllegalArgumentException("CSV файл пуст");
        }

        /* Читаем заголовок*/
        String headerLine = allLines.get(0);
        List<String> headers = parseCsvLine(headerLine, delimiter);

        /* Создаем маппинг: имя поля -> его индекс в исходном файле*/
        Map<String, Integer> headerIndexMap = new LinkedHashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            headerIndexMap.put(headers.get(i).trim(), i);
        }

        /* Определяем индексы полей, которые нужно оставить, в порядке из фильтра*/
        List<Integer> indicesToKeep = new ArrayList<>();
        List<String> newHeaders = new ArrayList<>();

        if (fieldsToKeep.isEmpty()) {
            /* Если фильтр не указан, оставляем все поля*/
            for (int i = 0; i < headers.size(); i++) {
                indicesToKeep.add(i);
                newHeaders.add(headers.get(i));
            }
        } else {
            /* Идем в порядке, указанном в фильтре*/
            for (String field : fieldsToKeep) {
                if (headerIndexMap.containsKey(field)) {
                    int index = headerIndexMap.get(field);
                    indicesToKeep.add(index);
                    newHeaders.add(headers.get(index));
                } else {
                    System.err.println("Warning: Field '" + field + "' not found in CSV header");
                }
            }
        }

        /* Проверяем, что есть хоть одно поле для вывода*/
        if (newHeaders.isEmpty()) {
            throw new IllegalArgumentException("No fields to output. Check your filter parameter.");
        }

        /* Записываем заголовок*/
        String newHeaderLine = String.join(delimiter, newHeaders);
        writeOutput(out, newHeaderLine, false);

        /* Обрабатываем строки данных (начиная со второй)*/
        for (int lineNum = 1; lineNum < allLines.size(); lineNum++) {
            String line = allLines.get(lineNum);
            if (line.trim().isEmpty()) {
                continue;
            }

            /* Разбиваем строку с учётом кавычек*/
            List<String> fields = parseCsvLine(line, delimiter);

            /* Если количество полей не совпадает с заголовком, пробуем простой split*/
            if (fields.size() != headers.size()) {
                fields = Arrays.asList(line.split(delimiter, -1));
            }

            /* Оставляем только нужные поля в порядке из фильтра*/
            List<String> newFields = new ArrayList<>();
            for (int index : indicesToKeep) {
                if (index < fields.size()) {
                    newFields.add(fields.get(index));
                } else {
                    newFields.add(""); /* Пустое поле, если индекс вышел за границы*/
                }
            }

            /* Записываем строку*/
            String newLine = String.join(delimiter, newFields);
            writeOutput(out, newLine, true);
        }
    }

    /**
     * Запись вывода в консоль или файл
     */
    private static void writeOutput(String out, String data, boolean append) {
        try {
            if ("stdout".equals(out)) {
                System.out.println(data);
            } else {
                try (FileWriter fw = new FileWriter(out, append)) {
                    fw.write(data + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи вывода: " + e.getMessage(), e);
        }
    }

    /**
     * Парсит CSV строку с учётом кавычек
     */
    private static List<String> parseCsvLine(String line, String delimiter) {
        List<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        char delimiterChar = delimiter.charAt(0);

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        currentField.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    currentField.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == delimiterChar) {
                    result.add(currentField.toString());
                    currentField = new StringBuilder();
                } else {
                    currentField.append(c);
                }
            }
        }
        result.add(currentField.toString());
        return result;
    }

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
