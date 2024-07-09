package com.ey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogAnalyzer {

    public static void main(String[] args) {
        String filePath = "C:\\ArunKumarSingh\\InterviewQuestion\\2024\\log_file.txt";
        analyzeLogFile(filePath);
    }

    public static void analyzeLogFile(String filePath) {
        Map<String, Long> functionStartTimes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                long timestamp = Long.parseLong(parts[0]);
                String functionName = parts[1];
                String event = parts[2];

                if (event.equals("START")) {
                    functionStartTimes.put(functionName, timestamp);
                } else if (event.equals("END")) {
                    long startTime = functionStartTimes.get(functionName);
                    long duration = timestamp - startTime;
                    System.out.println(functionName + ": " + duration + "s");
                    functionStartTimes.remove(functionName); // Remove the start time from the map
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
