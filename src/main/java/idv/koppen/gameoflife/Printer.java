package idv.koppen.gameoflife;

public class Printer {
    public static void printGrid(Playfield playfield) {
        //Prints out the grid in the output log.
        int rows = playfield.rows;
        int cols = playfield.columns;
        System.out.println();
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            System.out.print("|");
            for (int currentCol = 0; currentCol < cols; currentCol++) {

                if (playfield.cell[currentRow][currentCol] == Playfield.CellStatus.ALIVE) {
                    System.out.print("X" + "|");
                } else
                    System.out.print(" " + "|");
            }
            System.out.println();
        }
        System.out.println();
    }
}
