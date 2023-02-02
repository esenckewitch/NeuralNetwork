import data.CsvData;
import neuron.Neuron;
import neuron.NeuronActions;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        CsvData scv;
        double[][] res;
        try {
            scv = new CsvData("test3.csv");
            scv.read();
            System.out.println(scv);
            res = scv.getDoubleData();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}