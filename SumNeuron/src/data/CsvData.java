package data;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class CsvData {
    private String path;
    private Scanner scanner;
    private String[][] data;
    public CsvData(String path) throws FileNotFoundException {
        this.path = path;
        scanner = new Scanner(new File(path));
        data = new String[1][1];
    }

    private String scan(){
        String result = "";

        while (true) {
            try {
                result += scanner.nextLine() + "\n";
            }
            catch (NoSuchElementException e){
                break;
            }
        }
        return result;
    }

    public CsvData read(){
        CsvData result;
        try {
            result = new CsvData(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String[] buf = scan().split("\\n");
        String[][] res = new String[buf.length][];

        for(int i = 0; i < buf.length; i++)
            res[i] = buf[i].split(",");
        result.data = res;
        return result;
    }
    public String[][] getData(){
        return data;
    }

    public double[][] getDoubleData(){
        double[][] result = new double[data.length][data[0].length];
        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                try {
                    result[i][j] = Double.parseDouble(data[i][j]);
                }catch (NumberFormatException e){
                    throw new NumberFormatException();
                }
            }
        }
        return result;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++)
                result += data[i][j] + " ";
            result += "\n";
        }
        return result;
    }
    public void close(){
        scanner.close();
    }

}
