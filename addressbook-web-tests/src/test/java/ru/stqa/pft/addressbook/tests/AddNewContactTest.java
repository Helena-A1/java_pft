package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContactTest extends TestBase {

  @Test(enabled = true)
  public void testAddNewContact() {
   Contacts before = app.contact().all();
    app.goTo().newContactPage();
    ContactData contact = new ContactData()
            .withName("Test2name")
            .withMidName( "Contact")
            .withSurname( "Java_pft")
            .withNic( "pft")
            .withPosition("tester")
            .withEmployer1("Software-Testing")
            .withAddress( "some address")
            .withPhone("12345678")
            .withMobile("33-33-33")
            .withWorkPhone("44 444 444 444")
            .withEmail("software-testing@gmail.ru")
            .withGroup( "test1");
    app.contact().create(contact,false);
    app.goTo().gotoHomepage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test(enabled = true)
  public void testBadAddNewContact() {
    Contacts before = app.contact().all();
    app.goTo().newContactPage();
    ContactData contact = new ContactData()
            .withName("Test2name'")
            .withMidName( "Contact")
            .withSurname( "Java_pft")
            .withNic( "pft")
            .withPosition("tester")
            .withEmployer1("Software-Testing")
            .withAddress( "some address")
            .withPhone("12345678")
            .withEmail("software-testing@gmail.ru")
            .withGroup( "test1");
    app.contact().create(contact,false);
    app.goTo().gotoHomepage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}
