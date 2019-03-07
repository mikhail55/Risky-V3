package AI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.*;
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
            }


            /*byte[] bytes = new byte[4];

            for(int b = 0; b < 4; b++) {
                bytes[b] = fileInput.nextByte();
            }
            weights = new float[(int) byteArrayToFloat(bytes)][][][];

            for(int k = 0; k < weights.length; k++) {
                for(int b = 0; b < 4; b++) {
                    bytes[b] = fileInput.nextByte();
                }
                weights[k] = new float[(int) byteArrayToFloat(bytes)][][];
                for (int i = 0; i < weights[k].length; i++) {
                    for(int b = 0; b < 4; b++) {
                        bytes[b] = fileInput.nextByte();
                    }
                    weights[k][i] = new float[(int) byteArrayToFloat(bytes)][];
                    for (int n = 0; n < weights[k][i].length; n++) {
                        for(int b = 0; b < 4; b++) {
                            bytes[b] = fileInput.nextByte();
                        }
                        weights[k][i][n] = new float[(int) byteArrayToFloat(bytes)];
                        for (int j = 0; j < weights[k][i][n].length; j++) {
                            for(int b = 0; b < 4; b++) {
                                bytes[b] = fileInput.nextByte();
                            }
                            weights[k][i][n][j] = byteArrayToFloat(bytes);
                        }
                    }
                }
            }*/


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


            /*byte[] bytes = floatToByteArray(weights.length);
            for(int b = 0; b < 4; b++) {
                outFile.print(bytes[b] + " ");
            }
            for(int k = 0; k < weights.length; k++) {
                bytes = floatToByteArray(weights[k].length);
                for(int b = 0; b < 4; b++) {
                    outFile.print(bytes[b] + " ");
                }
                for (int i = 0; i < weights[k].length; i++) {
                    bytes = floatToByteArray(weights[k][i].length);
                    for(int b = 0; b < 4; b++) {
                        outFile.print(bytes[b] + " ");
                    }
                    for (int n = 0; n < weights[k][i].length; n++) {
                        bytes = floatToByteArray(weights[k][i][n].length);
                        for(int b = 0; b < 4; b++) {
                            outFile.print(bytes[b] + " ");
                        }
                        for (int j = 0; j < weights[k][i][n].length; j++) {
                            bytes = floatToByteArray(weights[k][i][n][j]);
                            for(int b = 0; b < 4; b++) {
                                outFile.print(bytes[b] + " ");
                            }
                        }
                    }
                }
            }*/


            outFile.close();



        } catch (Exception e) {
            System.err.println("Error writing weights to file");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private byte[] floatToByteArray(float f) {
        return ByteBuffer.allocate(4).putFloat(f).array();
    }

    private float byteArrayToFloat(byte[] b) {
        ByteBuffer buff = ByteBuffer.wrap(b);
        return buff.getFloat();
    }
}
