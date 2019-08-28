package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.testng.Assert.*;

public class RemoveContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().gotoHomepage();
      app.contact().create(new ContactData().withName("TestName-addContactTest").withMidName("Contact"), true);
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1-addContactTest").withHeader("test-header").withFooter("test-footer"));
    }
    app.contact().gotoHomepage();
  }


  @Test
  public void testName() {
    GroupData groupData = app.db().groups().stream().filter(groupData1 -> !groupData1.getContacts().isEmpty()).findFirst().orElse(null);
    ContactData contactData = app.db().contacts().iterator().next();

    if (groupData == null) {
      groupData = app.db().groups().iterator().next();
      app.contact().addToGroup(contactData, groupData.getName());
    }

    int groupSizeBefore = groupData.getContacts().size();

    app.goTo().goHomeByLink();
    app.contact().removeFromGroup(contactData, groupData);

    GroupData groupAfter = app.db().group(groupData.getId());
    assertEquals(groupSizeBefore - 1, groupAfter.getContacts().size());
    assertFalse(groupAfter.getContacts().contains(contactData));
  }
}