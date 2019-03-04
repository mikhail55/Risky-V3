package AI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author TylerWilson
 */
public class NetSave {

    private Scanner fileInput;

    public NetSave() { }

    // I'M SO SORRY I HATE THE SCARY ARRAY TOO
    public double[][][][] readNetFile(String fileName) {
        double[][][][] weights = new double[0][0][0][0];

        try {

            File dataFile = new File(fileName);
            fileInput = new Scanner(dataFile);

            fileInput.useDelimiter("ø");

            weights = new double[Integer.parseInt(fileInput.next())][][][];
            fileInput.next();

            // First layer is which network
            // Second layer is which layer of weights
            // Third layer is which node
            // Fourth layer is each weight
            for(int k = 0; k < weights.length; k++) {
                weights[k] = new double[Integer.parseInt(fileInput.next())][][];
                for (int i = 0; i < weights[k].length; i++) {
                    weights[k][i] = new double[Integer.parseInt(fileInput.next())][];
                    for (int n = 0; n < weights[k][i].length; n++) {
                        weights[k][i][n] = new double[Integer.parseInt(fileInput.next())];
                        for (int j = 0; j < weights[k][i][n].length; j++) {
                            weights[k][i][n][j] = Double.parseDouble(fileInput.next());
                        }
                    }

                    fileInput.next();
                }
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

    public void saveFile(String fileName, double[][][][] weights) {
        try {
            FileWriter fw = new FileWriter(fileName, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);

            outFile.println(weights.length + "ø");

            outFile.println();

            for(int k = 0; k < weights.length; k++) {
                outFile.print(weights[k].length + "ø");
                for (int i = 0; i < weights[k].length; i++) {
                    outFile.print(weights[k][i].length + "ø");
                    for (int n = 0; n < weights[k][i].length; n++) {
                        outFile.print(weights[k][i][n].length + "ø");
                        for (int j = 0; j < weights[k][i][n].length; j++) {
                            outFile.print(weights[k][i][n][j] + "ø");
                        }
                    }

                    outFile.println();
                }
            }

            outFile.close();

        } catch (Exception e) {
            System.err.println("Error");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
