public class SquareCoil extends Coil {
    private MagnetSegment[] segments;
    public SquareCoil(double radius, double length, int precision, double current, double z){//length should always be at least twice radius
        Circle corner = new Circle(radius, precision);
        double l = (Math.PI * 2)/precision;
        segments = new MagnetSegment[precision*2];
        double angle = 0;
        boolean[][] testArray = new boolean[(int) (length)+1][(int) (length)+1];
        for(int count = 0; count <precision/4; count++) {
            angle += l;
            //this section is for testing and should be commented out
            //output an image of the coil using text
            int x = (int) ((Math.sin(angle) * radius + length / 2 - radius) + 0.5);
            System.out.println(x);
            int y = (int) ((Math.cos(angle) * radius + length / 2 - radius) + 0.5);
            System.out.println(y);
            testArray[x][y] = true;
            //
            segments[count] = new MagnetSegment(corner.outerSideLength(), Math.sin(angle) * radius + length / 2 - radius, Math.cos(angle) * radius + length / 2 - radius, z, angle, 0, current);
        }

        for(int count = 0; count <precision/4; count++){
            //this section is for testing and should be commented out
            //output an image of the coil using text
            int x = (int) ((length/2) + 0.5);
            System.out.println(x);
            int y = (int) ((count*length/precision) + 0.5);
            System.out.println(y);
            testArray[x][y] = true;
            //
            segments[count+precision/4] = new MagnetSegment(corner.outerSideLength(), length/2, length-(count*length/precision), z, angle, 0, current);
        }
        for(int count = 0; count <precision/4; count++){
            angle+=l;
            segments[count+precision/2] = new MagnetSegment(corner.outerSideLength(), Math.sin(angle)*radius+length/2-radius, -Math.cos(angle)*radius+length/2-radius, z, angle, 0, current);
        }
        for(int count = 0; count <precision/4; count++){
            segments[count+precision/4] = new MagnetSegment(corner.outerSideLength(), count*length/precision, length-(count*length/precision), z, angle, 0, current);
        }
        //
        for (boolean[] list: testArray){
            for (boolean b: list){
                if (b){
                    System.out.print("###");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        //
    }
}