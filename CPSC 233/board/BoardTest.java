import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CPSC 233 W24 Assignment 1 BoardTest Starter File
 * Holds a helper deep copy and example tests of deep copy
 * @name Malik Zaman Dogar
 * @email zaman.dogar@ucalgary.ca
 * @version 1.0
 */
public class BoardTest {
    // CREATE BOARD TESTS-----------------------------------------
        @Test
        void create_board_row_test() {
            int[][] board = Board.createBoard(6, 7);
            assertEquals(6, Board.rowCount(board));
        }

        @Test
        void create_Board_col_test() {
            int[][] board = Board.createBoard(6, 7);
            assertEquals(7, Board.columnCount(board));
        }

        @Test
        void create_board_0_test() {
            int[][] expected = {{0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}};
            assertArrayEquals(expected, Board.createBoard(4, 4));
        }

        @Test
        void create_board_equalNumbersOfColumnsAndRows() {
            int[][] unexpected = {{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0}};
            assertNotEquals(unexpected, Board.createBoard(5, 5));
        }

        @Test
        void create_board_DifferentRowAndColTest() {
            int[][] expected = {{0, 0,},
                    {0, 0,},
                    {0, 0,},
                    {0, 0,},
                    {0, 0,}};
            assertNotEquals(expected, Board.createBoard(4, 2));
        }


    // ROW COUNT TEST------------------------------------------------
    @Test
    void rowCount_sameNumberOfColumnsAndRows() {
        int [][] board = new int [6][6];
        assertEquals(6, Board.rowCount(board));
    }
    @Test
    void rowCount_differentNumberOfColumnsAndRows() {
        int [][] board = new int [5][7];
        assertEquals(5, Board.rowCount(board));
    }
    @Test
    void rowCount_Max_Row(){
            int [][] board = new int [8][4];
            assertEquals(8,Board.rowCount(board));
    }
    @Test
    void rowCount_Min_Row(){
        int [][] board = new int [4][4];
        assertEquals(4,Board.rowCount(board));
    }
    @Test
    void rowCount_Integer_values(){
            int[][] board ={{1, 2,},
                    {2, 3,},
                    {4, 2,},
                    {1, 7,},
                    {2, 1,}};

            assertEquals(5,Board.rowCount(board));
    }
    // col COUNT TEST------------------------------------------------
    @Test
    void colCount_sameNumberOfColumnsAndRows() {
        int [][] board = new int [6][6];
        assertEquals(6, Board.columnCount(board));
    }
    @Test
    void colCount_differentNumberOfColumnsAndRows() {
        int [][] board = new int [5][7];
        assertEquals(7, Board.columnCount(board));
    }
    @Test
    void colCount_Max_Row(){
        int [][] board = new int [4][8];
        assertEquals(8,Board.columnCount(board));
    }
    @Test
    void colCount_Min_Row(){
        int [][] board = new int [5][4];
        assertEquals(4,Board.columnCount(board));
    }
    @Test
    void colCount_Integer_values(){
        int[][] board ={{1, 2,},
                {2, 3,},
                {4, 2,},
                {1, 7,},
                {2, 1,}};

        assertEquals(2,Board.columnCount(board));
    }

