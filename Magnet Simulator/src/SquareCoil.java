public class SquareCoil extends Coil {
    private MagnetSegment[] segments;
    public SquareCoil(double radius, double length, int precision, double current, double z){
        Circle corner = new Circle(radius, precision);
        double l = (Math.PI * 2)/precision;
        segments = new MagnetSegment[precision*2];
        double angle = 0;
        for(int count = 0; count <precision/4; count++){
            angle+=l;
            segments[count] = new MagnetSegment(corner.outerSideLength(), Math.sin(angle)*radius, -Math.cos(angle)*radius, z, angle, 0, current);
        }
        for(int count = 0; count <precision/4; count++){
            //not complete
            segments[count+precision/4] = new MagnetSegment(corner.outerSideLength(), Math.sin(angle)*radius, -Math.cos(angle)*radius, z, angle, 0, current);
        }
    }
}