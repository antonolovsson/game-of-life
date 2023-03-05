package idv.koppen.gameoflife;

import java.io.PrintStream;
import java.util.Random;

public class Playfield {

    private final int columns;
    private final int rows;

    private CellStatus[][] cells;

    private final Random random;

    public Playfield(int rows, int columns) {
        random = new Random();
        cells = new CellStatus[rows][columns];
        this.columns = columns;
        this.rows = rows;
    }

    private enum CellStatus {
        ALIVE,
        DEAD;
    }

    public void generateStartingPosition() {

        //Generates '0' or '1' for each cell in the grid.
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                if (random.nextBoolean()) {
                    cells[currentRow][currentColumn] = CellStatus.ALIVE;
                } else {
                    cells[currentRow][currentColumn] = CellStatus.DEAD;
                }
            }
        }
    }

    public void calculateNextGrid() {
        //Initialize the placeholder grid.
        CellStatus[][] nextCells = new CellStatus[rows][columns];
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {

                int numberOfNeighbours = checkNumberOfNeighbours(currentRow, currentColumn);

                //If the cell has less than 2 neighbours then the cell dies or stays dead.
                if (numberOfNeighbours < 2) {
                    nextCells[currentRow][currentColumn] = CellStatus.DEAD;
                }

                //If the cell has exactly 2 neighbors the cell will stay the same state, dead or alive.
                else if (numberOfNeighbours == 2) {
                    nextCells[currentRow][currentColumn] = cells[currentRow][currentColumn];
                }

                //If the cell has exactly 3 neighbours the cell will stay alive, and if the cell was dead before the cell will now be alive.
                else if (numberOfNeighbours == 3) {
                    nextCells[currentRow][currentColumn] = CellStatus.ALIVE;
                }

                //Since the above checks for numbers 0 to 3 this is for when the cell has more than 4 neighbours, resulting in overpopulation and the cell is now dead.
                else {
                    nextCells[currentRow][currentColumn] = CellStatus.DEAD;
                }
            }
        }

        //Updates the grid with the populated placeholder data.
        cells = nextCells;
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
                        && cells[currentRow][currentCol] == CellStatus.ALIVE) {
                    neighbours++;
                }
            }
        }

        //Removes one from the neighbour count if the cell itself is populated since the loop above counts the cell itself.
        if (cells[row][col] == CellStatus.ALIVE) {
            neighbours--;
        }
        return neighbours;
    }

    public void print() {
        PrintStream printStream = new PrintStream(System.out);
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            printStream.append("|");
            for (int currentCol = 0; currentCol < columns; currentCol++) {

                if (cells[currentRow][currentCol] == CellStatus.ALIVE) {
                    printStream.append("X" + "|");
                } else
                    printStream.append(" " + "|");
            }
            printStream.println();
        }
        printStream.println();
    }
}


