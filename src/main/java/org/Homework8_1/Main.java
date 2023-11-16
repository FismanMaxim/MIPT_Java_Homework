package org.Homework8_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Initial file:");
        String inputFileName = in.nextLine();
        System.out.println("File-filter");
        String filterListFileName = in.nextLine();
        System.out.println("Result file:");
        String outputFileName = in.nextLine();

        Set<String> filterList = new HashSet<>();
        try (BufferedReader filterListReader = new BufferedReader(new FileReader(filterListFileName))) {
            String line;
            while ((line = filterListReader.readLine()) != null) {
                filterList.add(line.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
            return;
        }

        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            String line;
            while ((line = inputReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                boolean needToFilterOut = false;
                for (String word : words) {
                    if (filterList.contains(word.toLowerCase())) {
                        needToFilterOut = true;
                        break;
                    }
                }

                if (!needToFilterOut) {
                    outputWriter.write(line);
                    outputWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error while filter or write: " + e.getMessage());
        }
    }
}