//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*RoundMagnet test = new RoundMagnet(1, 0, 50, 51, 2, 100);
        RoundMagnet test2 = new RoundMagnet(1, 0, 100, 102, 2, 100, 25);
        VectorHandler.printList(test2.getStrength(0,0,0));
        VectorHandler.printList(test.getStrength(0,0,0));
        */
        SquareCoil2 test3 = new SquareCoil2(25, 50, 100, 1, 0);
        RoundCoil test4 = new RoundCoil(12.5, 100,1, 0);
        VectorHandler.printList(test3.getStrength(0,0,0));
        VectorHandler.printList(test4.getStrength(0,0,0));
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