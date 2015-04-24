
import net.obry.ti5x.Number;

public class test1
{

    public static void main(String[] args)
    {
        Number n1, n2;

        n1 = new Number(1.0);
        n2 = new Number(2.0);

        n1.add(n2);
        n2.cos(Number.ANG_RAD);

        System.out.println("test1 - 1 : " + n1.get());
        System.out.println("test1 - 2 : " + n2.get());

        n2.fracPart();
        System.out.println("test1 - 3 : " + n2.get());

        n1.set(1.0);
        n1.intPart();
        System.out.println("test1 - 4 : " + n1.get());

        n1.set(1.1);
        n1.intPart();
        System.out.println("test1 - 5 : " + n1.get());

        n1.set(1.4);
        n1.intPart();
        System.out.println("test1 - 6 : " + n1.get());

        n1.set(1.5);
        n1.intPart();
        System.out.println("test1 - 7 : " + n1.get());

        n1.set(1.9);
        n1.intPart();
        System.out.println("test1 - 8 : " + n1.get());

        n1.set(1.999999999);
        n1.intPart();
        System.out.println("test1 - 9 : " + n1.get());
    }
}
