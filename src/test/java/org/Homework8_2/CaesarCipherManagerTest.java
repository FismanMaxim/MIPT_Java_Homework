package org.Homework8_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CaesarCipherManagerTest {
    private CaesarCipherManager manager;

    @ParameterizedTest
    @CsvSource({
            "'a', 3, 'd'",
            "'i', 5, 'n'",
            "'z', 1, 'a'",
            "'a', 26, 'a'",
            "'A', 3, 'D'",
            "'I', 5, 'N'",
            "'Z', 1, 'A'",
            "'A', 26, 'A'"
    })
    void shouldEncryptLetter(char letter, int shift, char expected) {
        // Assign
        manager = new CaesarCipherManager(shift);

        // Act
        char actual = manager.encrypt(Character.toString(letter)).charAt(0);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "\"abcdefghijklmnopqrstuvwxyz\", 1, \"bcdefghijklmnopqrstuvwxyza\"",
            "\"abcdefghijklmnopqrstuvwxyz\", 2, \"cdefghijklmnopqrstuvwxyzab\"",
            "\"abcdefghijklmnopqrstuvwxyz\", 25, \"zabcdefghijklmnopqrstuvwxy\"",
            "\"abcdefghijklmnopqrstuvwxyz\", 26, \"abcdefghijklmnopqrstuvwxyz\"",
            "\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\", 1, \"BCDEFGHIJKLMNOPQRSTUVWXYZA\"",
            "\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\", 2, \"CDEFGHIJKLMNOPQRSTUVWXYZAB\"",
            "\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\", 25, \"ZABCDEFGHIJKLMNOPQRSTUVWXY\"",
            "\"ABCDEFGHIJKLMNOPQRSTUVWXYZ\", 26, \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"",
    })
    void shouldEncryptLetter(String s, int shift, String expected) {
        // Assign
        manager = new CaesarCipherManager(shift);

        // Act
        String actual = manager.encrypt(s);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}