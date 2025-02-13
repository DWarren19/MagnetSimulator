public class RoundMagnet {
    private RoundCoil[] coils;
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
        coils = new RoundCoil[sizeZ*sizeR];//total number of windings
        int count = 0;
        for(double z = -length/2+(0.5*incrementZ); z<-split/2; z+=incrementZ){
            for(double r = inner+(0.5*incrementR); r<outer; r+=incrementR){
                coils[count] = new RoundCoil(r, precision, 1, z);//current is 1 to give results in magnetic field per amp
                count++;
            }
        }
        int count2 = 0;
        for(double z = split/2+(0.5*incrementZ); z<length/2; z+=incrementZ){
            for(double r = inner+(0.5*incrementR); r<outer; r+=incrementR){
                coils[count+count2] = new RoundCoil(r, precision, 1, z);//current is 1 to give results in magnetic field per amp
                count2++;
            }
        }
    }
    public double[] getStrength(double x, double y, double z){
        double[] total = {0, 0, 0};
        //System.out.println(coils.length);//checks how many coils are being analysed (for debugging)
        //System.out.println(coils[coils.length-1]);//make sure this is not null
        for(int i=0; i<coils.length; i++){
            total[0] += coils[i].getStrength(x,y,z)[0];
            total[1] += coils[i].getStrength(x,y,z)[1];
            total[2] += coils[i].getStrength(x,y,z)[2];
        }
        return total;
    }
}

