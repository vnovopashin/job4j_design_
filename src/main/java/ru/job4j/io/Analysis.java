package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> periods = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            String startTime = null;
            String endTime = null;
            boolean isUnavailable = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length < 2) {
                    continue;
                }

                String status = parts[0];
                String time = parts[1];

                boolean isErrorStatus = status.equals("400") || status.equals("500");

                if (isErrorStatus && !isUnavailable) {
                    startTime = time;
                    isUnavailable = true;
                } else if (!isErrorStatus && isUnavailable) {
                    endTime = time;
                    periods.add(startTime + ";" + endTime + ";");
                    isUnavailable = false;
                }
            }

            if (isUnavailable) {
                periods.add(startTime + ";" + "???;");
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading log file: " + source, e);
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(target))) {
            for (String period : periods) {
                out.println(period);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to target file: " + target, e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
