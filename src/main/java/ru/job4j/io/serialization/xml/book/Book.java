package ru.job4j.io.serialization.xml.book;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute
    private UUID id;

    private String title;

    private Author author;
    private double price;

    @XmlElementWrapper(name = "chapters")
    @XmlElement(name = "chapter")
    private String[] chapters;

    public Book() {
    }

    public Book(String title, Author author, double price, String[] chapters) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.price = price;
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Book{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", author=" + author
                + ", price=" + price
                + ", chapters=" + Arrays.toString(chapters)
                + '}';
    }
}
