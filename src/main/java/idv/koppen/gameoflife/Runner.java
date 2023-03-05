package idv.koppen.gameoflife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {
    private final BufferedReader reader;
    public Runner(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        System.out.println("input rows (minimum value = 1): ");
        int rows = inputReader(reader);
        System.out.println("input columns (minimum value = 1): ");
        int columns = inputReader(reader);
        System.out.println("input generations (minimum value = 1): ");
        int generations = inputReader(reader);

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
        //TODO handle characters that's not an int and negative values.
        String input = reader.readLine();
        // Returns values greater than 0
        return Math.max(Integer.parseInt(input), 1);
    }
}
