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
        boolean[][] testArray = new boolean[(int) (length+1.5)][(int) (length+1.5)];
        int total = 0;
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner +x +y
            angle += l;
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), Math.sin(angle) * radius + length / 2 - radius, Math.cos(angle) * radius + length / 2 - radius, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        angle = Math.PI/2;
        //side +x
        for(int count = 0; count <straightSegments/4; count++){
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), length/2, length/2-(4*count*(length-2*radius)/straightSegments)-radius, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner +x -y
            angle += l;
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), Math.sin(angle) * radius + length / 2 - radius, Math.cos(angle) * radius - length / 2 + radius, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        angle = Math.PI;
        //System.out.println(Math.toDegrees(angle));
        for(int count = 0; count <straightSegments/4; count++){
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), length/2-(4*count*(length-2*radius)/straightSegments)-radius, length/2, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner -x -y
            angle += l;
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), (Math.sin(angle) * radius + length / 2 + radius)-length, Math.cos(angle) * radius - length / 2 + radius, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        angle = 3*Math.PI/2;
        //System.out.println(Math.toDegrees(angle));
        for(int count = 0; count <straightSegments/4; count++){
            //side -x
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), -length/2, length/2-(4*count*(length-2*radius)/straightSegments)-radius, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner -x +y
            angle += l;
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), Math.sin(angle) * radius + length / 2 + radius - length, Math.cos(angle) * radius + length / 2 - radius, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
        //System.out.println(Math.toDegrees(angle));
        for(int count = 0; count <straightSegments/4; count++){
            MagnetSegment next = new MagnetSegment(corner.outerSideLength(), length/2-(4*count*(length-2*radius)/straightSegments)-radius, length, z, -angle, 0, current);
            segments = checkArray(segments, next);
            total++;
        }
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