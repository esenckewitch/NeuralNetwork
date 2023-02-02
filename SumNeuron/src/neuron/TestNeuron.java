package neuron;

public class TestNeuron{

    public static void main(String[] args) {

        double[][] data = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 0},
                {1, 1, 1}
        };

        int n = 3;
        Neuron neuron = new NeuronWithSigmoidFunction(n);

        System.out.println(neuron);
        System.out.printf("0 0 %.6f\n", neuron.result(0, 0, 1));
        System.out.printf("0 1 %.6f\n", neuron.result(0, 1, 1));
        System.out.printf("1 0 %.6f\n", neuron.result(1, 0, 1));
        System.out.printf("1 1 %.6f\n", neuron.result(1, 1, 1));
        int count = 0;
        boolean finish = false;
        while(!finish){

            double targetResult;
            double[] x = new double[n];
            double maxError = 0;

            for(int j = 0; j < data.length; j++){
                for(int k = 0; k < n-1; k++)
                    x[k] = data[j][k];
                x[n-1] = 1;

                targetResult = data[j][2];

                double error = neuron.getError(targetResult, x);

                if(Math.abs(error) > Math.abs(maxError))
                    maxError = error;

                for(int k = 0; k < x.length; k++)
                    neuron.setWeight(k, error, 0.3, x);
            }
            finish = Math.abs(maxError) < 0.001;
            count++;
        }

        System.out.println(neuron);
        System.out.printf("0 0 %.6f\n", neuron.result(0, 0, 1));
        System.out.printf("0 1 %.6f\n", neuron.result(0, 1, 1));
        System.out.printf("1 0 %.6f\n", neuron.result(1, 0, 1));
        System.out.printf("1 1 %.6f\n", neuron.result(1, 1, 1));
        System.out.println(count);
    }
}
