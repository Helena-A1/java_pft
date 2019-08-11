package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import net.bytebuddy.build.Plugin;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
   private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String name;

  @Expose
  @Column(name = "middlename")
  private String midName;

  @Expose
  @Column(name = "lastname")
  private String surname;

  @Expose
  @Column(name = "nickname")
  private String nick;

  @Expose
  @Column(name = "title")

  private String postTitle;

  @Expose
  @Column(name = "company")
  private String employer1;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String phone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;



  @Transient
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();


  public File getPhoto() {
    if (photo == null) {
      return null;
    } else {
      return new File(photo);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(name == null ? "" : name, that.name == null ? "" : that.name) &&
            Objects.equals(midName == null ? "" : midName, that.midName == null ? "" : that.midName) &&
            Objects.equals(surname == null ? "" : surname, that.surname == null ? "" : that.surname) &&
            Objects.equals(nick == null ? "" : nick, that.nick == null ? "" : that.nick) &&
            Objects.equals(postTitle == null ? "" : postTitle, that.postTitle == null ? "" : that.postTitle) &&
            Objects.equals(employer1 == null ? "" : employer1, that.employer1 == null ? "" : that.employer1) &&
            Objects.equals(address == null ? "" : address, that.address == null ? "" : that.address) &&
            Objects.equals(phone == null ? "" : phone, that.phone == null ? "" : that.phone) &&
            Objects.equals(mobile == null ? "" : mobile, that.mobile == null ? "" : that.mobile) &&
            Objects.equals(workPhone == null ? "" : workPhone, that.workPhone == null ? "" : that.workPhone) &&
            Objects.equals(email == null ? "" : email, that.email == null ? "" : that.email) &&
            Objects.equals(email2 == null ? "" : email2, that.email2 == null ? "" : that.email2) &&
            Objects.equals(email3 == null ? "" : email3, that.email3 == null ? "" : that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, midName, surname, nick, postTitle, employer1, address, phone, mobile, workPhone, email, email2, email3);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }




  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }



  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return  this;
  }




  public ContactData() {
  }


  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withMidName(String midName) {
    this.midName = midName;
    return this;
  }

  public ContactData withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public ContactData withNic(String nick) {
    this.nick = nick;
    return this;
  }

  public ContactData withPosition(String postTitle) {
    this.postTitle = postTitle;
    return this;
  }

  public ContactData withEmployer1(String employer1) {
    this.employer1 = employer1;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }



  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
   //         ", surname='" + surname + '\'' +
            '}';
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

  public Groups getGroups() {
    return new Groups(groups);
  }

  public String getMobile() {
    return mobile;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }


}
