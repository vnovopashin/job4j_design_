package ru.job4j.io.serialization.xml.book;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
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
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        StringWriter writer = new StringWriter();
        marshaller.marshal(book, writer);
        xml = writer.getBuffer().toString();
        System.out.println(xml);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        Book book2 = (Book) unmarshaller.unmarshal(reader);
        System.out.println(book2);
    }
}
