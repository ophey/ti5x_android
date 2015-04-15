package net.obry.ti5x;

public class Number
{
    private double v = 0;
    private boolean error;

    public static final int ANG_RAD  = 1;
    public static final int ANG_DEG  = 2;
    public static final int ANG_GRAD = 3;
    public static final Number PI = new Number(Math.PI);
    public static final Number ONE = new Number(1.0);
    public static final Number TEN = new Number(10.0);
    public static final Number ZERO  = new Number(0.0);

    private final static double D_ZERO      = 0.0;
    private final static double HALF_PI   = Math.PI / 2.0;
    private final static double HALF3_PI  = 3.0 * Math.PI / 2.0;
    private final static double TWO_PI    = Math.PI * 2.0;
    private final static double FROM_DEG  = Math.PI / 180.0;
    private final static double FROM_RAD  = Math.PI / 200.0;
    private final static double ERROR     = 9.9999999e99;
    private final static double LOG10     = Math.log(10);
    private final static double LOG1000   = Math.log(1000);

    private final static Number N_PI       = new Number(Math.PI);
    private final static Number N_HALF_PI  = new Number(HALF_PI);
    private final static Number N_HALF3_PI = new Number(HALF3_PI);
    private final static Number N_TWO_PI   = new Number(TWO_PI);

    public Number()
    {
        v = 0.0;
        error = false;
    }

    public Number(double d)
    {
        v = d;
        error = false;
    }

    public Number(String s)
    {
        v =  Double.parseDouble (s);
        error = false;
    }

    public Number(Number n)
    {
        this.v = n.v;
        this.error = false;
    }

    public boolean isEqual(Number d)
    {
        final double Epsilon = 1e-11;

        if (Math.abs (v - d.v) <= Epsilon)
            return true;
        else
            return false;
    }

    public int compareTo(Number n)
    {
        if (v < n.v)
            return -1;
        else if (v > n.v)
            return 1;
        else
            return 0;
    }

    public int compareTo(int i)
    {
        if (v < (double)i)
            return -1;
        else if (v > (double)i)
            return 1;
        else
            return 0;
    }

    public boolean isError()
    {
        final boolean R = error;
        error = false;
        return R;
    }

    public boolean isNaN()
    {
        return Double.isNaN(v);
    }

    public boolean isInfinite()
    {
        return Double.isInfinite(v);
    }

    public void set(double d)
    {
        v = d;
        error = false;
    }

    public void set(Number n)
    {
        v = n.v;
        error = false;
    }

    public void set(String s)
    {
        v = Double.parseDouble (s);
        error = false;
    }

    public double get()
    {
        return v;
    }

    public long getInt()
    {
        final double Vabs = Math.abs(v);
        final double Epsilon =
            Vabs > 0.8
            ? 1.0 / Math.pow (10, 15 - 3 - Math.min(1, Math.abs((int)Math.floor(Math.log(Vabs) / Math.log(10.0)))))
            : 0.0;

        return (long)Math.floor(Vabs + Epsilon);
    }

    public int getSignum()
    {
        return (int)Math.signum(v);
    }

    public void signum()
    {
        v = Math.signum(v);
    }

    public void abs()
    {
        v = Math.abs(v);
    }

    public void negate()
    {
        v = -v;
    }

    public void min(long i)
    {
        double N = (double)i;
        if (v > N)
            v = N;
    }

    public void max(long i)
    {
        double N = (double)i;
        if (v < N)
            v = N;
    }

    public void add(long i)
    {
        v += (double)i;
    }

    public void add(Number n)
    {
        v += n.v;
    }

    public void sub(Number n)
    {
        v -= n.v;
    }

    public void sub(long i)
    {
        v -= (double)i;
    }

    public void mult(Number n)
    {
        v *= n.v;
    }

    public void mult(long i)
    {
        v *= (double)i;
    }

    public void div(Number n)
    {
        if (n.v == 0)
        {
            v = ERROR;
            error = true;
        }
        else
            v /= n.v;
    }

    public void div(long i)
    {
        div(new Number((double)i));
    }

    public void rem(Number n)
    {
        v = Math.IEEEremainder(v, n.v);
    }

    public void rem(long i)
    {
        v = Math.IEEEremainder(v, (double)i);
    }

    public void trigScale(int Angle)
    {
        v = trigScale2(Angle);
    }

