import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    //magnet data is stored as text in the following format: round/square | name | length | split | inner | outer | corner radius (square magnets only) | density
    public static String writeSpecificLine(String fileName, String name, MagnetData data){//writes a specific line of a file
        int line = 0;
        ArrayList<String> lines = new ArrayList<>();
        try (//reads the file and stores all lines except the updated one as an arraylist
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);

        ) {
            boolean found = false;
            String nextLine = br.readLine();
            MagnetData inputData;
            while (nextLine != null){
                inputData = new MagnetData(nextLine);
                nextLine = br.readLine();
                if (!inputData.getName().equals(name)) {
                    lines.add(inputData.toString());
                } else {
                    found = true;
                }
                if(!found) {
                    line++;
                }
            }
        } catch (IOException e) {
            line = -1;
        }
        try (//updates the file with the new line at the end
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
            while (currentLine<lines.size()){
                pw.println(lines.get(currentLine));
                currentLine++;
            }
            return "Data Written Successfully";
        } catch (IOException e) {
            return null;
        }
    }
    public static String writeMagnetData(MagnetData data, String fileName){//used for testing only
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
    public static MagnetData readMagnetData(String fileName, String name){//creates a MagnetData object from a line in a file
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
        ) {
            String nextLine = br.readLine();
            MagnetData data = new MagnetData(nextLine);
            while (data != null && nextLine != null){
                if (data.getName().equals(name)) {
                    return data;
                }
                nextLine = br.readLine();
                data = new MagnetData(nextLine);
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
    public static String[] readMagnetArray(String name){//returns the names of the magnets in a file
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
    public static String write3dArray(String[][][] a, String name){//writes a 3d array to a file
        try (
                FileWriter fw = new FileWriter(name+".txt");
                PrintWriter pw = new PrintWriter(fw);
        ) {
            for(String[][] b: a){
                for(String[] c: b){
                    for(String d: c){
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
    public static String write2dArray(String[][] a, String name){//writes a 2d array to a file
        try (
                FileWriter fw = new FileWriter(name+".txt");
                PrintWriter pw = new PrintWriter(fw);
        ) {
            for(String[] b: a){
                for(String c: b){
                    pw.print(c);
                    pw.print('|');
                }
                pw.println();
            }
            return "data written successfully";
        } catch (IOException e) {
            return "data not written";
        }
    }
}