import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static String writeSpecificLine(String fileName, String name, MagnetData data){
        int line = 0;
        ArrayList<String> lines = new ArrayList<>();
        //File magnetDataFile = new File("Documents/Magnet Data/"+fileName);
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);

        ) {
            String nextLine = br.readLine();
            MagnetData inputData;
            while (nextLine != null){
                inputData = new MagnetData(nextLine);
                nextLine = br.readLine();
                if (!inputData.getName().equals(name)) {
                    lines.add(inputData.toString());
                }
                line++;
                //System.out.println(nextLine);
            }
        } catch (IOException e) {
            line = -1;
        }
        try (
                FileWriter fw = new FileWriter(fileName);
                PrintWriter pw = new PrintWriter(fw);
        ) {
            int currentLine = 0;
            while (line != currentLine && currentLine<lines.size()){
                pw.println(lines.get(currentLine));
                currentLine++;
            }
            if (data != null) {
                pw.println(data);
            }
            currentLine++;
            while (currentLine<lines.size()){
                pw.println(lines.get(currentLine));
                currentLine++;
            }
            return "Data Written Successfully";
        } catch (IOException e) {
            return null;
        }
    }
    public static String writeMagnetData(MagnetData data, String fileName){
        String currentData = "";
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            for (String nextLine = br.readLine(); nextLine != null; nextLine = br.readLine()) {
                currentData = currentData.concat(nextLine+'\n');
            }
        } catch (IOException e) {
            return "data not written";
        }
        try (
                FileWriter fw = new FileWriter(fileName);
                PrintWriter pw = new PrintWriter(fw);
                ) {
            pw.println(currentData+data.toString());
            return "data written successfully";
        } catch (IOException e) {
            return "data not written";
        }
    }
    public static MagnetData readMagnetData(String fileName, String name){
        //File magnetDataFile = new File("Documents/Magnet Data/"+fileName);
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            MagnetData data = new MagnetData(br.readLine());
            while (data != null){
                if (data.getName().equals(name)) {
                    return data;
                }
                data = new MagnetData(br.readLine());
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    public static String[] readMagnetArray(String name){
        //File magnetDataFile = new File("Documents/Magnet Data/"+name);
        try (
                FileReader fr = new FileReader(name);
                BufferedReader br = new BufferedReader(fr);
        ) {
            ArrayList<String> data = new ArrayList<>();
            String line = br.readLine();
            while (line != null){
                int startPosition = 0;
                int position = 0;
                while(line.charAt(position) != '|'){
                    startPosition += 1;
                    position += 1;
                }
                startPosition += 1;
                position += 1;
                while(line.charAt(position) != '|'){
                    position += 1;
                }
                data.add(line.substring(startPosition, position));
                line = br.readLine();
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
        //File magnetDataFile = new File("Documents/"+name);
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