public class RoundCoil extends Coil {
    private MagnetSegment[] segments;
    public RoundCoil(double radius, int precision, double current, double z){
        Circle approximation = new Circle(radius, precision);
        segments = new MagnetSegment[precision];
        double l = (Math.PI * 2)/precision;
        double lDeg = 360/precision;
        for(int i = 0; i<precision; i++){
            //System.out.println(Math.sin(l*i)*radius);
            //System.out.println(-Math.cos(l*i)*radius);//angle is measured from x-axis, so this angle is anticlockwise
            //System.out.println(lDeg*i);
            //System.out.println();
            segments[i] = new MagnetSegment(approximation.outerSideLength(), Math.sin(l*i)*radius, -Math.cos(l*i)*radius, z, l*i, 0, current);
        }
        super.setSegments(segments);
    }
}