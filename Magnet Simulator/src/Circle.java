public class Circle {// this class represents an approximation of a circle to make shapes that are approximately round
    private double radius;
    private double precision;
    private double baseAngle;
    public Circle(double r, double p){
        radius = r;
        precision = p;
        baseAngle = Math.PI*((precision-2)/(2*precision));
    }
    public double circumference(){
        //System.out.println(precision-2);
        //System.out.println((precision-2)/precision);
        //System.out.println((precision-2)/(2*precision));
        //System.out.println(baseAngle);
        return ((radius*Math.sin(2*Math.PI/precision))/Math.sin(baseAngle))*precision;
    }
    public double sideLength(){//works out how long the sides of the shape are
        return ((radius*Math.sin(2*Math.PI/precision))/Math.sin(baseAngle));
    }
    public double outerSideLength(){// works out how long the sides of the bounding shape are
        double angle = Math.PI*(1-2/precision);
        return(2*Math.sin(Math.PI/2-baseAngle)*sideLength()/Math.sin(angle));
    }
}
