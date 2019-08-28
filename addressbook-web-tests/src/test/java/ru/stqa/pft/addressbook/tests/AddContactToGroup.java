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
import static org.testng.Assert.*;

public class AddContactToGroup extends TestBase {


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
  @Transactional
  public void testAddContactToGroup() throws IOException {

    ContactData contactData = app.db().contacts().iterator().next();
    GroupData groupData = app.db().groups().stream().filter(groupData1 -> !groupData1.getContacts().contains(contactData)).findFirst().orElse(null);

    if (groupData == null) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1-addContactTest").withHeader("test-header").withFooter("test-footer"));
      groupData = app.db().groups().stream().filter(groupData1 -> groupData1.getName().equals("test1-addContactTest")).filter(groupData1 -> groupData1.getContacts().isEmpty()).findFirst().get();
    }

    int groupSizeBefore = groupData.getContacts().size();

    app.goTo().goHomeByLink();
    app.contact().addToGroup(contactData, groupData.getName());

    GroupData group = app.db().group(groupData.getId());

    assertEquals(groupSizeBefore + 1, group.getContacts().size());
    assertTrue(group.getContacts().contains(contactData));
  }
}