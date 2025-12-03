public class Coil {//a single round or square loop of wire in a solenoid
    private MagnetSegment[] segments;

    public Coil(){//no parameters are used because the segments are created at the end of the inheritors' constructors (so SetSegments is used instead)
    }
    public Coil(MagnetSegment[] s){
        segments = s;
    }
    public void setSegments(MagnetSegment[] s){
        segments = s;
    }
    public double[] getStrength(double x, double y, double z){//gets the magnetic field strength due to this coil at a specific point
        double[] total = {0,0,0};
        for(int i = 0; i<segments.length; i++){//loops through every segment in the coil
            double[] magneticFieldStrength = segments[i].getStrength(x, y, z);
            total[0] += magneticFieldStrength[0];
            total[1] += magneticFieldStrength[1];
            total[2] += magneticFieldStrength[2];
        }
        return total;
    }
}
