package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHomepage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("TestName").withMidName("Contact").withPhone("11111").withMobile("222222").withWorkPhone("3333333")
              .withEmail("software@gmail.com").withEmail2("Software2@gmail.com").withEmail3("sofware3@gmail.com"), true);
    }
  }
  @Test(enabled = true)
  public void testContactPhones() {
    app.goTo().gotoHomepage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

  private String mergeEmails(ContactData contact) {
    return  Arrays.asList(contact.getEmail(),contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "").replaceAll("[-()@.]", "");
  }

}


