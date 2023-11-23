package org.Homework8_2;

public class CaesarCipherManager {
    private final int shift;

    public CaesarCipherManager(int shift) {
        this.shift = shift;
    }

    public String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) encrypted.append(encryptLetter(c));
            else encrypted.append(c);
        }
        return encrypted.toString();
    }

    private char encryptLetter(char c) {
        if (Character.isUpperCase(c))
            return (char) (((c - 'A' + shift) % 26) + 'A');
        else
            return (char) (((c - 'a' + shift) % 26) + 'a');
    }
}