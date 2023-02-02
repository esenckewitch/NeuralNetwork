package neuron;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestNeuron2{

    public static void main(String[] args) {

        double[][] data = {
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0}
        };

        int n = 5;
        Neuron neuron = new NeuronWithSigmoidFunction(n);
        //Neuron neuron = new Neuron(n);
        int count = 0;
        boolean finish = false;
        try{
            var bw = new BufferedWriter(new FileWriter("log.csv"));
            bw.write("Номер эпохи, Веса нейрона, Выходной вектор, Ошибка \n");
        while(!finish){
            double targetResult;
            double[] x = new double[n];
            double maxError = 0;

            for(int j = 0; j < data.length; j++){
                for(int k = 0; k < n-1; k++)
                    x[k] = data[j][k];
                x[n-1] = 1;

                targetResult = data[j][4];
                double error = neuron.getError(targetResult, x);
                if(Math.abs(error) > Math.abs(maxError))
                    maxError = error;
                for(int k = 0; k < x.length; k++)
                    neuron.setWeight(k, error, 0.3, x);
            }

            double[] logX = new double[n];
            var tmp = "";
            for(int j = 0; j < data.length; j++) {
                for (int k = 0; k < n - 1; k++)
                    logX[k] = data[j][k];
                logX[n - 1] = 1;
                tmp += String.format("%8.4f ", neuron.result(logX));
            }

            var logString = String.format("%d, %s, %s, %f\n", count, neuron, tmp, maxError);
            bw.write(logString);

            finish = Math.abs(maxError) < 0.1;
            count++;
        }
            bw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
