
// Test random from original TI

import net.obry.ti5x.Number;
import net.obry.ti5x.State;

public class test2
{

    public static void main(String[] args)
    {
        Number n1, n2, n3;
        Number r9;
        Number x;
        Number e5;
        Number Pi = new Number();
        Pi.Pi();
        Number n6 = new Number(5);

        // test 1 - RANDOM

        n1 = new Number(24298);
        n2 = new Number(99991);
        n3 = new Number(199017);
        r9 = new Number();
        x = new Number();
        e5 = new Number();

        e5.set(10);
        e5.pow(n6);
        r9.set(Pi);

        for (int k=1; k<3; k++)
        {
            x.set(n1);
            x.mult(r9);
            x.add(n2);

            r9.set(n3);
            x.div(n3);

            x.fracPart();
            r9.mult(x);
            x.mult(e5);
            x.intPart();
            x.div(e5);

            System.out.println("test - " + k + " " + x.get());
        }

        Number orig = new Number(10);
        Number copy = new Number(orig);
        copy.x2();
        System.out.println("orig=" + orig.get());
        System.out.println("copy=" + copy.get());


        // test 2 - TRIG

        x.set(90);
        x.cos(Number.ANG_DEG);
        System.out.println("cos(90)=" + x.get());
        x.set(45);
        x.cos(Number.ANG_DEG);
        System.out.println("cos(45)=" + x.get());
        x.set(Number.PI);
        x.cos(Number.ANG_RAD);
        System.out.println("cos(pi)=" + x.get());

        x.set(8);
        x.sqrt();
        System.out.println("sqrt(8)=" + x.get() + " " + x.isError());
        x.set(0);
        x.sqrt();
        System.out.println("sqrt(0)=" + x.get() + " " + x.isError());
        x.set(-5);
        x.sqrt();
        System.out.println("sqrt(-5)=" + x.get() + " " + x.isError());

        // test 3 - POW/LOG

        x.set(98765);
        x.log();
        System.out.println("log(98765)=" + x.get() + " " + x.isError());
        x.set(98765);
        x.ln();
        System.out.println("ln(98765)=" + x.get() + " " + x.isError());

        x.set(10);
        n6.set(2.5);
        x.pow(n6);
        System.out.println("pow(2.5)=" + x.get() + " " + x.isError());  // ?? wrong value should be 316.2277660141

        // test 4 - PRECISION

        Number one = new Number(1.0);
        Number lc = new Number(0.05);
        Number n4 = new Number(0.1);
        Number n5 = new Number(1);

        for (int k=0; k<100; k++)
            {
                one.add(lc);
                one.add(n4);
                one.sub(lc);
            }

        n4.set(0.1 * 100);
        one.sub(n4);

        if (one.isEqual(n5))
            System.out.println("one = " + one.get());
        else
            System.out.println("not one = " + one.get());

        // test 5 - figures

        x.set(123.456);
        System.out.println("fbd (123.456) = " + x.figuresBeforeDecimal(0));
        System.out.println("fbd (123.456) = " + x.figuresBeforeDecimal(1));
        System.out.println("fbd (123.456) = " + x.figuresBeforeDecimal(2));
        System.out.println("fbd (123.456) = " + x.figuresBeforeDecimal(3));

        x.set(9.76);
        System.out.println("sexp (9.76) = " + x.scaleExp(State.FORMAT_ENG));
        System.out.println("sexp (9.76) = " + x.scaleExp(State.FORMAT_FLOAT));
        x.set(69.76);
        System.out.println("sexp (69.76) = " + x.scaleExp(State.FORMAT_ENG));
        System.out.println("sexp (69.76) = " + x.scaleExp(State.FORMAT_FLOAT));
        x.set(169.76);
        System.out.println("sexp (169.76) = " + x.scaleExp(State.FORMAT_ENG));
        System.out.println("sexp (169.76) = " + x.scaleExp(State.FORMAT_FLOAT));
        x.set(8169.76);
        System.out.println("sexp (8169.76) = " + x.scaleExp(State.FORMAT_ENG));
        System.out.println("sexp (8169.76) = " + x.scaleExp(State.FORMAT_FLOAT));

        // test 6 - cut to given decimal

        x.set(123.456789);
        x.nDigits(1);
        System.out.println("nDigits (1) = " + x.get());
        x.set("123.456789");
        x.nDigits(3);
        System.out.println("nDigits (3) = " + x.get());

        // test 7 - reminder

        x.set(123.456789);
        x.rem(23);
        System.out.println("123.456789 rem 23 = " + x.get());

        for (int k=0; k<8; k++)
            System.out.println("f" + k + ": " + x.formatString(java.util.Locale.US, k));

        x.set(-6.987654);
        for (int k=0; k<8; k++)
            System.out.println("f" + k + ": " + x.formatString(java.util.Locale.US, k));

        x.set(1.1);
        System.out.println(">" + x.formatString(java.util.Locale.US, 8));
        x.set(12.12);
        System.out.println(">" + x.formatString(java.util.Locale.US, 8));
        x.set(123.123);
        System.out.println(">" + x.formatString(java.util.Locale.US, 8));
        x.set(1234.1234);
        System.out.println(">" + x.formatString(java.util.Locale.US, 8));
        x.set(12345.12345);
        System.out.println(">" + x.formatString(java.util.Locale.US, 8));
        x.set(123456.123456);
        System.out.println(">" + x.formatString(java.util.Locale.US, 8));
    }
}
