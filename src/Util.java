import java.util.HashSet;

public class Util {

    /**
     * Prints the Sudoku board in a readable format.
     *
     * @param board The Sudoku board to print.
     */
    public void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints the Sudoku board with grid lines separating the 3x3 sub-grids.
     *
     * @param board The Sudoku board to print.
     */
    public void printBoardWithGrid(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }

            for (int j = 0; j < board[i].length; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints the coordinates of empty cells (0's) in the Sudoku board.
     *
     * @param board The Sudoku board to check for empty cells.
     */
    public void printZeroCoordinates(int[][] board) {
        System.out.println("Coordinates of empty cells (0's):");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }
    }

    /**
     * Prints the occupied numbers in the row and column for each empty cell.
     *
     * @param board The Sudoku board to check for occupied numbers.
     */
    public void printZeroCoordinatesWithOccupiedNumbers(int[][] board) {
        System.out.println("Empty cells (0's) and occupied numbers in their row and column:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    System.out.print("Cell (" + i + ", " + j + ") - ");

                    // Get occupied numbers in the row
                    System.out.print("Row: { ");
                    for (int col = 0; col < board[i].length; col++) {
                        if (board[i][col] != 0) {
                            System.out.print(board[i][col] + " ");
                        }
                    }
                    System.out.print("} ");

                    // Get occupied numbers in the column
                    System.out.print("Column: { ");
                    for (int row = 0; row < board.length; row++) {
                        if (board[row][j] != 0) {
                            System.out.print(board[row][j] + " ");
                        }
                    }
                    System.out.println("}");
                }
            }
        }
    }

    /**
     * Prints the available numbers for each empty cell (0's) in the Sudoku board.
     *
     * @param board The Sudoku board to check for available numbers.
     */
    public void printAvailableNumbersForZeros(int[][] board) {
        System.out.println("Available numbers for empty cells (0's):");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    // Track occupied numbers in the row, column, and 3x3 sub-grid
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

                    // Calculate and display available numbers
                    System.out.print("Cell (" + i + ", " + j + ") - Available: { ");
                    for (int num = 1; num <= 9; num++) {
                        if (!occupied.contains(num)) {
                            System.out.print(num + " ");
                        }
                    }
                    System.out.println("}");
                }
            }
        }
    }

}
