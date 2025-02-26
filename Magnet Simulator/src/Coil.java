public class Coil {
    private MagnetSegment[] segments;

    public Coil(){
    }
    public void setSegments(MagnetSegment[] s){
        segments = s;
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
