//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //RoundMagnet test = new RoundMagnet(4.3, 0.6, 1.52, 2.195, 52.33296, 100);
        //MagnetSegment.printList(test.getStrength(0,0.1,0.1));
        SquareCoil test = new SquareCoil(3, 100, 400, 1, 0);
        test.getStrength(0,0,0);
        /*
        for(double r = 0; r<=2; r+=0.1){
            for(double z = 0; z<=2; z+=0.1){
                double[] magnetStrength = test.getStrength(0,r,z);
                System.out.print((double)((int)(z*10))/10 + " ");
                System.out.print((double)((int)(r*10))/10 + " ");
                if(magnetStrength[1] > 0.00000000001) {
                    System.out.print((int)(magnetStrength[1]*1000000) + " ");
                } else {
                    System.out.print("0000 ");
                }
                System.out.print((int)(magnetStrength[2]*1000000) + " ");
                System.out.println(VectorHandler.toVector(magnetStrength));
            }
        }
        */
    }
}