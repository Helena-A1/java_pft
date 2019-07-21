package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {


    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestName", "Contact", "Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru", "test1"));

    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().findContact(before.size() - 1);
    app.getContactHelper().submitDeleteContact();
    app.getContactHelper().closeSubmitModal();
    app.getContactHelper().verifyDeleted();
    app.getNavigationHelper().goHomeByLink();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(before, after);

  }


}
