import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

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
