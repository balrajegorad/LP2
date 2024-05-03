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
        System.out.println("Encrypte message: " + new String(encrypted));
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted message: " + new String(decrypted));
    }
}


/*

enter string
balu
Original message: balu
Encrypte message: D/?h4?d~??|??
?♦e*?dI7?K?*??↔|▬??f??B???#T=????m??t7Q?aDY?U▲??X???&???V??
(????♦▼???$^I?5?
Decrypted message: balu
PS C:\Desktop\Final programs AI IS - Copy> 

/*


/*


This Java program implements the RSA (Rivest-Shamir-Adleman) encryption algorithm, which is a widely used cryptographic algorithm for secure communication over an insecure channel. Here's a simple explanation of how the code works:

The RSA class defines a constructor to generate public and private keys based on a given bit length. It uses two large prime numbers p and q to calculate the modulus n (product of p and q) and the totient phi of n.
The publicKey is set to a common value (65537), which is a typical choice for the public key exponent in RSA.
The privateKey is calculated as the modular inverse of the publicKey modulo phi.
The encrypt method takes a byte array representing the message to be encrypted. It converts the byte array to a BigInteger, raises it to the power of the publicKey modulo modulus, and returns the result as a byte array.
The decrypt method takes a byte array representing the encrypted message. It converts the byte array to a BigInteger, raises it to the power of the privateKey modulo modulus, and returns the result as a byte array.
In the main method, the user is prompted to enter a string message.
An instance of the RSA class is created with a bit length of 1024.
The original message is encrypted using the encrypt method, and the encrypted message is printed.
The encrypted message is decrypted using the decrypt method, and the decrypted message is printed.
Overall, this code demonstrates how RSA encryption and decryption work using public and private keys. It showcases the process of encrypting a message with the public key and decrypting it with the corresponding private key to recover the original message.

*/
