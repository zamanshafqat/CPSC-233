import java.util.Arrays;
/**
 * Spent multiple weeks to dessgin this program with love!!!
 * CPSC 233 W24 Assignment 1
 * @name Malik zaman Dogar
 * @email zaman.dogar@ucalgary.ca
 * @version 1.0
 */
public class Board {

    /**
     * No piece in board (empty)
     */
    public static final int EMP = Game.EMP;
    /**
     * Connect-L Red Piece
     */
    public static final int RED = Game.RED;
    /**
     * Connect-L Blue Piece
     */
    public static final int BLU = Game.BLU;


    public static boolean isGameOver(int[][] board, int length) {
        return full(board) || won(board, RED, length) || won(board, BLU, length);
    }


    //Students should enter their functions below here

    public static int[][] createBoard(int rows, int columns)
            // in this function we create a matrix with zeros
    {
        int [][] board1;
        board1 = new int[rows][columns];
        return board1;
    }
    public static int rowCount(int[][] board) {
        // this function return rows
        int rows;
        rows = board.length;
        return rows;
    }

    public static int columnCount(int[][] board) {
        // this function return columns
        int cols;
        cols = board[0].length;
        return  cols;

    }

    public static boolean valid(int[][]board,int row,int col)
            // this function make sure certain row and column are in bound of board
    {
        if (row < 0 || col < 0 || row  >= rowCount(board) || col  >= columnCount(board)) {
            return false;
        }
        return true;
    }
    public static boolean canPlay(int[][] board, int col) {
        // this function loops around one row to find if you can play and return the row number
        int row = board.length;
        boolean canPlay = false;
        for (int i= 0 ;i<row-1; i++) {
            if (board[i][col] == 0) {
                canPlay = true;
                return canPlay;
            }
        }
        return canPlay;
    }

    public static int play(int[][] board, int col, int piece) {
        // this function loops around row from bottom and places piece in the first empty row found
        int row;
        int z;
        row = board.length;
        for (z = row - 1; z >= 0; z = z - 1) {
            if (board[z][col] == 0) {
                board[z][col] = piece;
                return z;
            }
        }
        return -1;
    }

    public static int removeLastPlay(int[][] board, int col) {
        // this function removes last play from a row
        int row = board.length;
        for(int i = 0;i <= row-1 ;i++){
            if (board[i][col] != 0) {
                board[i][col] = 0;
                return i;
            }
        }
        return -1;
    }
    public static boolean full(int[][] board) {
        // this function used 2d array to find if board is full or not
        int col =columnCount(board);
        int row = rowCount(board);
        boolean full = true;
        for(int i =0;i <= row-1;i++){
            for(int z =0; z <= col-1;z++){
                if(board[i][z]==0) {
                    full = false;
                    return full;
                }
            }

        }
        return full;

    }
    public static boolean winInRow(int[][] board, int row, int piece, int length) {
        int seriesOfPieces = 0;
        int z = 0;
        int col = columnCount(board);
        int start_seriesOfPieces = 0;
        int condition_true_1 = 0;
        boolean L_VALID = false;
        do {
            if (z + 1 < col && board[row][z] == piece && board[row][z + 1] == piece) {
                seriesOfPieces++;
                condition_true_1++;
                if(condition_true_1 == 1){
                    start_seriesOfPieces = z;
                }
                // CASES EXCLUDING ROW IS ZERO OR MAX
                if (seriesOfPieces == length - 1 && row-1>= 0 && row+1 <= rowCount(board) -1 ) {
                    if (board[row+1][start_seriesOfPieces]==piece){
                        L_VALID = true;
                    }
                    if (board[row-1][start_seriesOfPieces]==piece){
                        L_VALID = true;
                    }
                    if (board[row+1][start_seriesOfPieces+seriesOfPieces]==piece){
                        L_VALID = true;
                    }
                    if (board[row-1][start_seriesOfPieces+seriesOfPieces]==piece){
                        L_VALID = true;
                    }
                }
                // CASE WHERE ROW IS EQUAL TO ZERO OR MAX
                if (seriesOfPieces == length - 1 && row == 0 ) {
                    if (board[row + 1][start_seriesOfPieces] == piece) {
                        L_VALID = true;
                    }
                    if (board[row + 1][start_seriesOfPieces + seriesOfPieces] == piece) {
                        L_VALID = true;
                    }
                }
                if (seriesOfPieces == length -1 && row+1 == rowCount(board)){
                    if (board[row - 1][start_seriesOfPieces]==piece) {
                        L_VALID = true;
                    }
                    if (board[row - 1][start_seriesOfPieces+seriesOfPieces] == piece) {
                        L_VALID = true;
                    }
                }

            }
            else {
                seriesOfPieces = 0;
                condition_true_1=0;
            }
            z++;
        } while (z <= col);


        return L_VALID;
    }

