package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.UUID;

public class Book {
    private final UUID id;
    private final String title;
    private final Author author;
    private final double price;
    private final String[] chapters;

    public Book(String title, Author author, double price, String[] chapters) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.price = price;
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return String.format("""
                        {
                          "id": "%s",
                          "title": "%s",
                          "author": {
                            "firstName": "%s",
                            "lastName": "%s"
                          },
                          "price": %.2f,
                          "chapters": %s
                        }
                        """,
                id, title, author.getFirstName(), author.getLastName(), price, Arrays.toString(chapters));
    }

    public static void main(String[] args) {
        Book book = new Book("Java. Библиотека профессионала. Том 1. Основы",
                new Author("Кей", "Хорстманн"),
                2499.0,
                new String[]{"Введение в язык Java",
                        "Среда программирования на Java",
                        "Основные языковые конструкции Java",
                        "Объекты и классы",
                        "Наследование",
                        "Интерфейсы, лямбда-выражения и внутренние классы",
                        "Исключения, утверждения и протоколирование",
                        "Обобщённое программирование",
                        "Коллекции",
                        "Программирование графики",
                        "Компоненты пользовательского интерфейса в Swing",
                        "Параллелизм",
                        "Библиотека JavaFX"});

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(book));

        String bookJson = """
                {
                  "id": "3f8a1b2c-9d4e-4f5a-8b7c-1e2d3f4a5b6c",
                  "title": "Java. Библиотека профессионала. Том 1. Основы",
                  "author": {
                    "firstName": "Кей",
                    "lastName": "Хорстманн"
                  },
                  "price": 2499.00,
                  "chapters": [
                    "Введение в язык Java",
                    "Среда программирования на Java",
                    "Основные языковые конструкции Java",
                    "Объекты и классы",
                    "Наследование",
                    "Интерфейсы, лямбда-выражения и внутренние классы",
                    "Исключения, утверждения и протоколирование",
                    "Обобщённое программирование",
                    "Коллекции",
                    "Программирование графики",
                    "Компоненты пользовательского интерфейса в Swing",
                    "Параллелизм",
                    "Библиотека JavaFX"
                  ]
                }""";

        Book bookMod = gson.fromJson(bookJson, Book.class);
        System.out.println(bookMod);
    }
}
