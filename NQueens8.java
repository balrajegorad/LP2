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
        List<List<String>> ans = nQueens.solveNQueens(4); // Change to solve for 8x8 board

        for (List<String> row : ans) {
            for (String str : row)
                System.out.println(str);
            System.out.println();
        }
    }
}

