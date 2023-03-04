package idv.koppen.gameoflife;

public class Playfield {

    int columns;
    int rows;
    CellStatus[][] cell;

    public Playfield(int rows, int columns) {
        cell = new CellStatus[rows][columns];
        this.columns = columns;
        this.rows = rows;
    }

    public enum CellStatus {
        ALIVE,
        DEAD;
    }
}


