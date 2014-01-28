package biztrackme.common;

import java.io.Serializable;

/**
 *
 * @author Eran
 */
public abstract class Person implements Serializable{

  String  firstName,
          lastName,
          phone;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String name) {
    this.firstName = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  
}