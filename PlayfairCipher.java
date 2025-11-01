import java.util.*;
public class PlayfairCipher {
    static void toLowerCase(StringBuilder text) {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                text.setCharAt(i, (char)(ch + 32));
            }
        }
    }
    static void removeSpaces(StringBuilder text) {
        int i = 0;
        while (i < text.length()) {
            if (text.charAt(i) == ' ') {
                text.deleteCharAt(i);
            } else {
                i++;
            }
        }
    }
    static void replaceJ(StringBuilder text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'j') {
                text.setCharAt(i, 'i');
            }
        }
    }
    static void processInput(StringBuilder text) {
        for (int i = 0; i < text.length() - 1; i += 2) {
            if (text.charAt(i) == text.charAt(i + 1)) {
                text.insert(i + 1, 'x');
            }
        }
        if (text.length() % 2 != 0) {
            text.append('x');
        }
    }
    static void generateKeyMatrix(StringBuilder key, char[][] matrix) {
        boolean[] used = new boolean[26];
        used['j' - 'a'] = true; 
        int x = 0, y = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch == 'j') ch = 'i';
            if (!used[ch - 'a']) {
                matrix[x][y++] = ch;
                used[ch - 'a'] = true;
                if (y == 5) {
                    x++;
                    y = 0;
                }
            }
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (!used[ch - 'a']) {
                matrix[x][y++] = ch;
                used[ch - 'a'] = true;
                if (y == 5) {
                    x++;
                    y = 0;
                }
            }
        }
    }
    static void printMatrix(char[][] matrix) {
        System.out.println("\nPlayfair Cipher Matrix :");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void findPosition(char[][] matrix, char a, char b, int[] pos) {
        if (a == 'j') a = 'i';
        if (b == 'j') b = 'i';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == a) {
                    pos[0] = i;
                    pos[1] = j;
                }
                if (matrix[i][j] == b) {
                    pos[2] = i;
                    pos[3] = j;
                }
            }
        }
    }
    static void encrypt(StringBuilder text, char[][] matrix) {
        int[] pos = new int[4];
        for (int i = 0; i < text.length(); i += 2) {
            findPosition(matrix, text.charAt(i), text.charAt(i + 1), pos);
            if (pos[0] == pos[2]) { 
                text.setCharAt(i, matrix[pos[0]][(pos[1] + 1) % 5]);
                text.setCharAt(i + 1, matrix[pos[2]][(pos[3] + 1) % 5]);
            } else if (pos[1] == pos[3]) { 
                text.setCharAt(i, matrix[(pos[0] + 1) % 5][pos[1]]);
                text.setCharAt(i + 1, matrix[(pos[2] + 1) % 5][pos[1]]);
            } else { 
                text.setCharAt(i, matrix[pos[0]][pos[3]]);
                text.setCharAt(i + 1, matrix[pos[2]][pos[1]]);
            }
        }
    }
    static void decrypt(StringBuilder text, char[][] matrix) {
        int[] pos = new int[4];
        for (int i = 0; i < text.length(); i += 2) {
            findPosition(matrix, text.charAt(i), text.charAt(i + 1), pos);
            if (pos[0] == pos[2]) { 
                text.setCharAt(i, matrix[pos[0]][(pos[1] + 4) % 5]);
                text.setCharAt(i + 1, matrix[pos[2]][(pos[3] + 4) % 5]);
            } else if (pos[1] == pos[3]) { 
                text.setCharAt(i, matrix[(pos[0] + 4) % 5][pos[1]]);
                text.setCharAt(i + 1, matrix[(pos[2] + 4) % 5][pos[1]]);
            } else {
                text.setCharAt(i, matrix[pos[0]][pos[3]]);
                text.setCharAt(i + 1, matrix[pos[2]][pos[1]]);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the key : ");
        StringBuilder key = new StringBuilder(sc.nextLine());
        System.out.print("Enter the plaintext : ");
        StringBuilder plaintext = new StringBuilder(sc.nextLine());
        toLowerCase(key);
        removeSpaces(key);
        replaceJ(key);
        toLowerCase(plaintext);
        removeSpaces(plaintext);
        replaceJ(plaintext);
        processInput(plaintext);
        char[][] matrix = new char[5][5];
        generateKeyMatrix(key, matrix);
        printMatrix(matrix);
        StringBuilder cipherText = new StringBuilder(plaintext.toString());
        encrypt(cipherText, matrix);
        System.out.println("Encrypted Text : " + cipherText);
        StringBuilder decrypted = new StringBuilder(cipherText.toString());
        decrypt(decrypted, matrix);
        System.out.println("Decrypted Text : " + decrypted);
    }
}