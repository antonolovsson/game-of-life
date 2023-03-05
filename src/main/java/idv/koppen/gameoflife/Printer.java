package idv.koppen.gameoflife;

import java.io.PrintStream;

public class Printer {
    public static void printGrid(Playfield playfield, int generation) {
        //Prints out the grid in the output log.
        System.out.println("Generation: " + (generation + 1));
        playfield.print();
    }

    public static void printGrid(Playfield playfield) {
        //Prints out the grid in the output log.
        System.out.println("Stating position.");
        playfield.print();
    }
}
