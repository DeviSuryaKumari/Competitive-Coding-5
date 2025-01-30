// Approach: We use a brute-force approach, checking each cell individually to determine if it belongs to a valid row, column,
// and sub-box. An array of size n is used to track whether a number has already appeared in the current row, column, or sub-box.
// Time Complexity: O(n^3) where n - #rows. Can be reduced to O(n^2) using hashset but uses O(n^2) extra space.
// Space Complexity: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// https://leetcode.com/problems/valid-sudoku/description/
// https://www.geeksforgeeks.org/check-if-given-sudoku-board-configuration-is-valid-or-not/
// https://www.geeksforgeeks.org/java-program-to-convert-char-to-int/

import java.util.Set;
import java.util.HashSet;

public class ValidSudoku {

    boolean isValidRow(char[][] board, int row) {
        boolean[] present = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == '.') {
                continue;
            }
            if (present[board[row][i] - '0' - 1]) {
                return false;
            }
            present[board[row][i] - '0' - 1] = true;
        }
        return true;
    }

    boolean isValidCol(char[][] board, int col) {
        boolean[] present = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == '.') {
                continue;
            }
            if (present[board[i][col] - '0' - 1]) {
                return false;
            }
            present[board[i][col] - '0' - 1] = true;
        }
        return true;
    }

    boolean isValidBox(char[][] board, int rowStart, int colStart) {
        boolean[] present = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + rowStart][j + colStart] == '.') {
                    continue;
                }
                if (present[board[i + rowStart][j + colStart] - '0' - 1]) {
                    return false;
                }
                present[board[i + rowStart][j + colStart] - '0' - 1] = true;
            }
        }
        return true;
    }

    boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!isValidRow(board, i) || !isValidCol(board, j) || !isValidBox(board, i - i % 3, j - j % 3)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean validateSudokuBoardUsingHashSet(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (rows[i].contains(board[i][j])) {
                    return false;
                }
                rows[i].add(board[i][j]);

                if (cols[j].contains(board[i][j])) {
                    return false;
                }
                cols[j].add(board[i][j]);

                int boxIdx = (i / 3) * 3 + (j / 3);
                if (boxes[boxIdx].contains(board[i][j])) {
                    return false;
                }
                boxes[boxIdx].add(board[i][j]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidSudoku vs = new ValidSudoku();
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' },
        };
        System.out.println("Given sudoku board configuration is valid (true/false): " + vs.isValidSudoku(board));
    }
}