    public static boolean winInColumn(int[][] board, int col, int piece, int length) {
        int seriesOfPieces = 0;
        int z = 0;
        int row = rowCount(board);
        int start_seriesOfPieces = 0;
        int condition_true_1 = 0;
        boolean L_VALID = false;
        do {
            if (z + 1 < row && board[z][col] == piece && board[z+1][col] == piece) {
                seriesOfPieces++;
                condition_true_1++;
                if(condition_true_1 == 1){
                    start_seriesOfPieces = z;
                }
                // CASES EXCLUDING COL IS ZERO OR MAX
                if (seriesOfPieces == length - 1 && col-1 >= 0 && col+1 <= columnCount(board) - 1) {
                    if (board[start_seriesOfPieces][col + 1]==piece){
                        L_VALID = true;
                    }
                    if (board[start_seriesOfPieces][col - 1]==piece){
                        L_VALID = true;
                    }
                    if (board[start_seriesOfPieces+seriesOfPieces][col + 1] == piece){
                        L_VALID = true;
                    }
                    if (board[start_seriesOfPieces+seriesOfPieces][col - 1]==piece){
                        L_VALID = true;
                    }
                }
                // CASE WHERE COL IS EQUAL TO ZERO OR MAX
                if (seriesOfPieces == length - 1 && col-1 == -1 ) {
                    if (board[start_seriesOfPieces][col + 1] == piece) {
                        L_VALID = true;
                    }
                    if (board[start_seriesOfPieces + seriesOfPieces][col + 1] == piece) {
                        L_VALID = true;
                    }
                }
                if (seriesOfPieces == length -1 && col+1 == columnCount(board)){
                    if (board[start_seriesOfPieces][col - 1]==piece) {
                        L_VALID = true;
                    }
                    if (board[start_seriesOfPieces+seriesOfPieces][col - 1] == piece) {
                        L_VALID = true;
                    }
                }
            }
            else {
                seriesOfPieces = 0;
                condition_true_1=0;
            }
            z++;
        } while (z <= row);


        return L_VALID;
    }
    //        \ backslash
    public static boolean winInDiagonalBackslash(int[][] board, int piece, int length) {
        // this code only works for the \ its doesnt work for the connecting perpendicular L

        int row = rowCount(board);
        int col = columnCount(board);
        int startRow = 0;
        int startCol = 0;


        for (int i = 0; i < row - length + 1 ; i++) {
            // CHECKING IN DIAGONAL STOPS WHEN \ ROWS < LENGTH
            for (int z = length -1; z < col; z++) {
                int seriesOfPieces = 0;
                for (int k = 0; k < length; k++) {
                    if (board[i + k][z - k] == piece) {
                        seriesOfPieces++;
                        startCol = z - k;
                        startRow = i + k;
                    }
                }
                if (seriesOfPieces == length ) {
                    return true;
                }
            }
        }

        return false;  // No win found here
    }



    public static boolean winInDiagonalForwardSlash(int[][] board, int piece, int length) {
        int row = rowCount(board);
        int col = columnCount(board);
        int startRow = 0;
        int startCol = 0;

        // this code only works for the / its doesnt work for the connecting perpendicular L


        for (int i = 0; i < row - length + 1 ; i++) {
            // CHECKING IN DIAGONAL STOPS WHEN / ROWS < LENGTH
            for (int z = 0; z < col - length + 1; z++) {
                int seriesOfPieces = 0;
                for (int k = 0; k < length; k++) {
                    if (board[i + k][z + k] == piece) {
                        seriesOfPieces++;
                        startCol = z - k;
                        startRow = i + k;
                    }
                }
                if (seriesOfPieces == length ) {
                    return true;
                }
            }
        }

        return false; // no win found here
    }

    public static int[] hint(int[][] board, int piece, int length) {
        int[] hint ={-1,-1};
        int row = 0;
        int row_removed = 0;
        int col = columnCount(board);
        for (int z = 0; z < col ; z++) {
            row = play(board,z,piece);
            if (winInRow(board, row, piece, length)) {
                // remove last play
                row_removed = removeLastPlay(board, z);
                hint[0] = row_removed;
                hint[1] = z;
                return hint;
            } else if (winInColumn(board, z, piece, length)) {
                // remove last play
                removeLastPlay(board, z);
                hint[0] = row;
                hint[1] = z;
                return hint;
            } else if (winInDiagonalBackslash(board, piece, length)) {
                // remove last play
                removeLastPlay(board, z);
                hint[0] = row;
                hint[1] = z;
                return hint;
            } else if (winInDiagonalForwardSlash(board, piece, length)) {
                // remove last play
                removeLastPlay(board, z);
                hint[0] = row;
                hint[1] = z;
                return hint;
            } else {
                removeLastPlay(board, z);
            }
        }

        return hint;
    }



    //Students should enter their functions above here
    /**
     * Is there a win in given board in any row of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any row
     * @return True if there is length in any row, False otherwise
     */
    private static boolean winInAnyRow(int[][] board, int piece, int length) {
        for (int row = 0; row < board.length; row++) {
            if (winInRow(board, row, piece, length)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Is there a win in given board in any column of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any column
     * @return True if there is length in any column, False otherwise
     */
    private static boolean winInAnyColumn(int[][] board, int piece, int length) {
        for (int col = 0; col < board[0].length; col++) {
            if (winInColumn(board, col, piece, length)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Is there a win in given board in any diagonal of board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to look for length in a row for any diagonal
     * @return True if there is length in any diagonal /\, False otherwise
     */
    private static boolean winInAnyDiagonal(int[][] board, int piece, int length) {
        return winInDiagonalBackslash(board, piece, length) || winInDiagonalForwardSlash(board, piece, length);
    }


    /**
     * Has the given piece won the board
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @param piece The piece to check for a win
     * @return True if piece has won
     */
    public static boolean won(int[][] board, int piece, int length) {
        return winInAnyRow(board, piece, length) || winInAnyColumn(board, piece, length) || winInAnyDiagonal(board, piece, length);
    }

    /**
     * This function determines if the game is complete due to a win or tie by either player
     *
     * @param board The 2D array board of size rows (dimension 1) and columns (dimension 2)
     * @return True if game is complete, False otherwise
     */

}
