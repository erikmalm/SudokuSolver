/**
 * A class for solving Sudoku puzzles.
 */
public class SudokuSolver {

    private static final SudokuBoardGenerator.Difficulty EASY_DIFFICULTY = SudokuBoardGenerator.Difficulty.EASY;

    public static void main(String[] args) {
        // Create instances of the solving strategy and utility class
        SingleOptionStrategy singleOptionStrategy = new SingleOptionStrategy();
        SudokuBoardGenerator sudokuBoardGenerator = new SudokuBoardGenerator();



        int[][] board = sudokuBoardGenerator.generateSudokuBoard(EASY_DIFFICULTY);
        Util util = new Util();

        // Example 9x9 grid (0 represents empty cells)
        int[][] exampleBoard = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        // Display the original board
        System.out.println("Original Sudoku Board:");
        util.printBoardWithGrid(board);
        System.out.println("***********************");

        // Measure the time taken to solve the Sudoku puzzle
        long startTime = System.nanoTime();
        if (singleOptionStrategy.solveSudoku(board)) {
            long endTime = System.nanoTime();
            System.out.println("Solved Sudoku Board:");
            util.printBoardWithGrid(board);
            System.out.printf("Time taken to solve: %.2f ms%n", (endTime - startTime) / 1_000_000.0);
        } else {
            System.out.println("The Sudoku puzzle cannot be solved with the current approach.");
        }
    }
}