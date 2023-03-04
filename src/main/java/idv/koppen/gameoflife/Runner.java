package idv.koppen.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {


    public void run() throws IOException {
        int rows;
        int columns;
        int generations;

        System.out.println("input rows (minimum value = 1): ");
        rows = inputReader();
        System.out.println("input columns (minimum value = 1): ");
        columns = inputReader();
        System.out.println("input generations (minimum value = 1): ");
        generations = inputReader();

        Playfield playfield = new Playfield(rows, columns);
        Calculator calculator = new Calculator();
        playfield = calculator.generateStartingPosition(playfield);
        Printer.printGrid(playfield);
        for (int i = 0; i < generations; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            playfield = calculator.calculateNextGrid(playfield);
            Printer.printGrid(playfield);
        }
    }

    private int inputReader() throws IOException {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        //TODO handle characters that's not an INT
        String input = reader.readLine();
        // Returns values greater than 0
        return Math.max(Integer.parseInt(input), 1);
    }
}
