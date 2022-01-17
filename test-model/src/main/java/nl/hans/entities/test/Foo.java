package nl.hans.entities.test;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name="my_foo_1")
public class Foo implements Serializable {
  private static final long serialVersionUID = -386935304947026121L;

  @Id
  String id1;

  @Transient
  String id2;

  String test1;

  @Transient
  String test2;

  @Transient
  Bar bar;

  public String getId1() {
    return id1;
  }

  public void setId1(String id1) {
    this.id1 = id1;
  }

  public String getId2() {
    return id2;
  }

  public void setId2(String id2) {
    this.id2 = id2;
  }

  public String getTest1() {
    return test1;
  }

  public void setTest1(String test1) {
    this.test1 = test1;
  }

  public String getTest2() {
    return test2;
  }

  public void setTest2(String test2) {
    this.test2 = test2;
  }

  public Bar getBar() {
    return bar;
  }

  public void setBar(Bar bar) {
    this.bar = bar;
  }
}