    // VALID TEST ------------------------------------------------------
    @Test
    // to check if max col is in bound of board
    void Valid_MaxRow_Test(){
        int[][] board = Board.createBoard(8,6);
        assertTrue(Board.valid(board,7,5));

    }
    @Test
        // to check if max col is in bound of board
    void Valid_MaxColumn_Test(){
        int[][] board = Board.createBoard(6,8);
        assertTrue(Board.valid(board,5,7));

    }
    @Test
        // to test min row is in bound of board
    void Valid_MinRow_Test(){
        int[][] board = Board.createBoard(4,3);
        assertTrue(Board.valid(board,0,2));

    }
    @Test
        // to test if min column is bounds of board
    void Valid_MinColumn_Test(){
        int[][] board = Board.createBoard(3,4);
        assertTrue(Board.valid(board,2,0));
    }
    @Test
        // to test if both min row and column are in board
    void Valid_minRowAndColumn_Test(){
        int[][] board = Board.createBoard(3,4);
        assertTrue(Board.valid(board,0,0));
    }
    // canPlay TEST ------------------------------------------------------------
    @Test
    // to test if you can add at the last row of a column
    void canPlay_lastRowTest(){
        int [][] board = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {0, 7,3,4},
                {0, 1,3,4}};
        assertTrue(Board.canPlay(board,0));
    }
    @Test
        // to test if you can add at the first row of a column
    void canPlay_firstRowTest(){
        int [][] board = {{0, 2,3,4},
                {3, 3,3,4},
                {6, 2,3,4},
                {7, 7,3,4},
                {1, 1,3,4}};
        assertTrue(Board.canPlay(board,0));
    }
    @Test
        // to test if you can add in a full column
    void canPlay_Full_Column_Test(){
        int [][] board = {{1, 2,3,4},
                {3, 3,3,4},
                {6, 2,3,4},
                {7, 7,3,4},
                {1, 1,3,4}};
        assertFalse(Board.canPlay(board,0));
    }
    @Test
        // to test if you can add with only one item in a column
    void canPlay_1item_Column_Test(){
        int [][] board = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {0, 7,3,4},
                {1, 1,3,4}};
        assertTrue(Board.canPlay(board,0));
    }
    @Test
        // to test if the method works with larger matrix.
    void canPlay_Large_Matrix_Test(){
        int [][] board = {{0, 2,3,4,0, 2,3,4},
                {0, 3,3,4,0, 2,3,4},
                {0, 2,3,4,0, 2,3,4},
                {0, 7,3,4,0, 2,3,4},
                {8, 1,3,4,0, 2,3,4},
                {2, 3,3,4,0, 2,3,4},
                {4, 2,3,4,0, 2,3,4},
                {1, 7,3,4,0, 2,3,4},
                {1, 1,3,4,0, 2,3,4}};
        assertTrue(Board.canPlay(board,0));
    }

    // play TEST------------------------------------------------------------
    @Test
    // test if you can play in a full column
    void play_full_column_test(){
        int [][] board = {{1, 2,3,4},
                {4, 3,3,4},
                {3, 2,3,4},
                {2, 7,3,4},
                {1, 1,3,4}};
        assertEquals(-1,Board.play(board,0,2));
    }
    @Test
        // test if your right piece is actually placed!!!!
    void play_right_row_test(){
        int [][] board = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {2, 7,3,4},
                {1, 1,3,4}};

        // 1 is placed in 2 row
        int [][] expected = {{0, 2,3,4},
                {0, 3,3,4},
                {1, 2,3,4},
                {2, 7,3,4},
                {1, 1,3,4}};

        Board.play(board,0,1);

        assertArrayEquals(expected,board);

    }@Test
        // to test if you can add in last row of a column
    void Play_1item_Last_Row_Test(){
        int [][] board = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {0, 7,3,4},
                {0, 1,3,4}};
        assertEquals(4,Board.play(board,0,1));
    }
    @Test
        // to test if you can add in first row of a column
    void Play_1item_First_Row_Test(){
        int [][] board = {{0, 2,3,4},
                {4, 3,3,4},
                {1, 2,3,4},
                {2, 7,3,4},
                {3, 1,3,4}};
        assertEquals(0,Board.play(board,0,1));
    }
    @Test
        // to test if this function works on larger board
    void Play_Larger_Array() {
        int[][] board = {{0, 2, 3, 4, 0, 2, 3, 4},
                {0, 3, 3, 4, 0, 2, 3, 4},
                {0, 2, 3, 4, 0, 2, 3, 4},
                {0, 7, 3, 4, 0, 2, 3, 4},
                {8, 1, 3, 4, 0, 2, 3, 4},
                {2, 3, 3, 4, 0, 2, 3, 4},
                {4, 2, 3, 4, 0, 2, 3, 4},
                {1, 7, 3, 4, 0, 2, 3, 4},
                {1, 1, 3, 4, 0, 2, 3, 4}};
        assertEquals(3,Board.play(board,0,1));
    }

    //removeLastPlay Test-------------------------------------------------------------------------------------------
    @Test
    // test if you can remove in a full column
    void remove_full_column_test(){
        int [][] board = {{1, 2,3,4},
                {4, 3,3,4},
                {3, 2,3,4},
                {2, 7,3,4},
                {1, 1,3,4}};
        assertEquals(0,Board.removeLastPlay(board,0));
    }
    @Test
        // test if  correct piece is actually removed!!!!
    void remove_right_row_test(){
        int [][] board = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {2, 7,3,4},
                {1, 1,3,4}};

        // 1 is placed in 2 row
        int [][] expected = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {0, 7,3,4},
                {1, 1,3,4}};

        Board.removeLastPlay(board,0);

        assertArrayEquals(expected,board);

    }@Test
        // to test if you can remove from an empty column
    void Remove_empty_column_Test(){
        int [][] board = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {0, 7,3,4},
                {0, 1,3,4}};
        assertEquals(-1,Board.removeLastPlay(board,0));
    }
    @Test
        // to test if you can remove from last row
    void remove_last_Row_item_Test(){
        int [][] board = {{0, 2,3,4},
                {0, 3,3,4},
                {0, 2,3,4},
                {0, 7,3,4},
                {3, 1,3,4}};
        assertEquals(4,Board.removeLastPlay(board,0));
    }
    @Test
        // to test if this function works on larger board
    void removeLastPlay_Larger_Array() {
        int[][] board = {{0, 2, 3, 4, 0, 2, 3, 4},
                {0, 3, 3, 4, 0, 2, 3, 4},
                {0, 2, 3, 4, 0, 2, 3, 4},
                {0, 7, 3, 4, 0, 2, 3, 4},
                {8, 1, 3, 4, 0, 2, 3, 4},
                {2, 3, 3, 4, 0, 2, 3, 4},
                {4, 2, 3, 4, 0, 2, 3, 4},
                {1, 7, 3, 4, 0, 2, 3, 4},
                {1, 1, 3, 4, 0, 2, 3, 4}};
        assertEquals(4,Board.removeLastPlay(board,0));
    }
    // full Test---------------------------------------------------------------------------------------------
    @Test
    // to check if function works for full board
    void full_Full_Matrix_Test(){
        int [][] board = {{1, 2,3,4,1},
                {1, 3,3,4,2},
                {3, 2,3,4,1},
                {2, 7,3,4,1},
                {3, 1,3,4,1}};
        assertTrue(Board.full(board));
    }
    @Test
    // to check if the function works for last Column
    void full_last_column_test(){
        int [][] board = {{1, 2,3,4,0},
                {1, 3,3,4,0},
                {3, 2,3,4,0},
                {2, 7,3,4,0},
                {3, 1,3,4,1}};
        assertFalse(Board.full(board));
    }
    @Test
    // to check if function works for the first column
    void full_first_column_test() {
        int[][] board = {{0, 2, 3, 4, 1},
                {0, 3, 3, 4, 2},
                {0, 2, 3, 4, 1},
                {0, 7, 3, 4, 1},
                {3, 1, 3, 4, 1}};
        assertFalse(Board.full(board));

    }
    @Test
    // to check if function works with empty board
    void full_empty_board_Test(){
            int[][] board = new int[8][8];
        assertFalse(Board.full(board));
    }
    @Test
    // to check if function works with first row in any column    (large matrix)
    void full_firstRow_Test(){
        int[][] board = {{1, 2, 3, 4, 0, 2, 3, 4},
                {2, 3, 3, 4, 1, 2, 3, 4},
                {1, 2, 3, 4, 1, 2, 3, 4},
                {4, 7, 3, 4, 1, 2, 3, 4},
                {8, 1, 3, 4, 1, 2, 3, 4},
                {2, 3, 3, 4, 1, 2, 3, 4},
                {4, 2, 3, 4, 1, 2, 3, 4},
                {1, 7, 3, 4, 1, 2, 3, 4},
                {1, 1, 3, 4, 1, 2, 3, 4}};

        // here it is a large matrix and we are checking row 5

        assertFalse(Board.full(board));
    }

    //winInRow Test------------------------------------------------------------------------------------------

    @Test
    // to check if win work in the last row
    void win_lastRow_Test(){
        int[][] board = {{1, 2, 3, 4, 1},
                {1, 3, 3, 4, 2},
                {1, 2, 3, 2, 2},
                {1, 7, 1, 4, 2},
                {3, 7, 1, 1, 1}};

        // here the wining length is 3 and it makes an l in last row
        assertTrue(Board.winInRow(board,4,1,3));
    }
    @Test
        // to check if win work in the FIRST row
    void win_firstRow_Test(){
        int[][] board = {{1, 2, 1, 1, 1},
                {1, 3, 1, 4, 2},
                {1, 2, 3, 2, 2},
                {1, 7, 5, 4, 2},
                {3, 7, 1, 1, 1}};

        // here the wining length is 3 and it makes an l in first row
        assertTrue(Board.winInRow(board,0,1,3));
    }
    @Test
      // to check if it wins when length  is 3 and actually 4 consecutive pieces
    void win_4consecutive_3length_Test(){
        int[][] board = {{1, 2, 2, 1, 1},
                {1, 3, 2, 4, 2},
                {1, 2, 3, 2, 2},
                {1, 7, 1, 4, 2},
                {3, 1, 1, 1, 1}};

        // here the length is 3 and 1's in the last row are 4
        assertTrue(Board.winInRow(board,4,1,3));
    }
    @Test
        // to check if wins in row works with a tail and downwards
    void win_Tail_L_Down_Test() {
            // second last row
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 1, 1, 1, 2},
                {2, 2, 2, 1, 2}};

        assertTrue(Board.winInRow(board,3,1,3));
    }
    @Test
        // to check if wins in row works with a tail and upwards
    void win_Tail_L_Up_Test() {
            // second row
        int[][] board = {{2, 2, 2, 2, 1},
                {2, 2, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}};

        assertTrue(Board.winInRow(board,1,1,3));
    }
    @Test
    // to check if  does not win with half different pieces L shape
     void win_diff_pieces_Test(){
         int[][] board = {{2, 2, 2, 2, 1},
                 {2, 2, 1, 2, 1},
                 {2, 2, 2, 2, 2},
                 {2, 2, 2, 2, 2},
                 {2, 2, 2, 2, 2}};

         // here 1 1 1  is replaced with 1 2 1
         assertFalse(Board.winInRow(board,1,1,3));
     }
    @Test
        // to check if function works with win length as big as the matrix
    void winLargeLength(){
        int[][] board = {{2, 2, 2, 2, 2},
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 2}};


        assertTrue(Board.winInRow(board,1,1,5));
    }


    // winColumnTest---------------------------------------------------------------------------------------------
    @Test
    // to check win works in first column
    void winInFirstRow(){
        int[][] board = {{2, 2, 2, 2, 1},
                {1, 1, 2, 2, 2},
                {1, 2, 2, 2, 2},
                {1, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}};
        assertTrue(Board.winInColumn(board,0,1,3));
    }
    @Test
    // to check win works in last column
    void winInLastRow(){
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 1, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 2}};
        assertTrue(Board.winInColumn(board,4,1,3));
    }
    @Test
    // to check if win work with the tail of consecutive pieces
    void winInRowTailI(){
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 1, 1}};
        assertTrue(Board.winInColumn(board,4,1,4));
    }
    @Test
    // to check win work with same length as board dimensions
    void winLargeLengthTest(){
        int[][] board = {{2, 2, 2, 1, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1}};
        assertTrue(Board.winInColumn(board,4,1,5));
    }
    @Test
    // to check if it win with different pieces SHAPED L
    void winDiffPiecesL(){
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 1, 1},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1}};

        // HERE
        //  1  1                     1  1
        //     1     IS REPLACED BY     2
        //     1                        1
        //     1                        1

        assertFalse(Board.winInColumn(board,4,1,4));
    }
    @Test
        // to check if it wins when length  is 3 and actually 4 consecutive pieces
    void win_4consecutive_3length_Column_Test(){
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 1, 1},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 2, 1}};
        // here the length is 3 and 1's in the last column are 4
        assertTrue(Board.winInColumn(board,4,1,3));
    }
    //winInDiagonalForwardSlash-------------------------------------------------------------

    @Test
    void winInDiagonalForward1(){
            // to check if it wins with one side touching the edge of 2d array
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 1, 2, 2},
                {2, 1, 2, 1, 2},
                {1, 2, 2, 2, 2}};
        assertTrue(Board.winInDiagonalForwardSlash(board,1,3));

    }
    @Test
    void winInDiagonalForward2(){
            // in this we check if you Can make L in the opposite direction
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 1, 2, 2, 2},
                {2, 2, 1, 2, 2},
                {2, 1, 2, 2, 2},
                {1, 2, 2, 2, 2}};
        assertTrue(Board.winInDiagonalForwardSlash(board,1,3));
    }
    @Test
    void winInDiagonalForward3(){
            // in this we check if you can make L with are large consective pieces rows
        int[][] board = {{2, 2, 2,2,1},
                {2, 1, 2, 1, 2},
                {2, 2, 1, 2, 2},
                {2, 1, 2, 2, 2},
                {1, 2, 2, 2, 2}};
        // basically there are 5 consectve pieces and perpendicular L is connected in middle making a L
        assertTrue(Board.winInDiagonalForwardSlash(board,1,3));
    }
    @Test
    void winInDiagonalForward4(){
            // in this test we are making sure that you can make L at the bottom also
        int[][] board = {{2, 2, 2,2,1},
                {2, 2, 2, 1, 2},
                {2, 2, 1, 2, 2},
                {2, 1, 2, 2, 2},
                {2, 2, 1, 2, 2}};
        // so basically we are making a L with the bottom end of the consecutive pieces;
        assertTrue(Board.winInDiagonalForwardSlash(board,1,3));
    }
    @Test
    void winInDiagonalForward5(){
            // this test is super simple just making sure that it only make L with same pieces;
        int[][] board = {{2, 2, 2,2,1},
                {2, 2, 2, 3, 2},
                {2, 2, 3, 2, 2},
                {2, 1, 2, 2, 2},
                {2, 2, 1, 2, 2}};
        // here the L is mix of 3 and 1
        assertFalse(Board.winInDiagonalForwardSlash(board,1,3));
    }
    @Test
    void winInDiagonalForward6(){
            // in this we will check if function work with larger matrix with forward slash
        int[][] board = {{2, 2, 2,2,2,2, 2, 2,2,2},
                {2, 2, 2, 2, 2, 2, 2, 2,2,2},
                {2, 2, 2, 2, 2, 2, 2, 2,2,2},
                {2, 2, 2, 2, 2, 2, 2, 2,1,2},
                {2, 2, 2, 2, 2, 2, 2, 2,2,1},         //<---------- check for here if it goes out of bound here
                {2, 2, 2, 2, 2, 2, 2, 2,1,2},
                {2, 2, 2, 2, 2, 2, 2, 1,2,2},
                {2, 2, 2, 2, 2, 2, 1, 2,2,2},
                {2, 2, 2, 2, 2, 1, 2, 2,2,2},};
        // here the goal is to make sure its doesnt go out of bounds when checking right of board for perpendicular win when
        // its the last column
        assertTrue(Board.winInDiagonalForwardSlash(board,1,5));
    }
    //winInDiagonalBackwardSlash-------------------------------------------------------------

    @Test
    void winInDiagonalBackward1(){
        // to check if it wins with one side touching the edge of 2d array
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 1, 2, 2},
                {2, 1, 2, 1, 2},
                {2, 2, 2, 2, 1}};
        assertTrue(Board.winInDiagonalBackslash(board,1,3));

    }
    @Test
    void winInDiagonalBackslash2(){
        // in this we check if you Can make L in the opposite direction
        int[][] board = {{2, 2, 2, 2, 2},
                {2, 2, 2, 1, 2},
                {2, 2, 1, 2, 2},
                {2, 2, 2, 1, 2},
                {2, 2, 2, 2, 1}};
        assertTrue(Board.winInDiagonalBackslash(board,1,3));
    }
    @Test
    void winInDiagonalBackslash3(){
        // in this we check if you can make L with are large consective pieces rows
        int[][] board = {{1, 2, 2,2,2},
                {2, 1, 2, 2, 2},
                {2, 2, 1, 2, 2},
                {2,1, 2, 1, 2},
                {2, 2, 2, 2, 1}};
        // basically there are 5 consective pieces and perpendicular L is connected in middle making a L
        assertTrue(Board.winInDiagonalBackslash(board,1,3));
    }
    @Test
    void winInDiagonalBackslash4(){
        // in this test we are making sure that you can make L at the bottom also
        int[][] board = {{2, 2, 2,2,2},
                {2, 2, 1, 2, 2},
                {2, 2, 2, 1, 2},
                {2, 2, 2, 2, 1},
                {2, 2, 2, 1, 2}};
        // so basically we are making a L with the bottom end of the consecutive pieces;
        assertTrue(Board.winInDiagonalBackslash(board,1,3));
    }
    @Test
    void winInDiagonalBackslash5(){
        // this test is super simple just making sure that it only make L with same pieces;
        int[][] board = {{2, 2, 2,2,2},
                {2, 2, 2, 1, 2},
                {2, 2, 1, 2, 2},
                {2, 3, 2, 2, 2},
                {2, 2, 1, 2, 2}};
        // here the L is mix of 3 and 1
        assertFalse(Board.winInDiagonalBackslash(board,1,3));
    }
    @Test
    void winInDiagonalBackslash6(){
        // in this we will check if function work with larger matrix with forward slash
        int[][] board = {{2, 2, 2,2,2,2, 2, 2,2,2},
                {2, 2, 2, 2, 2, 2, 2, 2,2,2},
                {2, 2, 2, 2, 2, 2, 2, 2,2,2},
                {2, 2, 2, 2, 2, 1, 2, 2,2,2},
                {2, 2, 2, 2, 2, 2, 1, 2,2,2},
                {2, 2, 2, 2, 2, 2, 2, 1,2,2},
                {2, 2, 2, 2, 2, 2, 2, 2,1,2},
                {2, 2, 2, 2, 2, 2, 2, 2,2,1},          //<---------- check for here if it goes out of bound here
                {2, 2, 2, 2, 2, 2, 2, 2,1,2},};
        // here the goal is to make sure its doesnt go out of bounds when checking right of board for perpendicular win when
        // its the last column
        assertTrue(Board.winInDiagonalBackslash(board,1,5));
    }
    // Hint Test-------------------------------------------------------------------------
    @Test
    void HintWinCol(){
            // to check if it gives hint for win in col
        int[][] board = {{0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {1,0,1,0,0,0},
                {1,2,1,0,0,0},
                {1,2,1,0,0,0}};
        int [] expected = {2,1};
        assertArrayEquals(expected,Board.hint(board,1,3));

    }
    @Test
    void hintWinRow(){
            // check if it gives hint for win in row
        int[][] board = {{0,0,0,0,0,0},
                {0,0,0,0,0,0,},
                {0,0,0,0,0,0},
                {0,0,2,2,0,0},
                {1,2,1,1,0,0}};
        int [] expected ={3,1};
        assertArrayEquals(expected,Board.hint(board,2,3));

    }
    @Test
    void hintWinByForwardslash(){
            // check if it gives hint for win by /
        int[][] board = {{0,0,0,0,0,0},
                {0,0,0,0,0,0,},
                {0,0,0,0,0,0},
                {0,1,2,2,0,0},
                {1,2,1,1,0,0}};
        int[] expected = {2,3};
        assertArrayEquals(expected,Board.hint(board,2,3));

    }
    @Test
    void hintWinByBackSlash(){
            // check if it wins by \
        int[][] board = {{0,0,0,0,0,0},
                {0,0,0,0,0,0,},
                {0,0,0,0,0,0},
                {0,1,2,2,0,0},
                {1,2,1,1,2,0}};
        int[] expected = {2,2};
        assertArrayEquals(expected,Board.hint(board,2,3));
    }
    @Test
    void hintNoWin(){
            // when there are no wins
        int[][] board = {{0,0,0,0,0,0},
                {0,0,0,0,0,0,},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0}};
        int[] expected ={-1,-1};
        assertArrayEquals(expected,Board.hint(board,1,3));
    }




    /**
     * Used to make a copy of board before functions run, so that verify a function was non-destructive on board is easy
     * @param board The board to make deep copy of
     * @return A deep copy of given board
     */


}

