package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

public class AddContactToGroup extends TestBase {



  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().gotoHomepage();
      app.contact().create(new ContactData().withName("TestName").withMidName("Contact"), true);
    }

  }

  @Test
  @Transactional
  public void testAddContactToGroup() throws IOException {
    Groups groups = app.db().groups();
    GroupData groupData = groups.stream().filter(group -> group.getName().equals("test1-updated")).findFirst().get();
    long beforeCount = groupData.getContacts().stream().count();

    app.goTo().gotoHomepage();
    Contacts all = app.contact().all();
    ContactData contactData = all.stream().findFirst().get();
    app.contact().addToGroup(contactData, "test1-updated");

    Groups groups2 = app.db().groups();
    GroupData groupData2 = groups.stream().filter(group -> group.getName().equals("test1-updated")).findFirst().get();
    long afterCount = groupData.getContacts().stream().count();

    Assert.assertEquals(afterCount, beforeCount + 1);

  }
}
