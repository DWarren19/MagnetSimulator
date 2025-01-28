public class VectorHandler {
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
    public static double[] vectorMultiply(double x1, double y1, double z1, double x2, double y2, double z2){//
        double[] vectorProduct = {y1*z2-z1*y2, z1*x2-x1*z2, x1*y2-y1*x2};
        return vectorProduct;
    }
    public static void printList(double[] l){//
        for(double n: l){
            System.out.println(n);
        }
    }
}