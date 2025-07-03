public class MagnetData {
    private boolean square;
    private String name;
    private double[] data;
    //length, split, inner, outer, density, precision
    public MagnetData(double[] d, boolean s, String n){
        data = d;
        name = n;
        square = s;
    }
    public double[] getData(){
        return data;
    }
    public MagnetData(String inputData) {
        int dataPosition = 0;
        int strPosition;
        name = "";
        for (strPosition = 2; strPosition < inputData.length(); strPosition++) {
            if(inputData.charAt(strPosition)=='|'){
                strPosition++;
                break;
            } else {
                name = name.concat(Character.toString(inputData.charAt(strPosition)));
            }
        }
        if (inputData.charAt(0) == '0'){
            square = false;
            data = new double[5];
            String currentValue = "";
            for (;strPosition < inputData.length(); strPosition++) {
                if(inputData.charAt(strPosition)=='|'){
                    data[dataPosition] = Double.parseDouble(currentValue);
                    currentValue = "";
                    dataPosition++;
                    if(dataPosition == 6){
                        break;
                    }
                } else {
                    currentValue = currentValue.concat(Character.toString(inputData.charAt(strPosition)));
                }
            }
        } else {
            square = true;
            data = new double[6];
            String currentValue = "";
            for (;strPosition < inputData.length(); strPosition++) {
                if(inputData.charAt(strPosition)=='|'){
                    data[dataPosition] = Double.parseDouble(currentValue);
                    currentValue = "";
                    dataPosition++;
                    if(dataPosition == 7){
                        break;
                    }
                } else {
                    currentValue = currentValue.concat(Character.toString(inputData.charAt(strPosition)));
                }
            }
        }
    }
    public String toString(){
        String outputData = "";
        for (double d: data){
            outputData+=d;
            outputData+="|";
        }
        if (square){
            return "1"+"|"+name+"|"+outputData;
        } else {
            return "0"+"|"+name+"|"+outputData;
        }
    }
    public String getName(){
        return name;
    }
    public void setName(String s){
        name = s;
    }
}
