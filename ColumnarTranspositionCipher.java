import java.util.*;

public class ColumnarTranspositionCipher {
    // Key for Columnar Transposition
    private static String key;
    private static Map<Character, Integer> keyMap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // message
        System.out.print("Enter the message: ");
        String plainText = scanner.nextLine().toUpperCase();
        System.out.println("Entered message: " + plainText);

        // key
        System.out.print("Enter key: ");
        key = scanner.next().toUpperCase();
        scanner.close();

        setPermutationOrder();

        // Encryption
        String cipherText = encryptMessage(plainText);
        System.out.println("Encrypted Message: " + cipherText);

        // Decryption
        String decryptedMessage = decryptMessage(cipherText);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    // Setting permutation order
    private static void setPermutationOrder() {
        keyMap = new LinkedHashMap<>();
        for (int i = 0; i < key.length(); i++) {
            keyMap.put(key.charAt(i), i);
        }
    }

    // Encryption
    private static String encryptMessage(String msg) {
        int row, col, j;
        StringBuilder cipher = new StringBuilder();

        // calculate column of the matrix
        col = key.length();

        // calculate maximum row of the matrix
        row = (int) Math.ceil((double) msg.length() / col);

        char[][] matrix = new char[row][col];

        for (int i = 0, k = 0; i < row; i++) {
            for (int l = 0; l < col; ) {
                if (k < msg.length() && Character.isLetter(msg.charAt(k))) {
                    matrix[i][l++] = msg.charAt(k++);
                } else {
                    matrix[i][l++] = '_'; // adding the padding character '_'
                }
            }
        }

        // Transpose the matrix
        char[][] transposedMatrix = new char[col][row];
        for (int i = 0; i < row; i++) {
            for (int l = 0; l < col; l++) {
                transposedMatrix[l][i] = matrix[i][l];
            }
        }

        for (Map.Entry<Character, Integer> entry : keyMap.entrySet()) {
            j = entry.getValue();
            for (int i = 0; i < row; i++) {
                if (Character.isLetter(transposedMatrix[j][i]) || transposedMatrix[j][i] == '_') {
                    cipher.append(transposedMatrix[j][i]);
                }
            }
        }

        return cipher.toString();
    }

    // Decryption
    private static String decryptMessage(String cipher) {
        int col = key.length();
        int row = (int) Math.ceil((double) cipher.length() / col);

        char[][] cipherMat = new char[row][col];

        for (int i = 0, k = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cipherMat[i][j] = cipher.charAt(k++);
            }
        }

        char[][] decCipher = new char[row][col];
        int k = 0;
        for (Map.Entry<Character, Integer> entry : keyMap.entrySet()) {
            int j = entry.getValue();
            for (int i = 0; i < row; i++) {
                decCipher[i][j] = cipherMat[i][k];
            }
            k++;
        }

        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (decCipher[i][j] != '_') {
                    msg.append(decCipher[i][j]);
                }
            }
        }
        return msg.toString();
    }
}



// Enter the message: shravan
// Entered message: SHRAVAN
// Enter key: qwe
// Encrypted Message: SANHV_RA_
// Decrypted Message: SANHVRA
// PS C:\My Files\Final programs AI IS - Copy> 
