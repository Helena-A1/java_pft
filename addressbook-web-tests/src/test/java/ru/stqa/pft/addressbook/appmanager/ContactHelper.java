package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    if (creation) {
      try {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } catch (Exception e) {
        new Select(wd.findElement(By.name("new_group"))).selectByIndex(0);
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }


  public void startModify(ContactData contact) {
    wd.findElement(By.xpath("//img[@alt='Edit']")).click();

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void gotoDetailsPage() {
    click(By.xpath("//img[@alt='Details']"));
  }

  public void submit() {
    wd.findElements(By.xpath("//input[@value='Delete']"));
    click(By.xpath("//input[@value='Delete']"));
  }


  public void find(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }


  public void closeModal() {

    wd.switchTo().alert().accept();
  }


  public void create(ContactData contact, boolean creation) {
    gotoAddNew();
    fillContactForm(contact, true);
    submitContactCreation();
    gotoHomepage();
  }

  public void modify(ContactData contact) {
    startModify(contact);
    fillContactForm(contact, false);
    submitContactModification();
    gotoHomepage();
    gotoDetailsPage();
    goHomeByLink();
  }
  public void delete(int index) {
   find(index);
    submit();
   closeModal();
   verifyDeleted();
   goHomeByLink();
  }

  public void delete(ContactData contact) {
    findById(contact.getId());
    submit();
    closeModal();
    verifyDeleted();
    goHomeByLink();
  }

  private void findById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  private void goHomeByLink() {
    click(By.linkText("home"));
  }

  public void gotoHomepage() {
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

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      String surname = element.findElement(By.xpath(".//td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname);
      contacts.add(contact);
    }

    return contacts;

  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      String surname = element.findElement(By.xpath(".//td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname);
      contacts.add(contact);
    }

    return contacts;
  }


}
