package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void submitContactCreation() {
    click(By.name("submit"));
  }
  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMidName());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("nickname"), contactData.getNick());
    type(By.name("title"), contactData.getPostTitle());
    type(By.name("company"), contactData.getEmployer1());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());

    if(creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

     }



  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void gotoDetailsPage() {
    click(By.xpath("//img[@alt='Details']"));
  }

  public void submitDeleteContact() {
    wd.findElements(By.xpath("//input[@value='Delete']"));
    click(By.xpath("//input[@value='Delete']"));
  }


  public void findContact(int index) {
    wd.findElements(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]")).get(index).click();

  }


  public void closeSubmitModal() {

    wd.switchTo().alert().accept();
  }


  public void createContact(ContactData contact) {
    gotoAddNew();
    fillContactForm(contact,true);
    submitContactCreation();
    gotoHomepage();


  }

  private void gotoHomepage() {
    click(By.linkText("home"));
  }

  private void gotoAddNew() {
    click(By.linkText("add new"));
  }

  public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void verifyDeleted() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }
}
