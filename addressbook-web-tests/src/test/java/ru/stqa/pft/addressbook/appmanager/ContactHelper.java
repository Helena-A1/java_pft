package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {

  private WebDriver wd;
  public ContactHelper(WebDriver wd) {
    this.wd = wd;
  }


  public void submitContactCreation() {
    wd.findElement(By.name("submit")).click();
  }
  public void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getName());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMidName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getSurname());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(contactData.getNick());
    wd.findElement(By.name("title")).click();
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys(contactData.getPostTitle());
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(contactData.getEmployer1());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(contactData.getPhone());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }


  public void initContactModification() {
    wd.findElement(By.xpath("//img[@alt='Edit']")).click();
  }

  public void submitContactModification() {
    wd.findElement(By.name("update")).click();
  }

  public void gotoDetailsPage() {
    wd.findElement(By.xpath("//img[@alt='Details']")).click();
  }

  public void submitDeleteContact() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }


  public void findContact() {
    wd.findElement(By.id("21")).click();
  }


  public void closeSubmitModal() {
    wd.switchTo().alert().accept();
  }


}
