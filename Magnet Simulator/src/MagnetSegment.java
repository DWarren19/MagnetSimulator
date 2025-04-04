public class MagnetSegment {
    private double length;

    private double angleXY;//the angle of r on the XY plane, measured from the x-axis
    private double angleXZ;//the angle of r on the XZ plane, measured from the x-axis
    //the length in the x, y and z directions
    private double lengthX;
    private double lengthY;
    private double lengthZ;
    //the position of the segment
    private double x;
    private double y;
    private double z;

    private double current;
    public MagnetSegment(double l,double x,double y,double z,double angleXY, double angleXZ, double i){
        length = l;
        current = i;
        this.x=x;
        this.y=y;
        this.z=z;
        this.angleXY = angleXY;
        this.angleXZ = angleXZ;
        lengthX = VectorHandler.resolveVector(length, angleXY, false);
        lengthY = VectorHandler.resolveVector(length, angleXY, true);
        lengthZ = VectorHandler.resolveVector(lengthX, angleXZ, true);
        lengthX = VectorHandler.resolveVector(lengthX, angleXZ, false);
    }
    public double[] getStrength(double pointX, double pointY, double pointZ){
        double rSquared = Math.pow(pointX-x,2)+Math.pow(pointY-y,2)+Math.pow(pointZ-z,2);
        double[] direction = {pointX-x, pointY-y, pointZ-z};
        double r = VectorHandler.toVector(direction);
        for(int i = 0; i<3; i++){
            direction[i] = (direction[i]/r);
        }
        //System.out.println(r);
        double[] total = VectorHandler.vectorMultiply(lengthX, lengthY, lengthZ, direction[0], direction[1], direction[2]);
        //printList(total);
        for(int i = 0; i<3; i++){
            total[i] = (total[i]*current/Math.pow(r,2))*Math.pow(10, -7);
        }
        return total;
    }
    public double getStrength2(double pointX, double pointY, double pointZ, double angle){//
        double rSquared = Math.pow(pointX-x,2)+Math.pow(pointY-y,2)+Math.pow(pointZ-z,2);
        double total = length;
        total = (total*current/rSquared)*Math.pow(10, -7)*Math.sin(Math.toRadians(angle));
        return total;
    }
    public static double toVector(double[] d){//
        return(Math.sqrt(Math.pow(d[0],2)+Math.pow(d[1],2)+Math.pow(d[2],2)));
    }
    public static double resolveVector(double l, double angle, boolean opposite){//
//        angle = Math.toRadians(angle);
        if(opposite){
            return(Math.sin(angle)*l);
        } else {
            return(Math.cos(angle)*l);
        }
    }
    public static void printList(double[] l){//
        for(double n: l){
            System.out.println(n);
        }
    }
}