    private static double trigScale2(int Angle)
    {
        switch (Angle)
        {
           case ANG_DEG:
               return FROM_DEG;
           case ANG_GRAD:
               return FROM_RAD;
           default:
               return 1.0;
        }
    }

    private void NormalizeAngle(int Angle)
    {
        v = v * trigScale2(Angle);

        if (v < 0)
        {
            while (v < 0.0)
                v += TWO_PI;
        }
        else if (v >= TWO_PI)
        {
            while (v >= TWO_PI)
                v -= TWO_PI;
        }
    }

    public void cos(int Angle)
    {
        NormalizeAngle(Angle);

        v = Math.cos(v);
        if (isEqual(ZERO))
            v = 0;
    }
    public void acos(int Angle)
    {
        v = Math.acos(v) / trigScale2(Angle);
    }

    public void sin(int Angle)
    {
        NormalizeAngle(Angle);

        v = Math.sin(v);
        if (isEqual(ZERO))
            v = D_ZERO;
    }
    public void asin(int Angle)
    {
        v = Math.asin(v) / trigScale2(Angle);
    }

    public void tan(int Angle)
    {
        NormalizeAngle(Angle);

        if (isEqual(N_HALF_PI) || isEqual(N_HALF3_PI))
        {
            v = ERROR;
            error = true;
        }
        else if (isEqual(ZERO) || isEqual(N_PI))
        {
            v = D_ZERO;
        }
        else
            v = Math.tan(v);
    }
    public void atan(int Angle)
    {
        v = Math.atan(v) / trigScale2(Angle);
    }

    public void log()
    {
        if (v < 0.0)
        {
            v = Math.log10(-v);
            error = true;
        }
        else if (v == 0.0)
        {
            v = -ERROR;
            error = true;
        }
        else
            v = Math.log10(v);
    }

    public void ln()
    {
        if (v < 0.0)
        {
            v = Math.log(-v);
            error = true;
        }
        else if (v == 0.0)
        {
            v = -ERROR;
            error = true;
        }
        else
            v = Math.log(v);
    }

    public void sqrt()
    {
        if (v < 0.0)
        {
            v = Math.sqrt(-v);
            error = true;
        }
        else
            v = Math.sqrt(v);
    }

    public void x2()
    {
        v *= v;
    }

    public void reciprocal()
    {
        if (v == 0)
        {
            v = ERROR;
            error = true;
        }
        else
            v = 1.0 / v;
    }

    public void pow(Number n)
    {
        v = Math.pow(v, n.v);
    }

    public void exp()
    {
        v = Math.exp(v);
    }

    public void intPart()
    {
        final double Vabs = Math.abs(v);
        final double Epsilon =
            Vabs > 0.8
            ? 1.0 / Math.pow (10, 15 - 3 - Math.min(1, Math.abs((int)Math.floor(Math.log(Vabs) / Math.log(10.0)))))
            : 0.0;
        v = Math.floor(Vabs + Epsilon);
    }

    public void fracPart()
    {
        final double i = (long)v;
        v = v - i;
    }

    public void Pi()
    {
        v = Math.PI;
    }

    /* returns the number of figures before the decimal point in the
       formatted representation of X scaled by Exp. This has to be
       at least 1, because the decimal point is part of the display
       of the preceding digit. */

    public int figuresBeforeDecimal(int Exp)
    {
        int BeforeDecimal;

        if (v != 0.0)
        {
            BeforeDecimal = Math.max
                ((int)Math.ceil(Math.log10(Math.abs(v) / Math.pow(10.0, Exp))), 1);
        }
        else
        {
            BeforeDecimal = 1;
        }

        return BeforeDecimal;
    }

    /* returns the exponent scale to display X using the given format. */

    public int scaleExp(int UsingFormat)
    {
        int Exp = 0;
        double l = Math.abs(v);
        if (l != 0.0)
        {
            switch (UsingFormat)
            {
            case State.FORMAT_FLOAT:
                Exp = (int)Math.floor(Math.log(l) / LOG10);
                break;
            case State.FORMAT_ENG:
                Exp = (int)Math.floor(Math.log(l) / LOG1000) * 3;
                break;
            }
        }
        return Exp;
    }

    public void nDigits(int n)
    {
        if (n >= 0)
        {
            final double Factor = Math.pow(10, n);
            v = (int)(v * Factor) / Factor;
        }
    }

    public String formatString(java.util.Locale locale, int nrDecimals)
    {
        return String.format(locale, String.format(locale, "%%.%df", nrDecimals), v);
    }
}
