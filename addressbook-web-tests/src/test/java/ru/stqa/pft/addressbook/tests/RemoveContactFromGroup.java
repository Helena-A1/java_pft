package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.testng.Assert.assertEquals;

public class RemoveContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHomepage();
    ContactData contact = new ContactData().withName("TestName-removeContactFromGroup").withSurname("Contact");
    app.contact().create(contact, true);

    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1-removeContactFromGroup").withHeader("test-header").withFooter("test-footer"));

    GroupData groupData = app.db().groups().stream().filter(g -> g.getName().equals("test1-removeContactFromGroup")).findFirst().get();
    ContactData contactData = app.db().contacts().stream().filter(c -> c.getName().equals("TestName-removeContactFromGroup")).findFirst().get();
    app.goTo().goHomeByLink();
    app.contact().addToGroup(contactData, groupData.getName());
  }



  @Test
  public void testName() {
    ContactData contactData = app.db().contacts().stream().filter(c -> c.getName().equals("TestName-removeContactFromGroup")).findFirst().get();
    GroupData groupData = app.db().groups().stream().filter(g -> g.getName().equals("test1-removeContactFromGroup")).findFirst().get();

    app.goTo().goHomeByLink();
    app.contact().removeFromGroup(contactData, groupData);

    ContactData contactDataAfter = app.db().contacts().stream().filter(c -> c.getName().equals("TestName-removeContactFromGroup")).findFirst().get();
    assertEquals(contactDataAfter.getGroups().size(), 0);
  }

  @AfterMethod
  public void afterTest() {
    ContactData deletedContact = app.db().contacts().stream().filter(contactData -> contactData.getName().equals("TestName-removeContactFromGroup")).findFirst().orElse(null);
    if (deletedContact != null) {
      app.goTo().goHomeByLink();
      app.contact().delete(deletedContact);
    }

    GroupData groupToDelete = app.db().groups().stream().filter(groupData -> groupData.getName().equals("test1-removeContactFromGroup")).findFirst().orElse(null);
    if (groupToDelete != null) {
      app.goTo().groupPage();
      app.group().delete(groupToDelete);
    }
  }
}