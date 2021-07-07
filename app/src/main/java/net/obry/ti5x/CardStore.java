package net.obry.ti5x;

import java.util.Map;
import java.util.HashMap;

public class CardStore {
  final Map<Integer, Card> Store = new HashMap<Integer, Card>();

  public Boolean Contains
      (
          int id
      ) {
    return Store.containsKey(id);
  }

  public Card Get
      (
          int id
      ) {
    return Store.get(id);
  }

  public void SetMem
      (
          int Id,
          Number[] Data
      ) {
    Card C;
    if (Store.containsKey(Id)) {
      C = Store.get(Id);
    } else {
      C = new Card();
    }

    C.SetMem (Data);
  }

  public void SetProg
      (
          int Id,
          byte[] Data
      ) {
    Card C;
    if (Store.containsKey(Id)) {
      C = Store.get(Id);
    } else {
      C = new Card();
    }

    C.SetProg (Data);
  }
}
