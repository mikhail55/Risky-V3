import AI.NetSave;

public class NeuralTrainer {
    public static void main(String[] args) {
        String fileName = "AISaveFile";

        NetSave netSave = new NetSave();

        //NetController netController = new NetController(netSave.readNetFile(fileName)[0]);
        NetController netController = new NetController();

        float [][][][] saveData = new float[1][][][];
        saveData[0] = netController.save();

        netSave.saveFile(fileName, saveData);
    }
}
