package neuron;
public class Neuron implements AbstractNeuron{
    private double[] weight;
    public Neuron(int n){
        weight = new double[n];
        for(int i = 0; i < n; i++){
            weight[i] = Math.random() * 0.02 - 0.01;
        }
    }
    public double getWeight(int i) {
        return weight[i];
    }

    //Производная (targetResult - Y) по Y
    public double getError(double targetValue, double... x){
        double y = result(x);
        double error = -(targetValue - y);
        return error;
    }

    public void setWeight(int i, double value){
        weight[i] = value;
    }

    //Каррекция веса в зависимости он ошибки методом градиентного спуска
    public void setWeight(int i, double error, double L, double... x){
        double newValue = getWeight(i) - error * x[i] * L;
        setWeight(i, newValue);
    }

    //Сумма весов нейрона
    public double sum(double... x){
        double sum = 0.0;
        for (int i = 0; i < weight.length; i++)
            sum += weight[i] * x[i];
    return sum;
    }
    public double derivative(double... x) {
        return 0;
    }
    //Пороговая функция активации
    public double activate(double... x){
        double sum = sum(x);
        if(sum >= 5)
            return 1.0;
        return 0.0;
    }

    public double result(double... x){
        return activate(x);
    }

    @Override
    public String toString(){
        String string = "";
        for(int i = 0; i < weight.length; i++){
            string += String.format("w%d:%8.4f ", i, weight[i]);
        }
        //string += "\n";
        return string;
    }
}
