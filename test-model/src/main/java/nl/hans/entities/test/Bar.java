package nl.hans.entities.test;

import java.io.Serializable;

public class Bar implements Serializable {
  private static final long serialVersionUID = 4964533228646966622L;

  String bartest1;

  String bartest2;

  public String getBartest1() {
    return bartest1;
  }

  public void setBartest1(String bartest1) {
    this.bartest1 = bartest1;
  }

  public String getBartest2() {
    return bartest2;
  }

  public void setBartest2(String bartest2) {
    this.bartest2 = bartest2;
  }
}
