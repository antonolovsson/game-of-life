package idv.koppen.gameoflife;

import java.io.PrintStream;
import java.util.Random;

public class Playfield {

    private final int columns;
    private final int rows;
    private CellStatus[][] board;
    private final Random random;

    public CellStatus[][] getBoard() {
        return board;
    }

    public void setBoard(CellStatus[][] board) {
        this.board = board;
    }

    public Playfield(int rows, int columns) {
        random = new Random();
        board = new CellStatus[rows][columns];
        this.columns = columns;
        this.rows = rows;
    }

    public void generateStartingPosition() {

        //Generates '0' or '1' for each cell in the grid.
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                if (random.nextBoolean()) {
                    board[currentRow][currentColumn] = CellStatus.ALIVE;
                } else {
                    board[currentRow][currentColumn] = CellStatus.DEAD;
                }
            }
        }
    }

    public void calculateNextGrid() {
        //Initialize the placeholder grid.
        CellStatus[][] nextBoard = new CellStatus[rows][columns];
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {

                int numberOfNeighbours = checkNumberOfNeighbours(currentRow, currentColumn);

                //If the cell has less than 2 neighbours then the cell dies or stays dead.
                if (numberOfNeighbours < 2) {
                    nextBoard[currentRow][currentColumn] = CellStatus.DEAD;
                }

                //If the cell has exactly 2 neighbors the cell will stay the same state, dead or alive.
                else if (numberOfNeighbours == 2) {
                    nextBoard[currentRow][currentColumn] = board[currentRow][currentColumn];
                }

                //If the cell has exactly 3 neighbours the cell will stay alive, and if the cell was dead before the cell will now be alive.
                else if (numberOfNeighbours == 3) {
                    nextBoard[currentRow][currentColumn] = CellStatus.ALIVE;
                }

                //Since the above checks for numbers 0 to 3 this is for when the cell has more than 4 neighbours, resulting in overpopulation and the cell is now dead.
                else {
                    nextBoard[currentRow][currentColumn] = CellStatus.DEAD;
                }
            }
        }

        //Updates the grid with the populated placeholder data.
        board = nextBoard;
    }

    private int checkNumberOfNeighbours(int row, int col) {
        int neighbours = 0;

        //Selects the surrounding cells to see if they are populated.
        for (int currentRow = row - 1; currentRow <= row + 1; currentRow++) {
            for (int currentCol = col - 1; currentCol <= col + 1; currentCol++) {

                //Checks for the conditions to make sure the selected cell is inside the grid and is populated, then it increases the count of neighbours by 1.
                if (currentRow >= 0
                        && currentRow < rows
                        && currentCol >= 0
                        && currentCol < columns
                        && board[currentRow][currentCol] == CellStatus.ALIVE) {
                    neighbours++;
                }
            }
        }

        //Removes one from the neighbour count if the cell itself is populated since the loop above counts the cell itself.
        if (board[row][col] == CellStatus.ALIVE) {
            neighbours--;
        }
        return neighbours;
    }

    public void print() {
        StringBuilder boardPrintString = new StringBuilder();
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            boardPrintString.append("|");
            for (int currentCol = 0; currentCol < columns; currentCol++) {

                if (board[currentRow][currentCol] == CellStatus.ALIVE) {
                    boardPrintString.append("X|");
                } else {
                    boardPrintString.append(" |");
                }
            }
            boardPrintString.append("\n");
        }
        boardPrintString.append("\n");
        System.out.println(boardPrintString);
    }
}


