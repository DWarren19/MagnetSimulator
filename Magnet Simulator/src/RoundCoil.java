public class RoundCoil {
    private MagnetSegment[] segments;
    private Circle approximation;

    public RoundCoil(double radius, int precision, double current, double z){
        approximation = new Circle(radius, precision);
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
    }
    public double[] getStrength(double x, double y, double z){
        double total[] = {0,0,0};
        for(int i = 0; i<segments.length; i++){
            //segments[i].printList(segments[i].getStrength(x,y,z));
            total[0] += segments[i].getStrength(x,y,z)[0];
            total[1] += segments[i].getStrength(x,y,z)[1];
            total[2] += segments[i].getStrength(x,y,z)[2];
        }
        return total;
    }

}
