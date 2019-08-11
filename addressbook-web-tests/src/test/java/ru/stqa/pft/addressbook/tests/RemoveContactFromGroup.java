package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().gotoHomepage();
      app.contact().create(new ContactData().withName("TestName").withMidName("Contact"), true);
    }
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test").withFooter("footer").withHeader("header"));
      app.goTo().gotoHomepage();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testRemoveContactFromGroup(ContactData contact) throws IOException {
    app.goTo().gotoHomepage();
    Contacts before = app.db().contacts();
    ContactData findContact = before.iterator().next();
    ContactData contactAdd = new ContactData()
            .withId(findContact.getId()).withName("Updated TestName").withMidName("Updated Contact").withSurname("Updated Java_pft");
    app.contact().addToGroup(contactAdd);

    app.contact().removeFromGroup(contactAdd);



    assertThat(app.contact().count(), equalTo(before.size()));
  }
}