public class RoundCoil extends Coil {
    private MagnetSegment[] segments;
    public RoundCoil(double radius, int precision, double current, double z){
        Circle approximation = new Circle(radius, precision);
        segments = new MagnetSegment[precision];
        double l = (Math.PI * 2)/precision;
        double lDeg = 360/precision;
        for(int i = 0; i<precision; i++){//calculates the position and angle of every segment
            segments[i] = new MagnetSegment(approximation.outerSideLength(), Math.sin(l*i)*radius, -Math.cos(l*i)*radius, z, l*i, 0, current);
        }
        super.setSegments(segments);
    }
}