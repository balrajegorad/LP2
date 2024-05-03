import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DES {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Get plaintext from the user
            System.out.print("Enter the plaintext: ");
            String plainText = scanner.nextLine();

            // Get key from the user
            System.out.print("Enter the 8-byte key for DES: ");
            String keyString = scanner.nextLine();

            byte[] keyData = keyString.getBytes();
            SecretKeySpec key = new SecretKeySpec(keyData, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(plainText.getBytes());
            System.out.println("Encrypted: " + bytesToHex(encryptedData));

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            System.out.println("Decrypted: " + new String(decryptedData));

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}


/*

This Java program demonstrates the use of the DES (Data Encryption Standard) algorithm for encrypting and decrypting data.

Here's a breakdown of how the program works:

Import Statements: The program imports necessary classes from the java.util.Scanner package for user input and from the javax.crypto package for cryptographic operations.
Main Method: The main method is the entry point of the program. It prompts the user to enter plaintext and an 8-byte key for DES encryption.
User Input: The program reads plaintext and the key from the user using the Scanner class.
Key Setup: The entered key is converted to bytes (keyData), and a SecretKeySpec object is created using this key. This object specifies the DES algorithm for encryption.
Cipher Initialization: A Cipher object is created and initialized for encryption using the DES algorithm in ECB mode with PKCS5 padding. ECB (Electronic Codebook) mode is a basic encryption mode that operates on blocks of plaintext independently.
Encryption: The plaintext is encrypted using the initialized cipher object (cipher) and the provided key. The encrypted data is stored in encryptedData.
Display Encrypted Data: The encrypted data is converted to hexadecimal format using the bytesToHex method and printed to the console.
Decryption: To decrypt the encrypted data, the cipher is re-initialized for decryption mode using the same key. The doFinal method decrypts the encrypted data (encryptedData), and the decrypted data is stored in decryptedData.
Display Decrypted Data: The decrypted data is converted back to a string and printed to the console.
Exception Handling: The program uses a try-catch block to handle any exceptions that may occur during the cryptographic operations.
Utility Method (bytesToHex): This method converts a byte array to a hexadecimal string representation. It iterates over each byte in the array and appends its hexadecimal value to a StringBuilder.
Overall, the program demonstrates how to perform encryption and decryption using the DES algorithm in Java.


*/
