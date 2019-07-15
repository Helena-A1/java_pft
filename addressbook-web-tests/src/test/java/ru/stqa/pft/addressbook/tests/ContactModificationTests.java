package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestName", "Contact", "Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru", "test1"));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Updated TestName", "Updated Contact", "Updated Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().gotoDetailsPage();
    app.getNavigationHelper().goHomeByLink();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
