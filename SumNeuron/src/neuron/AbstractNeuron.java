package neuron;

public interface AbstractNeuron {
    double getWeight(int i);
    void setWeight(int i, double value);
    double sum(double... x);
    double activate(double... x);
    double derivative(double... x);
    double result(double... x);
    @Override
    String toString();
}
