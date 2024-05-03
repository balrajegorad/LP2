import java.util.Scanner;

public class StringOperation {
    public static String processString(String string, String operation) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (operation.equals("AND")) {
                result.append((char) (currentChar & 127));
            } else if (operation.equals("XOR")) {
                result.append((char) (currentChar ^ 127));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();

        System.out.println("Input String: " + inputString);

        System.out.print("Choose operation (AND/XOR): ");
        String operation = scanner.nextLine();

        String result = processString(inputString, operation);
        System.out.println("Result: " + result);

        scanner.close();
    }
}

// Enter a string: shravan
// Input String: shravan
// Choose operation (AND/XOR): XOR
// Result: 
// ▲       ▲◄
// PS C:\My Files\Final programs AI IS - Copy> 


// Enter a string: shravan
// Input String: shravan
// Choose operation (AND/XOR): AND
// Result: shravan
// PS C:\My Files\Final programs AI IS - Copy> 



/*Sure! Let's break down the code:

Import Statement: The code begins with an import statement import java.util.Scanner;, which is used to import the Scanner class from the java.util package. This class helps in taking input from the user.
StringOperation Class: This class contains two methods:
processString: This method takes two parameters - a string (string) and an operation (operation). It iterates through each character of the input string and performs a bitwise operation (AND or XOR) based on the operation chosen. The result of the operation is appended to a StringBuilder and returned as a string.
main: This is the entry point of the program. It prompts the user to enter a string and an operation (AND or XOR). Then, it calls the processString method to perform the specified operation on the input string and displays the result.
User Input Handling: The program uses a Scanner object (scanner) to read user input. It prompts the user to enter a string and an operation, and then reads these inputs using the nextLine() method of the Scanner class.
Output: After processing the input string with the chosen operation, the program prints the input string, the chosen operation, and the result of the operation.
Scanner Close: Finally, the program closes the Scanner object to release system resources.
Overall, this program demonstrates how to perform bitwise operations (AND and XOR) on a string based on user input. It provides a simple command-line interface for the user to interact with the program and see the result of the chosen operation on the input string.

/*