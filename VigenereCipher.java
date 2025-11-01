import java.util.Scanner;
class VigenereCipher {
    static String generateKey(String str, String key) {
        int x = str.length();
        for (int i = 0; ; i++) {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key += (key.charAt(i));
        }
        return key;
    }
    static String cipherText(String str, String key) {
        String cipher_text = "";

        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (Character.isAlphabetic(currentChar)) {
                int x = (str.charAt(i) + key.charAt(j)) % 26;
                x += 'A';
                cipher_text += (char) (x);
                j++;
            } else {
                cipher_text += currentChar;
            }
        }
        return cipher_text;
    }
    static String originalText(String cipher_text, String key) {
        String orig_text = "";
        int j = 0;
        for (int i = 0; i < cipher_text.length(); i++) {
            char currentChar = cipher_text.charAt(i);
            if (Character.isAlphabetic(currentChar)) {
                int x = (cipher_text.charAt(i) - key.charAt(j) + 26) % 26;
                x += 'A';
                orig_text += (char) (x);
                j++;
            } else {
                orig_text += currentChar;
            }
        }
        return orig_text;
    }
    static String LowerToUpper(String s) {
        StringBuffer str = new StringBuffer(s);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
            }
        }
        return str.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text to encrypt: ");
        String str = sc.nextLine();
        System.out.print("Enter the keyword: ");
        String keyword = sc.nextLine();
        str = LowerToUpper(str);
        keyword = LowerToUpper(keyword);
        String key = generateKey(str, keyword);
        String cipher_text = cipherText(str, key);
        System.out.println("\nCiphertext: " + cipher_text);
        String originalText = originalText(cipher_text, key);
        System.out.println("Original/Decrypted Text: " + originalText);
        sc.close();
    }
}