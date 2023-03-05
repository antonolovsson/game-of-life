package idv.koppen.gameoflife;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PlayfieldTest {

    @Test
    void generateStartingPosition() {
        //TODO Write this test.
    }

    @Test
    void calculateNextGrid() {
        int rows = 3;
        int columns = 3;
        CellStatus[][] startingBoard = {
                {CellStatus.DEAD,CellStatus.DEAD,CellStatus.DEAD},
                {CellStatus.ALIVE,CellStatus.ALIVE,CellStatus.ALIVE},
                {CellStatus.DEAD,CellStatus.DEAD,CellStatus.DEAD}
        };
        CellStatus[][] expectedEndBoard = {
                {CellStatus.DEAD, CellStatus.ALIVE, CellStatus.DEAD},
                {CellStatus.DEAD, CellStatus.ALIVE, CellStatus.DEAD},
                {CellStatus.DEAD, CellStatus.ALIVE, CellStatus.DEAD}
        };

        Playfield playfield = new Playfield(rows,columns);
        playfield.setBoard(startingBoard);
        playfield.calculateNextGrid();
        CellStatus[][] endBoard = playfield.getBoard();

        assertEquals(Arrays.deepHashCode(expectedEndBoard), Arrays.deepHashCode(endBoard));
    }

    @Test
    void print() {

        //TODO Fix this test.
        //PrintStream out = mock(PrintStream.class);
        //System.setOut(out);
        //Playfield playfield = new Playfield(3,3);
        //playfield.generateStartingPosition();
        //playfield.print();
        //verify(out, times(1)).println(any(String.class));\
    }
}