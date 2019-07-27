package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHomepage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("TestName").withMidName("Contact").withAddress("some address").withPhone("11111").withMobile("222222").withWorkPhone("3333333"), true);
    }
  }
  @Test(enabled = true)
  public void testContactAddressTests() {
    app.goTo().gotoHomepage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactAddressInfoFromEdit = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(cleaned(contactAddressInfoFromEdit.getAddress())));

  }
  public static String cleaned(String address) {
    return address.replaceAll(" \n", "\n");
  }
}
