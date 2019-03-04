package AI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

class NetSave {

    private Scanner fileInput;

    NetSave() { }

    // I'M SO SORRY I HATE THE SCARY ARRAY TOO
    int[][][] readNetFile(String fileName) {
        int[][][] weights = new int[0][0][0];

        try {

            File dataFile = new File(fileName);
            fileInput = new Scanner(dataFile);

            fileInput.useDelimiter("ø");

            weights = new int[Integer.parseInt(fileInput.next())][][];
            fileInput.next();


            // First layer is which layer of weights
            // Second layer is which node
            // Third layer is each weight
            for(int i = 0; i < weights.length; i++) {
                weights[i] = new int[Integer.parseInt(fileInput.next())][];
                for(int n = 0; n < weights[i].length; n++) {
                    weights[i][n] = new int[Integer.parseInt(fileInput.next())];
                    for(int j = 0; j < weights[j][n].length; j++) {
                        weights[i][n][j] = Integer.parseInt(fileInput.next());
                    }
                }

                fileInput.next();
            }

            fileInput.close();

        } catch (Exception e) {
            System.err.println("Error reading weights from file");
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if (fileInput != null) {
                fileInput.close();
            }
        }

        return weights;
    }
}
