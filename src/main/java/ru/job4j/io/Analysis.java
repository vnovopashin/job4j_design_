package ru.job4j.io;

public class Analysis {
    public void unavailable(String source, String target) {

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
