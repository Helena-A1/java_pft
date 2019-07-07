package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {

   // app.getContactHelper().initContactModification();
   // app.getContactHelper().submitDeleteContact();


    app.getContactHelper().findContact();
    app.getContactHelper().submitDeleteContact();
    app.getContactHelper().closeSubmitModal();



  }
}