import java.math.BigInteger;
import java.util.Scanner;
class RSA {
    static BigInteger power(BigInteger base, BigInteger expo, BigInteger m) {
        return base.modPow(expo, m);
    }
    static BigInteger modInverse(BigInteger e, BigInteger phi) {
        return e.modInverse(phi);
    }
    static void generateKeys(BigInteger[] keys, BigInteger p, BigInteger q) {
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.ZERO;
        for (e = new BigInteger("2"); e.compareTo(phi) < 0; e = e.add(BigInteger.ONE)) {
            if (e.gcd(phi).equals(BigInteger.ONE)) {
                break;
            }
        }
        BigInteger d = modInverse(e, phi);
        keys[0] = e;  
        keys[1] = d; 
        keys[2] = n;
        System.out.println("n : " + n);
        System.out.println("phi (Euler's Quotient): " + phi);
        System.out.println("e (Public Exponent): " + e);
        System.out.println("d (Private Exponent): " + d);
    }
    static BigInteger encrypt(BigInteger m, BigInteger e, BigInteger n) {
        return power(m, e, n);
    }
    static BigInteger decrypt(BigInteger c, BigInteger d, BigInteger n) {
        return power(c, d, n);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first prime number (p): ");
        BigInteger p = new BigInteger(sc.nextLine());
        System.out.print("Enter second prime number (q): ");
        BigInteger q = new BigInteger(sc.nextLine());
        BigInteger[] keys = new BigInteger[3];
        generateKeys(keys, p, q);
        System.out.println("Public Key (e, n): (" + keys[0] + ", " + keys[2] + ")");
        System.out.println("Private Key (d, n): (" + keys[1] + ", " + keys[2] + ")");
        System.out.print("Enter the plaintext message (as a number): ");
        BigInteger M = new BigInteger(sc.nextLine());
        BigInteger C = encrypt(M, keys[0], keys[2]);
        System.out.println("Encrypted Message: " + C);
        BigInteger decrypted = decrypt(C, keys[1], keys[2]);
        System.out.println("Decrypted Message: " + decrypted);
        sc.close();
    }
}