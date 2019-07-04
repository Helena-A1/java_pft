package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

 @Test
  public void testContactModification () {


   app.getContactHelper().initContactModification();
   app.getContactHelper().fillContactForm(new ContactData("Updated TestName", "Updated Contact", "Updated Java_pft", "pft", "tester", "Software-Testing", "some address", "12345678", "software-testing@gmail.ru"));
   app.getContactHelper().submitContactModification();
   app.getNavigationHelper().gotoHomepage();
   app.getContactHelper().gotoDetailsPage();
   app.getNavigationHelper().goHomeByLink();
 }
}
