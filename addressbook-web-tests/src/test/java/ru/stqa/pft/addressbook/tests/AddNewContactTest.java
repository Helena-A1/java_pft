package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class AddNewContactTest extends TestBase {

  @Test
  public void testAddNewContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData("TestName", "Contact", "Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru", "test1"));
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
