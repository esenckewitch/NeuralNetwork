package neuron;

public class NeuronWithSigmoidFunction extends Neuron{

    public NeuronWithSigmoidFunction(int n) {
        super(n);
    }

    @Override
    public double activate(double... x) {
        double sum = sum(x);
        return 1.0 / (1.0 + Math.pow(Math.E, -sum));
    }

    public double derivative(double... x){
        return activate(x) * (1.0 - activate(x));
    }

    @Override
    public void setWeight(int i, double error, double L, double... x) {
        double newValue = getWeight(i) - error * x[i] * L * derivative(x);
        setWeight(i, newValue);
    }
}
