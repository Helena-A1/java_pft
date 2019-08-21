package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class AddContactToGroup extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHomepage();
    ContactData contact = new ContactData().withName("TestName-addContactTest").withSurname("Contact");
    app.contact().create(contact, true);

    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1-addContactTest").withHeader("test-header").withFooter("test-footer"));
  }


  @Test
  @Transactional
  public void testAddContactToGroup() throws IOException {

    GroupData groupData = app.db().groups().stream().filter(group -> group.getName().equals("test1-addContactTest")).findFirst().get();
    ContactData contactData = app.db().contacts().stream().filter(contact -> contact.getName().equals("TestName-addContactTest")).findFirst().get();

    assertEquals(contactData.getGroups().size(), 0);

    int contactDataId = contactData.getId();

    app.goTo().goHomeByLink();
    app.contact().addToGroup(contactData, groupData.getName());

    ContactData contact = app.db().contact(contactDataId);

    assertNotEquals(contact.getGroups().size(), 0);
    assertEquals(contact.getGroups().iterator().next(), groupData);
  }


  @AfterMethod
  public void afterTest() {
    ContactData deletedContact = app.db().contacts().stream().filter(contactData -> contactData.getName().equals("TestName-addContactTest")).findFirst().orElse(null);
    if (deletedContact != null) {
      app.goTo().goHomeByLink();
      app.contact().delete(deletedContact);
    }

    GroupData groupToDelete = app.db().groups().stream().filter(groupData -> groupData.getName().equals("test1-addContactTest")).findFirst().orElse(null);
    if (groupToDelete != null) {
      app.goTo().groupPage();
      app.group().delete(groupToDelete);
    }
  }
}