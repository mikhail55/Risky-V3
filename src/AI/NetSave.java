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
    public float[][][][] readNetFile(String fileName) {
        float[][][][] weights = new float[0][0][0][0];


        fileName = fileName + ".jpg";

        try {

            File dataFile = new File(fileName);
            fileInput = new Scanner(dataFile);

            weights = new float[Integer.parseInt(fileInput.nextLine())][][][];

            // First layer is which network
            // Second layer is which layer of weights
            // Third layer is which node
            // Fourth layer is each weight
            for(int k = 0; k < weights.length; k++) {
                weights[k] = new float[Integer.parseInt(fileInput.nextLine())][][];
                for (int i = 0; i < weights[k].length; i++) {
                    weights[k][i] = new float[Integer.parseInt(fileInput.nextLine())][];
                    for (int n = 0; n < weights[k][i].length; n++) {
                        weights[k][i][n] = new float[Integer.parseInt(fileInput.nextLine())];
                        for (int j = 0; j < weights[k][i][n].length; j++) {
                            weights[k][i][n][j] = Float.parseFloat(fileInput.nextLine());
                        }
                    }
                }

                if(fileInput.hasNext()) {
                    fileInput.nextLine();
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

    public void saveFile(String fileName, float[][][][] weights) {
        try {
            fileName = fileName + ".jpg";

            FileWriter fw = new FileWriter(fileName, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);

            outFile.println(weights.length);
            for(int k = 0; k < weights.length; k++) {
                outFile.println(weights[k].length);
                for (int i = 0; i < weights[k].length; i++) {
                    outFile.println(weights[k][i].length);
                    for (int n = 0; n < weights[k][i].length; n++) {
                        outFile.println(weights[k][i][n].length);
                        for (int j = 0; j < weights[k][i][n].length; j++) {
                            outFile.println(weights[k][i][n][j]);
                        }
                    }
                }
            }

            outFile.close();

        } catch (Exception e) {
            System.err.println("Error writing weights to file");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
