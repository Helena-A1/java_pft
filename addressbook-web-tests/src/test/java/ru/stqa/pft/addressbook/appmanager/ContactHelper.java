package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("title"), contactData.getPostTitle());
    type(By.name("company"), contactData.getEmployer1());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhone());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());


    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }


  public void startModify(ContactData contact) {
    wd.findElement(By.xpath("//img[@alt='Edit']")).click();

  }

  public void startModifyById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();

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
    contactCache = null;
    gotoHomepage();
  }

  public void modify(ContactData contact) {
    startModifyById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    gotoHomepage();
    gotoDetailsPage();
    contactCache = null;
    goHomeByLink();
  }


  public void delete(int index) {
    find(index);
    submit();
    closeModal();
    verifyDeleted();
    contactCache = null;
    goHomeByLink();
  }

  public void delete(ContactData contact) {
    findById(contact.getId());
    submit();
    closeModal();
    verifyDeleted();
    contactCache = null;
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

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void verifyDeleted() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }


 /* public List<ContactData> list() {
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
  }*/

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String surname = cells.get(1).getText();
      String name = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname).withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails);
      contactCache.add(contact);
    }

    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withName(firstname).withSurname(lastname).withAddress(address)
            .withPhone(home).withMobile(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void addToGroup(ContactData findContact) {
    findById(findContact.getId());
    clickAdd();
  }

  public void addToGroup(ContactData findContact, String groupName) {
    findById(findContact.getId());
    WebElement select = wd.findElement(By.name("to_group"));
    List<WebElement> options = select.findElements(By.tagName("option"));
    for(WebElement option : options){
      if(option.getText().equals(groupName)) {
        option.click();
        break;
      }
    }
    wd.findElement(By.name("add")).click();
  }

  private void clickAdd() {
    wd.findElement(By.name("add")).click();
  }

  public void gotoGroupPage() {
    wd.findElement(By.xpath("//*[@id=\"content\"]/div/i/a")).click();
  }

  public void removeFromGroup(ContactData findContact) {
    gotoGroupPage();
    findById(findContact.getId());
    removeContact();
    gotoHomepage();
    goToAllGroups();



  }

  private void goToAllGroups() {
    wd.findElement(By.name("group")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    wd.findElement(By.name("group")).click();
  }

  private void removeContact() {
    wd.findElement(By.name("remove")).click();
  }
}
