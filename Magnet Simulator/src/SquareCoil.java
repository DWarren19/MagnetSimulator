public class SquareCoil extends Coil {
    private MagnetSegment[] segments;
    public SquareCoil(double radius, double length, int precision, double current, double z){//length should always be at least twice radius
        Circle corner = new Circle(radius, precision);
        double curvedLength = Math.PI*radius*2;
        double straightLength = length*4-8*radius;
        double curvedSegments = (precision*curvedLength)/(curvedLength+straightLength);
        double straightSegments = (precision*straightLength)/(curvedLength+straightLength);
        double l = (Math.PI * 2)/curvedSegments;
        segments = new MagnetSegment[1];
        double angle = 0;
        boolean[][] testArray = new boolean[(int) (length)+1][(int) (length)+1];
        int total = 0;
        for(int count = 0; count <curvedSegments/4; count++) {
            angle += l;
            //this section is for testing and should be commented out
            //output an image of the coil using text
            // +0.5 is used to round values
            // +length/2 is used to make all values positive
            int x = (int) ((Math.sin(angle) * radius + length / 2 - radius) + 0.5   +length/2);
            System.out.println(x);
            int y = (int) ((Math.cos(angle) * radius + length / 2 - radius) + 0.5   +length/2);
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), Math.sin(angle) * radius + length / 2 - radius, Math.cos(angle) * radius + length / 2 - radius, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        System.out.println(Math.toDegrees(angle));
        for(int count = 0; count <straightSegments/4; count++){
            //this section is for testing and should be commented out
            //output an image of the coil using text
            int x = (int) ((length/*/2 should be added here, but this will result in some negative values*/) + 0.5);
            System.out.println(x);
            int y = (int) (length/*/2*/-(4*count*(length-2*radius)/straightSegments)-radius + 0.5);
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), length/2, length/2-(4*count*(length-2*radius)/straightSegments)-radius, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        angle = Math.PI/2;
        for(int count = 0; count <curvedSegments/4; count++) {
            angle += l;
            //this section is for testing and should be commented out
            //output an image of the coil using text
            // +0.5 is used to round values
            // +length/2 is used to make all values positive
            int x = (int) ((Math.sin(angle) * radius + length / 2 - radius) + 0.5   +length/2);
            System.out.println(x);
            int y = (int) ((Math.cos(angle) * radius - length / 2 + radius) + 0.5   +length/2);
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), Math.sin(angle) * radius + length / 2 - radius, Math.cos(angle) * radius - length / 2 + radius, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        System.out.println(Math.toDegrees(angle));
        for(int count = 0; count <straightSegments/4; count++){
            //this section is for testing and should be commented out
            //output an image of the coil using text
            int x = (int) (length/*/2*/-(4*count*(length-2*radius)/straightSegments)-radius);
            System.out.println(x);
            int y = (int) ((length/*/2 should be added here, but this will result in some negative values*/) + 0.5);
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), length/2-(4*count*(length-2*radius)/straightSegments)-radius, length/2, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        angle = Math.PI;
        for(int count = 0; count <curvedSegments/4; count++) {
            angle += l;
            //this section is for testing and should be commented out
            //output an image of the coil using text
            // +0.5 is used to round values
            // +length/2 is used to make all values positive
            int x = (int) ((Math.sin(angle) * radius + length / 2 + radius) + 0.5   -length/2);
            System.out.println(x);
            int y = (int) ((Math.cos(angle) * radius - length / 2 + radius) + 0.5   +length/2);
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), (Math.sin(angle) * radius + length / 2 + radius)-length, Math.cos(angle) * radius - length / 2 + radius, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        System.out.println(Math.toDegrees(angle));
        for(int count = 0; count <straightSegments/4; count++){
            //this section is for testing and should be commented out
            //output an image of the coil using text
            int x = 0;//should be -length/2
            System.out.println(x);
            int y = (int) (length/*/2*/-(4*count*(length-2*radius)/straightSegments)-radius + 0.5);
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), -length/2, length/2-(4*count*(length-2*radius)/straightSegments)-radius, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        angle = 3*Math.PI/2;
        for(int count = 0; count <curvedSegments/4; count++) {
            angle += l;
            //this section is for testing and should be commented out
            //output an image of the coil using text
            // +0.5 is used to round values
            // +length/2 is used to make all values positive
            int x = (int) ((Math.sin(angle) * radius + length / 2 + radius) + 0.5   -length/2);
            System.out.println(x);
            int y = (int) ((Math.cos(angle) * radius + length / 2 - radius) + 0.5   +length/2);
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), Math.sin(angle) * radius + length / 2 + radius - length, Math.cos(angle) * radius + length / 2 - radius, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        System.out.println(Math.toDegrees(angle));
        for(int count = 0; count <straightSegments/4; count++){
            //this section is for testing and should be commented out
            //output an image of the coil using text
            int x = (int) (length/*/2*/-(4*count*(length-2*radius)/straightSegments)-radius);
            System.out.println(x);
            int y = 0;//should be -length/2
            System.out.println(y);
            testArray[x][y] = true;
            //
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), length/2-(4*count*(length-2*radius)/straightSegments)-radius, -length/2, z, angle, 0, current);
            segments = checkArray(segments, next);
            total++;
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
        System.out.println(segments.length);
        //
        //System.out.println(segments[segments.length-150]);
        super.setSegments(segments);
    }
    //replace with Queue?
    public MagnetSegment[] checkArray(MagnetSegment[] array, MagnetSegment next){
        if (array[array.length-1] == null){
            array[array.length-1] = next;
            return array;
        } else {
            MagnetSegment[] temp = new MagnetSegment[array.length+1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            temp[array.length] = next;
            return temp;
        }
    }
}