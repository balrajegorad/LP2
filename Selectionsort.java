import java.util.Arrays;
import java.util.Scanner;

public class Selectionsort {
    public static int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
            System.out.println("Pass " + (i + 1) + ": " + Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the elements of the array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();
        int[] sortedArr = Selectionsort.sort(arr);
        System.out.println("Sorted array is: " + Arrays.toString(sortedArr));
    }
}

// Output

/*
 * Enter the elements of the array: 6
 * Enter the elements of the array:
 * 56
 * 45
 * 12
 * 96
 * 78
 * 56
 * Pass 1: [12, 45, 56, 96, 78, 56]
 * Pass 2: [12, 45, 56, 96, 78, 56]
 * Pass 3: [12, 45, 56, 96, 78, 56]
 * Pass 4: [12, 45, 56, 56, 78, 96]
 * Pass 5: [12, 45, 56, 56, 78, 96]
 * Pass 6: [12, 45, 56, 56, 78, 96]
 * Sorted array is: [12, 45, 56, 56, 78, 96]
 */


/*


This Java program implements the selection sort algorithm to sort an array of integers in ascending order. Here's a simple explanation of how it works:

The program starts by taking input from the user for the elements of the array.
It then iterates through each element of the array and finds the smallest element among the unsorted elements.
Once the smallest element is found, it swaps it with the element at the current position.
This process is repeated for each element in the array until the entire array is sorted.
At each pass of the outer loop, the program prints the current state of the array to visualize the sorting process.
Finally, the sorted array is printed.
Here's a breakdown of the key components:

sort method: This method takes an unsorted array as input and returns the sorted array using the selection sort algorithm. It iterates through the array, finds the smallest element in the unsorted portion, and swaps it with the first unsorted element.
main method: This method is the entry point of the program. It takes input from the user, calls the sort method to sort the array, and then prints the sorted array.
Overall, the selection sort algorithm works by repeatedly finding the smallest element in the unsorted portion of the array and moving it to the beginning of the sorted portion. This process continues until the entire array is sorted.


*/