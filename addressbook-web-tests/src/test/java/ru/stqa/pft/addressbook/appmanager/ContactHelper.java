package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void submitContactCreation() {
    click(By.name("submit"));
  }
  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("middlename"), contactData.getMidName());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("nickname"), contactData.getNick());
    type(By.name("title"), contactData.getPostTitle());
    type(By.name("company"), contactData.getEmployer1());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail() );

  }


  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void gotoDetailsPage() {
    click(By.xpath("//img[@alt='Details']"));
  }

  public void submitDeleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }


  public void findContact() {
    click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]"));
  }


  public void closeSubmitModal() {

    wd.switchTo().alert().accept();
  }


}
