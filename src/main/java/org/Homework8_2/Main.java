package org.Homework8_2;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Missing paths of 3 files\nTry using: java Main <input_file> <shift> <output_file>");
            return;
        }

        String inputFilename = args[0];
        int shift = 0;
        try {
            shift = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Failed to convert shift string to an integer");
        }
        String outputFilename = args[2];

        CaesarCipherManager cipher = new CaesarCipherManager(shift);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Шифруем сдвигом
                String encryptedLine = cipher.encrypt(line);

                writer.write(encryptedLine);
                writer.newLine();
            }
        }
    }
}

