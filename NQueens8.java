import java.util.ArrayList;
import java.util.List;

public class NQueens8 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            board.add(sb.toString());
        }
        solve(0, ans, board, n);
        return ans;
    }

    private void solve(int row, List<List<String>> ans, List<String> board, int n) {
        if (row == n) {
            ans.add(new ArrayList<>(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, board, n)) {
                StringBuilder sb = new StringBuilder(board.get(row));
                sb.setCharAt(col, 'Q');
                board.set(row, sb.toString());
                solve(row + 1, ans, board, n);
                sb.setCharAt(col, '.');
                board.set(row, sb.toString());
            }
        }
    }

    private boolean isSafe(int row, int col, List<String> board, int n) {
        // check the column
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q')
                return false;
        }

        // check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }

        // check upper right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        NQueens8 nQueens = new NQueens8();
        List<List<String>> ans = nQueens.solveNQueens(8); // Change to solve for 8x8 board

        for (List<String> row : ans) {
            for (String str : row)
                System.out.println(str);
            System.out.println();
        }
    }
}

/*



This Java program solves the N-Queens problem, which is a classic problem in computer science. The goal is to place N queens on an NxN chessboard in such a way that no two queens threaten each other. In chess, a queen can move horizontally, vertically, and diagonally.

Here's how the program works:

The solveNQueens method initializes an empty chessboard represented as a list of strings (board). Each string represents a row on the chessboard, and initially, all cells are filled with '.' to represent empty cells.
The solve method is a recursive function that tries to place queens on the board row by row. It takes the current row index (row), the list of solutions (ans), the current state of the board (board), and the size of the board (n) as parameters.
The base case of the recursion is when all queens are placed successfully on the board (i.e., row == n). In this case, the current state of the board is added to the list of solutions.
In each recursive call, the method iterates over each column in the current row and tries to place a queen in that column if it's safe to do so. It checks safety by ensuring that no other queen threatens the current position horizontally, vertically, or diagonally.
If placing a queen in the current position is safe, the method updates the board state accordingly and recursively calls itself for the next row (row + 1).
After exploring all possible positions for the current row, the method backtracks by resetting the board state to its previous state before the queen was placed.
In the isSafe method, it checks whether placing a queen at a given row and column is safe by examining the current board state. It checks for threats in the same column, upper left diagonal, and upper right diagonal.
In the main method, an instance of the NQueens8 class is created, and the solveNQueens method is called to solve the N-Queens problem for an 8x8 board (you can change the argument to solve for a different board size). The solutions are then printed to the console.



*/