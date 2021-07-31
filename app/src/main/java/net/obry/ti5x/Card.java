/*
    ti5x calculator emulator -- data exporter context

    Copyright 2021 Pascal Obry <pascal@obry.net>.

    This program is free software: you can redistribute it and/or modify it under
    the terms of the GNU General Public License as published by the Free Software
    Foundation, either version 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful, but WITHOUT ANY
    WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
    A PARTICULAR PURPOSE. See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
package net.obry.ti5x;

public class Card {
  final int BANK_MEM = 30;
  final int BANK_PROG = 240;

  private byte[] ProgData;
  private Number[] MemData;
  private int BankNr;
  private int Id;

  Card (
      int BankNr
  )
  {
    ProgData = new byte[BANK_PROG];
    MemData = new Number[BANK_MEM];
    this.BankNr = BankNr;
    Id = -1;

    for (int k = 0; k < BANK_PROG; k++) {
      ProgData[k] = 0;
    }

    for (int k = 0; k < BANK_MEM; k++) {
      MemData[k] = new Number(0.0);
    }
  }

  public void SetMem
      (
          Number[] Data
      ) {
    for (int k=0; k<BANK_MEM; k++)
    {
      if (k < Data.length) {
        MemData[k] = Data[k];
      } else {
        MemData[k] = new Number(0.0);
      }
    }
  }

  public void SetProg
      (
          byte[] Data
      ) {
    for (int k=0; k<BANK_PROG; k++)
    {
      if (k < Data.length) {
        ProgData[k] = Data[k];
      } else {
        ProgData[k] = 0;
      }
    }
  }

  public void WriteCard
      (
          State Calc,
          int BankNr,
          int Id
      )
  {
    this.BankNr = BankNr;
    this.Id = Id;

    int idx = 0;
    for (int k = (4 - BankNr) * BANK_MEM; k < (4 - BankNr + 1) * BANK_MEM; k++) {
      if (k < Calc.MaxMemories) {
        MemData[idx++] = new Number(Calc.Memory[k]);
      }
    }
    System.arraycopy
        (
            Calc.Program,    (BankNr - 1) * BANK_PROG,
            ProgData,0,
            BANK_PROG
        );
  }

  public void LoadCard
      (
          State Calc
      )
  {
    int idx = 0;
    for (int k = (4 - this.BankNr) * BANK_MEM; k < (4 - this.BankNr + 1) * BANK_MEM; k++) {
      if (k < Calc.MaxMemories) {
        Calc.Memory[k] = new Number(MemData[idx++]);
      }
    }
    System.arraycopy
        (
            ProgData,0,
            Calc.Program,    (this.BankNr - 1) * BANK_PROG,
            BANK_PROG
        );
  }
}
