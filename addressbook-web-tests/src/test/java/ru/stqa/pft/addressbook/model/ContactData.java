package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String midName;
  private final String surname;
  private final String nick;
  private final String postTitle;
  private final String employer1;
  private final String address;
  private final String phone;
  private final String email;
  private String group;

  public ContactData(String name, String midName, String surname, String nick, String postTitle, String employer1, String address, String phone, String email,String group) {
    this.name = name;
    this.midName = midName;
    this.surname = surname;
    this.nick = nick;
    this.postTitle = postTitle;
    this.employer1 = employer1;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getMidName() {
    return midName;
  }

  public String getSurname() {
    return surname;
  }

  public String getNick() {
    return nick;
  }

  public String getPostTitle() {
    return postTitle;
  }

  public String getEmployer1() {
    return employer1;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
