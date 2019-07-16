package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {

   // app.getContactHelper().initContactModification();
    // app.getContactHelper().submitDeleteContact();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestName", "Contact", "Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru", "test1"));
      before++;
    }
    app.getContactHelper().findContact(before -1);
    app.getContactHelper().submitDeleteContact();
    app.getContactHelper().closeSubmitModal();
    app.getContactHelper().verifyDeleted();
    app.getNavigationHelper().goHomeByLink();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);



  }
}