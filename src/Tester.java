
package net.obry.ti5x;
/*
    The calculation state, number entry and programs.

    Copyright 2015 Pascal Obry <pascal@obry.net>.

    This program is free software: you can redistribute it and/or modify it under
    the terms of the GNU General Public License as published by the Free Software
    Foundation, either version 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful, but WITHOUT ANY
    WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
    A PARTICULAR PURPOSE. See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

import android.util.Log;
// Log.w("testname", Calc.CurDisplay);

public class Tester
{
    public static State Calc;

    private final static String  ERROR = "9.9999999 99";
    private final static String mERROR = "-9.9999999 99";

    // helpers

    private void SetX (double v)
    {
        Calc.X.set(v);
        Calc.SetX(Calc.X);
    }

    private void SetT (double v)
    {
        Calc.T.set(v);
    }

    public void Clear()
    {
        Calc.ClearMemories();
        Calc.InvState = false;
        Calc.CurState = Calc.ResultState;
        Calc.CurFormat = Calc.FORMAT_FIXED;
        Calc.CurNrDecimals = -1;
        Calc.CurAng = Calc.ANG_DEG;
        Calc.OpStackNext = 0;
        Calc.PreviousOp = -1;
        Calc.ResetEntry();

    }

    private boolean Test_1()
    {
        // commit: bccc9bc
        Clear();

        SetX(90);
        Calc.Cos();

        if (Calc.X.getSignum() == 0)
            return true;
        else
            return false;
    }

    private boolean Test_2()
    {
        // commit: a90fb0f
        Clear();

        Calc.Memory[1].set(1);
        Calc.ClearAll();
        Calc.Digit('1');
        Calc.EnterExponent();
        Calc.InvState = true;
        Calc.EnterExponent();
        Calc.Digit('2');

        if (Calc.CurDisplay.equals("12."))
            return true;
        else
            return false;
    }

    private boolean Test_3()
    {
        // commit: 9c74ebd
        Clear();

        Calc.ClearAll();
        Calc.Digit('1');
        Calc.EnterExponent();
        Calc.Digit('2');
        Calc.InvState = true;
        Calc.EnterExponent();
        Calc.Digit('3');

        if (Calc.CurDisplay.equals("13. 02"))
            return true;
        else
            return false;
    }

    private boolean Test_4()
    {
        // commit: 51e6436
        Clear();

        Calc.Memory[0].set(0);
        Calc.Memory[1].set(20.1);
        Calc.SpecialOp (01, true);
        Calc.Memory[1].set(20.9);
        Calc.SpecialOp (01, true);
        Calc.Memory[1].set(-20.9);
        Calc.SpecialOp (21, true);

        if (Calc.Memory[0].get() == 2)
            return true;
        else
            return false;
    }

    private boolean Test_5()
    {
        // from TI-59 book
        Clear();

        SetX(30.1348);
        Calc.D_MS();

        if (!Calc.CurDisplay.equals("30.23"))
            return false;

        Calc.InvState = true;
        Calc.D_MS();

        if (Calc.CurDisplay.equals("30.1348"))
            return true;
        else
            return false;
    }

    private boolean Test_6()
    {
        // from TI-59 book
        Clear();

        SetX(30);
        SetT(5);
        Calc.Polar();

        if (!Calc.CurDisplay.equals("2.5"))
            return false;

        Calc.SwapT();

        if (!Calc.CurDisplay.equals("4.330127019"))
            return false;

        Calc.SwapT();

        Calc.InvState = true;
        Calc.Polar();

        if (!Calc.CurDisplay.equals("30."))
            return false;

        Calc.SwapT();

        if (!Calc.CurDisplay.equals("5."))
            return false;

        Calc.SetAngMode(Calc.ANG_RAD);
        SetX(4);
        SetT(3);
        Calc.InvState = true;
        Calc.Polar();

        if (!Calc.CurDisplay.equals("0.927295218"))
            return false;

        Calc.SwapT();

        if (!Calc.CurDisplay.equals("5."))
            return false;

        return true;
    }

    private boolean Test_7()
    {
        // from TI-59 book
        Clear();

        SetX(96); Calc.StatsSum();
        SetX(81); Calc.StatsSum();
        SetX(97); Calc.StatsSum();
        Calc.InvState = true;
        SetX(97); Calc.StatsSum();
        Calc.InvState = false;
        SetX(87); Calc.StatsSum();
        SetX(70); Calc.StatsSum();
        SetX(93); Calc.StatsSum();
        SetX(77); Calc.StatsSum();

        Calc.StatsResult();

        if (!Calc.CurDisplay.equals("84."))
            return false;

        Calc.InvState = true;
        Calc.StatsResult();

        if (!Calc.CurDisplay.equals("9.879271228"))
            return false;

        Calc.InvState = false;
        Calc.SpecialOp(11, false);

        if (!Calc.CurDisplay.equals("81.33333333"))
            return false;

        if (Calc.Memory[1].get() != 504)
            return false;

        return true;
    }

    private boolean Test_8()
    {
        // from TI-59 book
        Clear();

        SetX(7);  Calc.SwapT(); SetX(99);  Calc.StatsSum();
        SetX(12); Calc.SwapT(); SetX(152); Calc.StatsSum();
        SetX(3);  Calc.SwapT(); SetX(81);  Calc.StatsSum();
        SetX(5);  Calc.SwapT(); SetX(98);  Calc.StatsSum();
        SetX(22); Calc.SwapT(); SetX(151);  Calc.StatsSum();
        Calc.InvState = true;
        SetX(22); Calc.SwapT(); SetX(151);  Calc.StatsSum();
        Calc.InvState = false;
        SetX(11); Calc.SwapT(); SetX(151);  Calc.StatsSum();
        SetX(8);  Calc.SwapT(); SetX(112);  Calc.StatsSum();


        SetX(200);
        Calc.SpecialOp(15, false);

        if (!Calc.CurDisplay.equals("17.81578947"))
            return false;

        SetX(15);
        Calc.SpecialOp(14, false);

        if (!Calc.CurDisplay.equals("176.5561798"))
            return false;

        Calc.SpecialOp(12, false);

        if (!Calc.CurDisplay.equals("51.66853933"))
            return false;

        Calc.SwapT();

        if (!Calc.CurDisplay.equals("8.325842697"))
            return false;

        return true;
    }

    private boolean Test_9()
    {
        Clear();

        SetX(2);
        Calc.Reciprocal();

        if (!Calc.CurDisplay.equals("0.5"))
            return false;

        SetX(0);
        Calc.Reciprocal();

        if (!Calc.CurDisplay.equals(ERROR))
            return false;

        if (!Calc.InErrorState())
            return false;

        Calc.CurState = Calc.ResultState;

        return true;
    }

    private boolean Test_10()
    {
        Clear();

        SetX(4);
        Calc.Sqrt();

        if (!Calc.CurDisplay.equals("2."))
            return false;

        SetX(-4);
        Calc.Sqrt();

        if (!Calc.CurDisplay.equals("2."))
            return false;

        if (!Calc.InErrorState())
            return false;

        Calc.CurState = Calc.ResultState;
        return true;
    }

    private boolean Test_11()
    {
        Clear();

        SetX(9.258);

        Calc.SetDisplayMode(Calc.FORMAT_FIXED, 4);
        if (!Calc.CurDisplay.equals("9.2580"))
            return false;

        Calc.SetDisplayMode(Calc.FORMAT_FIXED, 3);
        if (!Calc.CurDisplay.equals("9.258"))
            return false;

        Calc.SetDisplayMode(Calc.FORMAT_FIXED, 2);
        if (!Calc.CurDisplay.equals("9.26"))
            return false;

        Calc.SetDisplayMode(Calc.FORMAT_FIXED, 1);
        if (!Calc.CurDisplay.equals("9.3"))
            return false;

        Calc.SetDisplayMode(Calc.FORMAT_FIXED, 0);
        if (!Calc.CurDisplay.equals("9"))
            return false;

        Calc.SetDisplayMode(Calc.FORMAT_FIXED, -1);
        if (!Calc.CurDisplay.equals("9.258"))
            return false;

        return true;
    }

    private boolean Test_12()
    {
        Clear();

        Calc.Digit('3');
        Calc.Operator(Calc.STACKOP_EXP);
        Calc.Digit('7');
        Calc.Equals();

        if (!Calc.CurDisplay.equals("2187."))
            return false;

        Calc.InvState = true;
        Calc.Operator(Calc.STACKOP_EXP);
        Calc.InvState = false;
        Calc.Digit('6');
        Calc.Equals();

        if (!Calc.CurDisplay.equals("3.602810866"))
            return false;

        return true;
    }

    private boolean Test_13()
    {
        Clear();

        SetX(85.23);
        Calc.EnterExponent();
        Calc.Digit('2');
        Calc.Digit('2');
        Calc.Equals();

        if (!Calc.CurDisplay.equals("8.523 23"))
            return false;

        Calc.SetDisplayMode (Calc.FORMAT_ENG, -1);

        if (!Calc.CurDisplay.equals("852.3 21"))
            return false;

        Calc.SetDisplayMode (Calc.FORMAT_FIXED, -1);
        return true;
    }

    private boolean Test_14()
    {
        // commit: 6c9cf8f
        Clear();

        SetX(0);
        Calc.Log();

        if (!Calc.CurDisplay.equals(mERROR))
            return false;

        if (!Calc.InErrorState())
            return false;

        Calc.CurState = Calc.ResultState;

        SetX(-9);
        Calc.Ln();

        if (!Calc.CurDisplay.equals("2.197224577"))
            return false;

        if (!Calc.InErrorState())
            return false;

        Calc.CurState = Calc.ResultState;

        return true;
    }

    public int Run()
    {
        Calc = Global.Calc;
        int Total = 0;

        if (!Test_1())  return -1;  Total++;
        if (!Test_2())  return -2;  Total++;
        if (!Test_3())  return -3;  Total++;
        if (!Test_4())  return -4;  Total++;
        if (!Test_5())  return -5;  Total++;
        if (!Test_6())  return -6;  Total++;
        if (!Test_7())  return -7;  Total++;
        if (!Test_8())  return -8;  Total++;
        if (!Test_9())  return -9;  Total++;
        if (!Test_10()) return -10; Total++;
        if (!Test_11()) return -11; Total++;
        if (!Test_12()) return -12; Total++;
        if (!Test_13()) return -13; Total++;
        if (!Test_14()) return -14; Total++;

        return Total;
    }
}
