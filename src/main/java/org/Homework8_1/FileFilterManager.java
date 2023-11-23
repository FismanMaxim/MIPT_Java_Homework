package org.Homework8_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileFilterManager {
    private static final Set<String> filterList = new HashSet<>();

    private static void readFilterList(String filePath) {
        filterList.clear();
        try (BufferedReader filterListReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = filterListReader.readLine()) != null) {
                filterList.add(line.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
        }
    }

    private static boolean getShouldFilterLine(String line) {
        String[] words = line.split("\\s+");
        for (String word : words) {
            if (filterList.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private static void filterOutContent(String inputFilePath, String outputFilePath) {
        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            while ((line = inputReader.readLine()) != null) {
                if (!getShouldFilterLine(line)) {
                    outputWriter.write(line);
                    outputWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error while filter or write: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 3)
            throw new IllegalArgumentException("Missing paths to 3 files");

        String inputFileName = args[0];
        String filterListFileName = args[0];
        String outputFileName = args[0];

        readFilterList(filterListFileName);
        filterOutContent(inputFileName, outputFileName);
    }
}