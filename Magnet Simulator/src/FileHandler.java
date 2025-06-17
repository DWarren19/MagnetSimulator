import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.ArrayList;

public class FileHandler {
    public static String writeMagnetData(MagnetData data){
        try (
                FileWriter fw = new FileWriter(data.getName()+"MagnetData");
                PrintWriter pw = new PrintWriter(fw);
                ) {
            pw.println(data);
            return "data written successfully";
        } catch (IOException e) {
            return "data not written";
        }
    }
    public static MagnetData readMagnetData(String fileName){
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            return new MagnetData(br.readLine());
        } catch (IOException e) {
            return null;
        }
    }
    public static String[] readMagnetArray(String name){
        try (
                FileReader fr = new FileReader(name);
                BufferedReader br = new BufferedReader(fr);
        ) {
            ArrayList<String> data = new ArrayList<>();
            String line = "null";
            while (!line.isEmpty()){
                line = br.readLine();
                int position = 0;
                while(line.charAt(position) != '|'){
                    position += 1;
                }
                data.add(line.substring(0, position));
            }
            String[] dataArray = new String[data.size()];
            for (int i = 0; i < dataArray.length; i++) {
                dataArray[i] = data.get(i);
            }
            return dataArray;
        } catch (IOException e) {
            return null;
        }
    }
    public static String write3dArray(double[][][] a, String name){
        try (
                FileWriter fw = new FileWriter(name+".txt");
                PrintWriter pw = new PrintWriter(fw);
        ) {
            for(double[][] b: a){
                for(double[] c: b){
                    for(double d: c){
                        pw.print(d);
                        pw.print('|');
                    }
                    pw.println();
                }
                pw.println();
            }
            return "data written successfully";
        } catch (IOException e) {
            return "data not written";
        }
    }
}
