import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public class FileHandler {
    public static String writeMagnetData(String fileName, String data){
        try (
                FileWriter fw = new FileWriter(fileName);
                PrintWriter pw = new PrintWriter(fw);
                ) {
            pw.println(data);
            return "data written successfully";
        } catch (IOException e) {
            return "data not written";
        }
    }
    public static String readMagnetData(String fileName){
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            return br.readLine();
        } catch (IOException e) {
            return "data not found";
        }
    }
}
