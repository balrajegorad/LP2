import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public class RSA {

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    // Constructor to generate public and private keys
    public RSA(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, random);
        BigInteger q = new BigInteger(bitLength / 2, 100, random);
        modulus = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        publicKey = new BigInteger("65537"); // Common public key exponent
        privateKey = publicKey.modInverse(phi);
    }

    // Encryption
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(publicKey, modulus).toByteArray();
    }

    // Decryption
    public byte[] decrypt(byte[] encryptedMessage) {
        return (new BigInteger(encryptedMessage)).modPow(privateKey, modulus).toByteArray();
    }

    public static void main(String[] args) {
        System.out.println("enter string");
        Scanner sc=new Scanner(System.in);
        String message = sc.nextLine();
        RSA rsa = new RSA(1024);
        // String message = "Hello, RSA!";
        System.out.println("Original message: " + message);
        byte[] encrypted = rsa.encrypt(message.getBytes());
        System.out.println("Encrypted message: " + new String(encrypted));
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted message: " + new String(decrypted));
    }
}
