public class RoundMagnet {
    private RoundCoil[] coils;
    public RoundMagnet(double length, double split, double inner, double outer, double density, int precision){//measurements in cm
        coils = new RoundCoil[(int) Math.round(((length-split)*density)*(outer-inner)*(density))];//total number of windings
        int count = 0;
        for(double z = -length/2+(0.5/density); z<-split/2; z+=1/density){
            for(double r = inner+(0.5/density); r<outer; r+=1/density){
                coils[count] = new RoundCoil(r, precision, 1, z);//current is 1 to give results in magnetic field per amp
                count++;
            }
        }
        int count2 = 0;
        for(double z = split/2+(0.5/density); z<length/2; z+=1/density){
            for(double r = inner+(0.5/density); r<outer; r+=1/density){
                coils[count+count2] = new RoundCoil(r, precision, 1, z);//current is 1 to give results in magnetic field per amp
                count2++;
            }
        }
    }
    public double[] getStrength(double x, double y, double z){
        double[] total = {0, 0, 0};
        System.out.println(coils.length);//checks how many coils are being analysed (for debugging)
        System.out.println(coils[coils.length-1]);//make sure this is not null
        for(int i=0; i<coils.length; i++){
            total[0] += coils[i].getStrength(x,y,z)[0];
            total[1] += coils[i].getStrength(x,y,z)[1];
            total[2] += coils[i].getStrength(x,y,z)[2];
        }
        return total;
    }
}

