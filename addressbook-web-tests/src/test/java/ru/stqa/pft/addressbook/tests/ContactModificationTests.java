package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestName", "Contact", "Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    app.getContactHelper().fillContactForm(new ContactData("Updated TestName", "Updated Contact", "Updated Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().gotoDetailsPage();
    app.getNavigationHelper().goHomeByLink();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
