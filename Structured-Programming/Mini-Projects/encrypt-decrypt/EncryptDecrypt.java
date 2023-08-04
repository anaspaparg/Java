package gr.aueb.cf.homework.myJavaProjects;

/**
 * Encrypts strings (English Uppercase), based on an algorithm,
 * that shifts the initial string KEY positions to the right,
 * based on ASCII table
 */

public class EncryptDecrypt {
    public static void main(String[] args) {
        String s = "ANASTASIS";
        final int KEY = 3;

        String encrypted = encrypt(s, KEY);
        System.out.printf("%s --> %s", s, encrypted);

        System.out.println();

        String decrypted = decrypt(encrypted, KEY);
        System.out.printf("%s --> %s", encrypted, decrypted);
    }


    /**
     * Encrypts a string with a symmetric-like cryptography
     * Shifts initial characters key-positions to the right
     * @param s     The initial string
     * @param key   Key representing how many positions to move to the right
     * @return      The encrypted string
     */
    public static String encrypt(String s, int key) {
        StringBuilder encrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (Character.isUpperCase(ch)) encrypted.append(cipher(ch, key));
            else encrypted.append(ch);
        }
        return encrypted.toString();
    }

    /**
     * Encrypts an English uppercase character based on a secret key
     * @param ch    The initial character
     * @param key   Key representing how many positions to move to the right
     * @return      The encrypted character
     */
    public static char cipher(char ch, int key) {
        int m, c;

        m = ch - 65;

        c = (m + key) % 26;

        return (char) (c + 65);
    }

    /**
     * Decrypts a string with a symmetric-like cryptography
     * Shifts encrypted characters key-positions to the left
     * @param s     The encrypted string
     * @param key   Key representing how many positions to move to the left
     * @return      The decrypted string
     */
    public static String decrypt(String s, int key) {
        StringBuilder decrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) decrypted.append(decipher(ch, key));
            else decrypted.append(ch);

        }
        return decrypted.toString();
    }

    /**
     * Decrypts an English uppercase character based on a secret key
     * @param ch    The initial character
     * @param key   Key representing how many positions to move to the left
     * @return      The decrypted char
     */
    public  static char decipher(char ch, int key) {
        int m, c;

        c = ch - 65;

        m = ((c - key) + 26) % 26;

        return (char) (m + 65);

    }
}
