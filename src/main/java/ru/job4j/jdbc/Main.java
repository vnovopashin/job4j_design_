package ru.job4j.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("src/main/resources/app.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TableEditor editor = new TableEditor(properties);
        editor.dropTable("Students");
        System.out.println("- createTable() – создает пустую таблицу без столбцов с указанным именем");
        editor.createTable("Students");
        System.out.println(editor.getTableScheme("Students"));
        System.out.println();
        System.out.println("- addColumn() – добавляет столбец в таблицу");
        editor.addColumn("Students", "subject", "text");
        System.out.println(editor.getTableScheme("Students"));
        System.out.println();
        System.out.println("- renameColumn() – переименовывает столбец");
        editor.renameColumn("Students", "subject", "full_name");
        System.out.println(editor.getTableScheme("Students"));
        System.out.println();
        System.out.println("- dropColumn() – удаляет столбец из таблицы");
        editor.dropColumn("Students", "full_name");
        System.out.println(editor.getTableScheme("Students"));
        System.out.println();
        System.out.println("- dropTable() – удаляет таблицу по указанному имени");

        editor.dropTable("Students");
        try {
            editor.getTableScheme("Students");
            System.out.println("Таблица всё ещё существует!");
        } catch (Exception e) {
            System.out.println("Таблица удалена: " + e.getMessage());
        }
        editor.close();
    }
}
