import AI.NetSave;

/**
 * This class was supposed to manipulate the game to train the neural network
 * Is currently set up to test the save/read function for the neural network
 *
 * Could not be used to train neural network since the game was never finished by partners, no opportunity to build
 * training method
 */
public class NeuralTrainer {
    public static void main(String[] args) {
        // Define where the neural network is saved to
        String fileName = "AISaveFile.jpg";

        // The object it is saved through
        NetSave netSave = new NetSave();

        // These two functions were used to create/read neural networks (they were alternated)
        NetController netController = new NetController(netSave.readNetFile(fileName)[0], GameCell.Owner.Team1, new GameLogic());
        //NetController netController = new NetController();

        // Gets data that had been read/created from/in the neural network
        float [][][][] saveData = new float[1][][][];
        saveData[0] = netController.save();

        // Print one of the float values so it can be compared to check if there is consistency
        System.out.println(saveData[0][0][0][0]);

        // Save aforementioned data
        netSave.saveFile(fileName, saveData);
    }
}
