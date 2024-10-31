/**
 * A test class for measuring the performance of a SudokuSolver.
 */
public class TestClass {

    private static int numberOfBoards;
    private static final SudokuBoardGenerator.Difficulty EASY = SudokuBoardGenerator.Difficulty.EASY;
    private static final SudokuBoardGenerator.Difficulty MEDIUM = SudokuBoardGenerator.Difficulty.MEDIUM;
    private static final SudokuBoardGenerator.Difficulty HARD = SudokuBoardGenerator.Difficulty.HARD;

    private SudokuBoardGenerator boardGenerator;
    private SingleOptionStrategy solver;

    public TestClass(SingleOptionStrategy solver, int numberOfBoards) {
        this.numberOfBoards = numberOfBoards;
        this.solver = solver;
        this.boardGenerator = new SudokuBoardGenerator();
    }

    public void runTests() {
        System.out.println("Testing Sudoku Solver...");

        // Test for each difficulty level
        testDifficulty(EASY, "Easy");
        testDifficulty(MEDIUM, "Medium");
        testDifficulty(HARD, "Hard");
    }

    private void testDifficulty(SudokuBoardGenerator.Difficulty difficulty, String difficultyName) {
        int solvedCount = 0;
        long totalTime = 0;
        long lowestTime = Long.MAX_VALUE;
        long highestTime = Long.MIN_VALUE;

        for (int i = 0; i < numberOfBoards; i++) {
            // Generate a new Sudoku board for each test
            int[][] board = boardGenerator.generateSudokuBoard(difficulty);

            // Clear cache if applicable (placeholder for actual cache clearing if needed)
            System.gc(); // Suggest to the JVM to perform garbage collection

            long startTime = System.nanoTime();
            boolean solved = solver.solveSudoku(board);
            long endTime = System.nanoTime();

            if (solved) {
                long duration = endTime - startTime;
                totalTime += duration;

                // Update solved count
                solvedCount++;

                // Update lowest and highest times
                if (duration < lowestTime) {
                    lowestTime = duration;
                }

                if (duration > highestTime) {
                    highestTime = duration;
                }
            }
        }

        // Calculate average time only for solved boards
        double averageTime = solvedCount > 0 ? (double) totalTime / solvedCount : 0;
        double solvedPercentage = ((double) solvedCount / numberOfBoards) * 100;

        System.out.printf("%s Difficulty: %d solved out of %d boards (%.2f%% solved).\n",
                difficultyName, solvedCount, numberOfBoards, solvedPercentage);

        // Only print timing stats if at least one board was solved
        if (solvedCount > 0) {
            System.out.printf("Average time for solved boards: %.2f ms, Lowest time: %.2f ms, Highest time: %.2f ms\n",
                    averageTime / 1_000_000.0, lowestTime / 1_000_000.0, highestTime / 1_000_000.0);
        } else {
            System.out.println("No boards were solved, so timing statistics are not available.");
        }
    }

    public static void main(String[] args) {
        SingleOptionStrategy solver = new SingleOptionStrategy(); // Replace with your solver instance
        TestClass tester = new TestClass(solver, 1000); // Adjust the number of boards here as needed
        tester.runTests();
    }
}
