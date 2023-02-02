package data;

public class DataActions {
    public static double average(double... x){
        double average = 0.0;
        for(int i = 0; i < x.length; i++)
            average += x[i];
        return average / x.length;
    }

    public static double deviation(double... x){
        double deviation;
        double average = average(x);
        double sum = 0.0;

        for(int i = 0; i < x.length; i++)
            sum += (x[i] - average) * (x[i] - average);

        deviation = Math.sqrt(sum) / Math.sqrt(x.length - 1.0);
        return deviation;
    }

    private double[] standartization(double... x){
        double[] z = new double[x.length];
        double average = average(x);
        double deviation = deviation(x);
        for(int i = 0; i < z.length; i++)
            z[i] = (x[i] - average) / deviation;
        return z;
    }
}
