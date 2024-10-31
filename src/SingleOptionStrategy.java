import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SingleOptionStrategy {

    /**
     * Returns a list of empty cells (0's) that have only one available number.
     *
     * @param board The Sudoku board to check for single-option cells.
     * @return A list of coordinates with single available numbers.
     */
    public List<String> getSingleOptionCells(int[][] board) {
        List<String> singleOptionCells = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    HashSet<Integer> occupied = new HashSet<>();

                    // Row check
                    for (int col = 0; col < board[i].length; col++) {
                        if (board[i][col] != 0) {
                            occupied.add(board[i][col]);
                        }
                    }

                    // Column check
                    for (int row = 0; row < board.length; row++) {
                        if (board[row][j] != 0) {
                            occupied.add(board[row][j]);
                        }
                    }

                    // 3x3 sub-grid check
                    int boxRowStart = (i / 3) * 3;
                    int boxColStart = (j / 3) * 3;
                    for (int row = boxRowStart; row < boxRowStart + 3; row++) {
                        for (int col = boxColStart; col < boxColStart + 3; col++) {
                            if (board[row][col] != 0) {
                                occupied.add(board[row][col]);
                            }
                        }
                    }

                    // Find available numbers for this cell
                    List<Integer> availableNumbers = new ArrayList<>();
                    for (int num = 1; num <= 9; num++) {
                        if (!occupied.contains(num)) {
                            availableNumbers.add(num);
                        }
                    }

                    // If only one available number, add it to the result list
                    if (availableNumbers.size() == 1) {
                        int singleOption = availableNumbers.get(0);
                        singleOptionCells.add("Cell (" + i + ", " + j + ") - Available: " + singleOption);
                    }
                }
            }
        }

        return singleOptionCells;
    }

    /**
     * Solves the Sudoku board using a single-option strategy.
     *
     * @param board The Sudoku board to solve.
     * @return True if the board is solved, false otherwise.
     */
    public boolean solveSudoku(int[][] board) {
        boolean solved = false;  // To track if we've made progress

        while (true) {
            List<String> singleOptionCells = getSingleOptionCells(board);

            // If there are no more single-option cells, we break out of the loop
            if (singleOptionCells.isEmpty()) {
                break;
            }

            // Update the board based on single-option cells
            for (String cellInfo : singleOptionCells) {
                // Extract coordinates and the single available number
                String[] parts = cellInfo.split(" - ");
                String[] coords = parts[0].replace("Cell (", "").replace(")", "").split(", ");
                int row = Integer.parseInt(coords[0]);
                int col = Integer.parseInt(coords[1]);

                // Extract the number from the available number string
                int number = Integer.parseInt(parts[1].trim().split(": ")[1]);

                // Update the board
                board[row][col] = number;
                solved = true; // We made a change
            }
        }

        // Check if the board is completely solved
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false; // There are still empty cells
                }
            }
        }

        return true; // The board is completely solved
    }
}
