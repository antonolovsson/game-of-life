package idv.koppen.gameoflife;

import java.util.Random;

public class Calculator {

    private final Random random;

    public Calculator() {
        random = new Random();
    }

    public Playfield generateStartingPosition(Playfield playfield) {

        //Generates '0' or '1' for each cell in the grid.
        for (int currentRow = playfield.rows - 1; currentRow <= playfield.rows + 1; currentRow++) {
            for (int currentColumn = playfield.columns - 1; currentColumn <= playfield.columns + 1; currentColumn++) {
                if (random.nextBoolean()) {
                    playfield.cell[currentRow][currentColumn] = Playfield.CellStatus.ALIVE;
                } else {
                    playfield.cell[currentRow][currentColumn] = Playfield.CellStatus.DEAD;
                }
            }
        }
        return playfield;
    }

    public Playfield calculateNextGrid(Playfield playfield) {
        //Initialize the placeholder grid.
        Playfield nextPlayfield = new Playfield(playfield.rows, playfield.columns);
        for (int currentRow = playfield.rows - 1; currentRow <= playfield.rows + 1; currentRow++) {
            for (int currentColumn = playfield.columns - 1; currentColumn <= playfield.columns + 1; currentColumn++) {

                int numberOfNeighbours = checkNumberOfNeighbours(currentRow, currentColumn, playfield);

                //If the cell has less than 2 neighbours then the cell dies or stays dead.
                if (numberOfNeighbours < 2) {
                    nextPlayfield.cell[currentRow][currentColumn] = Playfield.CellStatus.DEAD;
                }

                //If the cell has exactly 2 neighbors the cell will stay the same state, dead or alive.
                else if (numberOfNeighbours == 2) {
                    nextPlayfield.cell[currentRow][currentColumn] = playfield.cell[currentRow][currentColumn];
                }

                //If the cell has exactly 3 neighbours the cell will stay alive, and if the cell was dead before the cell will now be alive.
                else if (numberOfNeighbours == 3) {
                    nextPlayfield.cell[currentRow][currentColumn] = Playfield.CellStatus.ALIVE;
                }

                //Since the above checks for numbers 0 to 3 this is for when the cell has more than 4 neighbours, resulting in overpopulation and the cell is now dead.
                else {
                    nextPlayfield.cell[currentRow][currentColumn] = Playfield.CellStatus.DEAD;
                }
            }
        }

        //Updates the grid with the populated placeholder data.
        return nextPlayfield;
    }

    private int checkNumberOfNeighbours(int row, int col, Playfield playfield) {
        int neighbours = 0;

        //Selects the surrounding cells to see if they are populated.
        for (int currentRow = row - 1; currentRow <= row + 1; currentRow++) {
            for (int currentCol = col - 1; currentCol <= col + 1; currentCol++) {

                //Checks for the conditions to make sure the selected cell is inside the grid and is populated, then it increases the count of neighbours by 1.
                if (currentRow >= 0
                        && currentRow < playfield.rows
                        && currentCol >= 0
                        && currentCol < playfield.columns
                        && playfield.cell[currentRow][currentCol] == Playfield.CellStatus.ALIVE) {
                    neighbours++;
                }
            }
        }

        //Removes one from the neighbour count if the cell itself is populated since the loop above counts the cell itself.
        if (playfield.cell[row][col] == Playfield.CellStatus.ALIVE) {
            neighbours--;
        }
        return neighbours;
    }
}
