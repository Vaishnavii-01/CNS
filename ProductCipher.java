import java.util.Scanner;
public class ProductCipher {

    public static String substitute(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + 3) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    public static String reverseSubstitute(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base - 3 + 26) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    public static char[][] fillMatrixRowWise(String text) {
        char[][] matrix = new char[4][4];
        int index = 0;
        for (int i = 0; i < 4 && index < text.length(); i++) {
            for (int j = 0; j < 4 && index < text.length(); j++) {
                matrix[i][j] = text.charAt(index++);
            }
        }
        while (index < 16) {
            int i = index / 4;
            int j = index % 4;
            matrix[i][j] = 'X';
            index++;
        }
        return matrix;
    }

    public static String readMatrixColumnWise(char[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                result.append(matrix[i][j]);
            }
        }
        return result.toString();
    }

    public static char[][] fillMatrixColumnWise(String text) {
        char[][] matrix = new char[4][4];
        int index = 0;
        for (int j = 0; j < 4 && index < text.length(); j++) {
            for (int i = 0; i < 4 && index < text.length(); i++) {
                matrix[i][j] = text.charAt(index++);
            }
        }
        return matrix;
    }

    public static String readMatrixRowWise(char[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.append(matrix[i][j]);
            }
        }
        return result.toString();
    }

    public static void displayMatrix(char[][] matrix) {
        System.out.println("Matrix :");
        for (char[] row : matrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext (max 16 letters) : ");
        String plaintext = scanner.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase();

        if (plaintext.length() > 16) {
            plaintext = plaintext.substring(0, 16);
        }

        String substituted = substitute(plaintext);
        System.out.println("After Substitution (+3) : " + substituted);

        char[][] matrix = fillMatrixRowWise(substituted);
        displayMatrix(matrix);

        String encrypted = readMatrixColumnWise(matrix);
        System.out.println("Encrypted text (column-wise) : " + encrypted);

        // ---------- Decryption ----------

        char[][] decryptMatrix = fillMatrixColumnWise(encrypted);

        String afterTransposition = readMatrixRowWise(decryptMatrix);

        String decrypted = reverseSubstitute(afterTransposition);
        System.out.println("Decrypted text : " + decrypted);
    }
}