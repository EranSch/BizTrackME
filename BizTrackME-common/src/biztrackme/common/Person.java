package biztrackme.common;

import java.io.Serializable;

/**
 *
 * @author Eran
 */
public abstract class Person implements Serializable{

  String name;
  String phone;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  
}