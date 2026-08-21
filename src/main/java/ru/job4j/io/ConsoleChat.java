package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            while (!OUT.equals(str)) {
                if (STOP.equals(str)) {
                    while (!CONTINUE.equals(str)) {
                        log.add(str);
                        str = br.readLine();
                    }
                }
                log.add(str);
                int index = (int) (Math.random() * phrases.size());
                String answer = phrases.get(index);
                System.out.println(answer);
                log.add(answer);
                str = br.readLine();
                if (OUT.equals(str)) {
                    log.add(str);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        try {
            return Files.readAllLines(Path.of(botAnswers));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(path)) {
            for (String s : log) {
                printWriter.println(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat(
                "./data/logChat.txt", "./data/answers.txt");
        consoleChat.run();
    }
}

