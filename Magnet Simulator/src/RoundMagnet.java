public class RoundMagnet {
    private Coil[] coils;
    public RoundMagnet(double length, double split, double inner, double outer, double density, int precision){
        //measurements in cm
        //length = total length including split
        //inner is the inner radius
        //outer is the outer radius
        //density is per cm (take the square root of density per cm**2)
        int sizeZ = (int)Math.round((length-split)*density);
        double incrementZ = (length-split)/sizeZ;
        //System.out.println(density);
        //System.out.println(1/incrementZ);
        int sizeR = (int)Math.round((outer-inner)*density);
        double incrementR = (outer-inner)/sizeR;
        double windings = ((length-split)*density)*((outer-inner)*density);
        double current = windings/(sizeZ*sizeR);//current changes to account for nonexistent windings-some windings will have been lost due to rounding errors
        coils = new Coil[sizeZ*sizeR];//total number of windings
        int count = 0;
        for(double z = -length/2+(0.5*incrementZ); z<-split/2; z+=incrementZ){
            for(double r = inner+(0.5*incrementR); r<outer; r+=incrementR){
                coils[count] = new RoundCoil(r, precision, current, z);
                count++;
            }
        }
        int count2 = 0;
        for(double z = split/2+(0.5*incrementZ); z<length/2; z+=incrementZ){
            for(double r = inner+(0.5*incrementR); r<outer; r+=incrementR){
                coils[count+count2] = new RoundCoil(r, precision, current, z);//current is 1 to give results in magnetic field per amp
                count2++;
            }
        }
    }
    public RoundMagnet(double length, double split, double inner, double outer, double density, int precision, double initialRadius){
        //inner is the inner length (radius*2)
        //outer is the outer length (radius*2)
        //initialRadius is the radius of the inside of the corners
        int sizeZ = (int)Math.round((length-split)*density);
        double incrementZ = (length-split)/sizeZ;
        int sizeR = (int)Math.round((outer-inner)*density/2);//density is halved because inner and outer represent the length, not radius, in this constructor
        double incrementR = (outer-inner)/sizeR;
        double windings = ((length-split)*density)*((outer-inner)*density/2);
        double current = windings/(sizeZ*sizeR);//current changes to account for nonexistent windings-some windings will have been lost due to rounding errors
        coils = new Coil[sizeZ*sizeR];//total number of windings
        int count = 0;
        for(double z = -length/2+(0.5*incrementZ); z<-split/2; z+=incrementZ){
            for(double l = (0.5*incrementR); l<outer-inner; l+=incrementR){
                coils[count] = new SquareCoil(initialRadius+l/2, l+inner, precision, -current, z);
                count++;
            }
        }
        int count2 = 0;
        for(double z = split/2+(0.5*incrementZ); z<length/2; z+=incrementZ){
            for(double l = (0.5*incrementR); l<outer-inner; l+=incrementR){
                coils[count+count2] = new SquareCoil(initialRadius+l/2, l+inner, precision, -current, z);
                count2++;
            }
        }
    }
    public double[] getStrength(double x, double y, double z){
        double[] total = {0, 0, 0};
        //System.out.println(coils.length);//checks how many coils are being analysed (for debugging)
        //System.out.println(coils[coils.length-1]);//make sure this is not null
        for(int i=0; i<coils.length; i++) {
            total[0] += coils[i].getStrength(x, y, z)[0];
            total[1] += coils[i].getStrength(x, y, z)[1];
            total[2] += coils[i].getStrength(x, y, z)[2];
        }
        return total;
    }
}

