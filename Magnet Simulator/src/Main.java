//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RoundMagnet test = new RoundMagnet(10, 0, 20, 30, 0.2, 100);
        RoundMagnet test2 = new RoundMagnet(10, 0, 40, 60, 0.2, 100, 20);
        VectorHandler.printList(test2.getStrength(0,0,0));
        VectorHandler.printList(test.getStrength(0,0,0));

        SquareCoil2 test3 = new SquareCoil2(20, 40, 100, -1, 0);
        RoundCoil test4 = new RoundCoil(20, 100,1, 0);
        VectorHandler.printList(test3.getStrength(1,1,1));
        VectorHandler.printList(test4.getStrength(1,1,1));
        /*
        for(double r = 0; r<=2; r+=0.1){
            for(double z = 0; z<=2; z+=0.1){
                double[] magnetStrength = test.getStrength(0,r,z);
                System.out.print((double)((int)(z*10+0.5))/10 + " ");
                System.out.print((double)((int)(r*10+0.5))/10 + " ");
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