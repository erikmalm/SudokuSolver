import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for generating Sudoku boards.
 */
class SudokuBoardGenerator {

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    /**
     * Generates a complete Sudoku board.
     *
     * @return A 9x9 2D array representing the completed Sudoku board.
     */
    public int[][] generateCompletedBoard() {
        int[][] board = new int[9][9];
        fillBoard(board);
        return board;
    }

    /**
     * Fills the Sudoku board with numbers using backtracking.
     *
     * @param board The Sudoku board to fill.
     * @return true if the board is filled correctly, false otherwise.
     */
    private boolean fillBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    List<Integer> numbers = new ArrayList<>();
                    for (int num = 1; num <= 9; num++) {
                        numbers.add(num);
                    }
                    Collections.shuffle(numbers); // Randomize numbers to fill

                    for (int num : numbers) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num; // Place the number
                            if (fillBoard(board)) { // Recur to fill the rest
                                return true;
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No number can be placed, backtrack
                }
            }
        }
        return true; // Board is completely filled
    }

    /**
     * Checks if it's safe to place a number in the specified cell.
     *
     * @param board The Sudoku board.
     * @param row   The row index.
     * @param col   The column index.
     * @param num   The number to check.
     * @return true if it's safe to place the number, false otherwise.
     */
    private boolean isSafe(int[][] board, int row, int col, int num) {
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                    board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a Sudoku puzzle with a specified difficulty level.
     *
     * @param difficulty The difficulty level of the puzzle.
     * @return A 9x9 2D array representing the Sudoku puzzle.
     */
    public int[][] generateSudokuBoard(Difficulty difficulty) {
        int[][] completedBoard = generateCompletedBoard();
        int[][] puzzleBoard = new int[9][9];

        // Copy completed board to puzzle board
        for (int i = 0; i < 9; i++) {
            System.arraycopy(completedBoard[i], 0, puzzleBoard[i], 0, 9);
        }

        // Remove numbers based on difficulty level
        int cellsToRemove = 0;
        switch (difficulty) {
            case EASY:
                cellsToRemove = 36; // Easy level removes fewer cells
                break;
            case MEDIUM:
                cellsToRemove = 45; // Medium level removes a moderate number of cells
                break;
            case HARD:
                cellsToRemove = 54; // Hard level removes more cells
                break;
        }

        // Randomly remove cells
        while (cellsToRemove > 0) {
            int row = (int) (Math.random() * 9);
            int col = (int) (Math.random() * 9);
            if (puzzleBoard[row][col] != 0) {
                puzzleBoard[row][col] = 0; // Remove the number
                cellsToRemove--;
            }
        }

        return puzzleBoard;
    }
}
