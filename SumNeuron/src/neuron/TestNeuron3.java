package neuron;

import data.CsvData;

import java.io.FileNotFoundException;

public class TestNeuron3 {
    public static void main(String[] args) {
        CsvData numbers;
        CsvData data;
        {
            try {
                numbers = new CsvData("numbers.csv");
                numbers = numbers.read();

                data = new CsvData("data.csv");
                data = data.read();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        int n = 16;
        Neuron neuron = new NeuronWithSigmoidFunction(n);

        int count = 0;
        boolean finish = false;
        while (!finish){
            double targetResult;
            double[] x = new double[n];
            double maxError = 0;

            for(int j = 0; j < numbers.getDoubleData().length; j++) {

                for (int i = 0; i < n - 1; i++)
                    x[i] = numbers.getDoubleData()[j][i];
                x[n - 1] = 1;

                if(j == 0)
                    targetResult = 1.0;
                else
                    targetResult = 0.0;

                double error = neuron.getError(targetResult, x);
                if (Math.abs(error) > Math.abs(maxError))
                    maxError = error;
                for (int k = 0; k < x.length; k++)
                    neuron.setWeight(k, error, 0.6, x);

            }
            finish = Math.abs(maxError) < 0.01;
            if(count%500 == 0){
                System.out.println(count);
                System.out.println(maxError);
            }
            count++;
        }

        System.out.println(count);

        double[] x = new double[n];
        for(int j = 0; j < data.getDoubleData().length; j++) {
            for (int k = 0; k < n - 1; k++)
                x[k] = data.getDoubleData()[j][k];
            x[n - 1] = 1;
            System.out.println(neuron.result(x));
        }

        System.out.println(neuron.result(1,1,1,1,0,1,1,0,1,1,0,1,1,1,1,1));
    }

}
