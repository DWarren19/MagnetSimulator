//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RoundMagnet test = new RoundMagnet(0.04, 0.02, 0.02, 0.03, 1000, 10000);
        MagnetSegment.printList(test.getStrength(0,0,0));
    }
}