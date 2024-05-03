import java.util.Scanner;

public class RailFenceCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Input message:");
        String name = scanner.next();
        System.out.println("Enter Number  of Rails:");
        int key = scanner.nextInt();
        int len = name.length();
        char[][] matrix = new char[30][30];
        
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = '*';
            }
        }
        
        int k = 0, l = 0, temp = 0, flag = 0;
        for (int i = 0; i < len; i++) {
            flag = 0;
            temp = 0;

            if (k == 0 || k == key - 1) {
                k = k * (-1);
            }

            if (k < 0) {
                temp = k;
                k = k * (-1);
                flag = 1;
            }
            matrix[k][i] = name.charAt(l);
            if (flag == 1) {
                k = temp;
            }
            l++;
            k++;
        }
        
        System.out.println("\nRailfence Matrix is: ");
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }

        char[] str = new char[100];
        int glo = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                if (matrix[i][j] != '*') {
                    str[glo++] = matrix[i][j];
                }
            }
        }

        System.out.println("\nOriginal message : ");
        System.out.println(name);
        System.out.println("\nEncrypted message : ");
        System.out.println(str);

        char[] dstr = new char[100];
        int glob = 0;
        System.out.println("\nDecrypted message : ");
        int p = 0, temp1 = 0;
        for (int i = 0; i < len; i++) {
            temp1 = 0;
            flag = 0;
            if (p == 0 || p == key - 1) {
                p = p * (-1);
            }
            if (p < 0) {
                temp1 = p;
                p = p * (-1);
                flag = 1;
            }
            char ct = matrix[p][i];
            dstr[glob++] = ct;

            if (flag == 1) {
                p = temp1;
            }
            p++;
        }
        for (int i = 0; i < len; i++) {
            System.out.print(dstr[i]);
        }
        System.out.println();
        scanner.close();
    }
}


// Enter Input message:
// girlings
// Enter Number  of Rails:
// 3

// Railfence Matrix is: 
//  g * * * i * * *
//  * i * l * n * s
//  * * r * * * g *

// Original message :
// girlings

// Encrypted message :
// giilnsrg

// Decrypted message :
// girlings
// PS C:\My Files\Final programs AI IS - Copy> 


/*


This Java program implements the Rail Fence Cipher encryption and decryption technique. Here's how the code works:

The program prompts the user to enter a message and the number of rails (rows) for the Rail Fence Cipher.
It initializes a 2D character array called matrix to represent the Rail Fence matrix with a maximum size of 30x30.
The program fills the matrix with asterisks ('*') to mark empty cells.
It then iterates through each character of the input message and assigns it to the appropriate position in the Rail Fence matrix according to the encryption algorithm.
The encryption algorithm involves moving diagonally up and down the rails and placing characters accordingly.
It handles the zigzag pattern by adjusting the direction of movement (up or down) whenever reaching the top or bottom rail.
After filling the Rail Fence matrix, the program prints the matrix to display the encrypted message in a zigzag pattern.
It then reconstructs the encrypted message from the Rail Fence matrix by traversing through the matrix row by row and collecting non-asterisk characters.
The program prints the original message and the encrypted message.
For decryption, the program follows a similar process by reconstructing the Rail Fence matrix and extracting characters row by row to decrypt the message.
Finally, the program prints the decrypted message.
In summary, this code demonstrates the encryption and decryption process of the Rail Fence Cipher using a simple algorithm based on zigzag traversal of a matrix.


*/