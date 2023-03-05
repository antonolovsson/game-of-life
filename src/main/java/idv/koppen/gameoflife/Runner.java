package idv.koppen.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {


    public void run() throws IOException {
        int rows;
        int columns;
        int generations;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("input rows (minimum value = 1): ");
        rows = inputReader(reader);
        System.out.println("input columns (minimum value = 1): ");
        columns = inputReader(reader);
        System.out.println("input generations (minimum value = 1): ");
        generations = inputReader(reader);

        Playfield playfield = new Playfield(rows, columns);
        playfield.generateStartingPosition();
        System.out.println("Stating position.");
        playfield.print();
        for (int currentGeneration = 0; currentGeneration < generations; currentGeneration++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            playfield.calculateNextGrid();
            System.out.println("Generation: " + (currentGeneration + 1));
            playfield.print();
        }
    }

    private int inputReader(BufferedReader reader) throws IOException {
        // Reading data using readLine
        //TODO handle characters that's not an INT
        String input = reader.readLine();
        // Returns values greater than 0
        return Math.max(Integer.parseInt(input), 1);
    }
}
