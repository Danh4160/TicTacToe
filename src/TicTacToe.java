/**
 * TicTacToe Game: Player wins by forming a horizontal, vertical or diagonal line of uninterrupted symbols.
 * TiTacToe Class is a blueprint for each possible game.
 *
 * @author Dan Hosi following the implementation of TUTORIAL 1 from COMP 250 Course by SASHA ALESHCHENKO
 */

public class TicTacToe {

    // Non-Static Fields (instance variables created when TicTacToe object is instantiated)
    Boolean[][] board;      // Empty board
    Boolean pastPlayerMove;     // To track the player in order to set turns

    // Static Fields (Class variables shared by all TicTacToe objects or games).
    static String gameName = "TicTacToe";

    /**
     * Instantiates a TicTacToe board.
     * @param n
     */
    public TicTacToe(int n) {
        board = new Boolean[n][n];
    }

    /**
     * Checks for any illegal actions made by the players and lets the players "play".
     *
     * @param xCoord
     * @param yCoord
     * @param playerMove
     * @return
     */
    public String play(int xCoord, int yCoord, Boolean playerMove) {
        // Check if players' action is outside of the board.
        if (xCoord > board.length || yCoord > board[0].length) {
            throw new IllegalArgumentException("You're playing outside of the board.");
        }
        // Check if a "box" has already an "X" or "O".
        if (board[xCoord][yCoord] != null) {
            throw new IllegalArgumentException("A move has already been entered in this position.");
        }
        // Check if the players' didn't play twice in a row.
        if (pastPlayerMove != null && pastPlayerMove.equals(playerMove)) {
            throw new IllegalArgumentException("You cannot play twice in a row.");
        }

        board[xCoord][yCoord] = playerMove;

        return checkWinConditions();
    }

    /**
     * Checks the win conditions for both players.
     *
     * @return
     */
    private String checkWinConditions() {
        Boolean winner = null;
        if (horizontalWinner(board) != null) {
            winner = horizontalWinner(board);
        }
        if (verticalWinner(board) != null) {
            winner = verticalWinner(board);
        }
        if (diagonalWinner(board) != null) {
            winner = diagonalWinner(board);
        }
        if (winner != null) {
            board = new Boolean[board.length][board.length];
            if (winner) {
                return "X wins!";
            } else {
                return "O wins!";
            }
        } else {
            return "It's a tie!";
        }
    }

    /**
     * Looks for a horizontal where all elements are true
     *
     * @param board
     * @return true if there's one, otherwise false
     */
    static Boolean horizontalWinner(Boolean[][] board) {
        // My Solution which is bad because I only check for moves of type true not type false
//        int countTrue = 0;
//        for (int row = 0; row < board.length; row++) {
//            for (int col = 0; col <board[0].length; col++) {
//                if (board[row][col] = true) countTrue++;
//            }
//        }
//        if (countTrue == board.length) return true;
//        else return null;
        // TA's Solution
        for (Boolean[] row : board) {
            boolean rowAllEqual = true;
            if (row[0] != null) {
                for (int i = 1; i < row.length; i++) {
                    // This checks if our assumption was right.
                    if (!row[i - 1].equals(row[i])) {
                        rowAllEqual = false;
                        break;
                    }
                }
                if (rowAllEqual) return row[0];
            } else {
                rowAllEqual = false;
            }
        }
        return null;
    }

    /**
     * Looks for diagonal Wins
     * @param board
     * @return true if there's a diagonal winner
     */
    public static Boolean diagonalWinner(Boolean[][] board) {
        Boolean firstCorner = board[0][0];
        boolean hasWonFirst = true;

        if (firstCorner != null) {
            for (int i = 0; i < board.length; i++) {
                if(!firstCorner.equals(board[i][i])) {
                    hasWonFirst = false;
                    break;
                }
            } if (hasWonFirst) return firstCorner;
        }

        Boolean secondCorner = board[0][0];
        boolean hasWonSecond = true;

        if (secondCorner != null) {
            for (int i = 0; i < board.length; i++) {
                if(!secondCorner.equals(board[i][i])) {
                    hasWonSecond = false;
                    break;
                }
            } if (hasWonSecond) return firstCorner;
        }
        return null;
    }

    /**
     * Looks for vertical wins
     * @param board
     * @return true if there's a vertical winner
     */
    public static Boolean verticalWinner(Boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            // Our assumption is that the elements of the columns are all equal
            boolean columnAllEqual = true;
            Boolean firstInCol = board[0][i];
            // This checks if the column has the potential to be all equal
            if (firstInCol == null) continue;

            for (int j = 1; j < board.length; j++) {
                if (!firstInCol.equals(board[j][i])) {
                    columnAllEqual = false;
                    break;
                }
            }
            if (columnAllEqual) {
                return firstInCol;
            }
        }
        return null;
    }
}
