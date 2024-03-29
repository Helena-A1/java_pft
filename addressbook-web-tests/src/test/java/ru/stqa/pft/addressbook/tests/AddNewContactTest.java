package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContactTest extends TestBase {



  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
     xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map( (g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

    }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map( (g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
      }
}


  @Test(dataProvider = "validContactsFromJson")
  public void testAddNewContact(ContactData contact) throws IOException {
    Groups groups = app.db().groups();
    File photo = new File("src\\test\\resources\\Image 4.png");
    ContactData newContact = new ContactData().withName("TestName").withMidName("Contact").inGroup(groups.iterator().next());
    Contacts before = app.db().contacts();
    app.goTo().newContactPage();


    app.contact().create(newContact, false);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    app.goTo().gotoHomepage();
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();


  }


  @Test(enabled = false)
  public void testBadAddNewContact()  {

    Contacts before = app.db().contacts();

    ContactData contact = new ContactData()
            .withName("Test2name'")
            .withMidName("Contact")
            .withSurname("Java_pft")
            .withNic("pft")
            .withPosition("tester")
            .withEmployer1("Software-Testing")
            .withAddress("some address")
            .withPhone("12345678")
            .withEmail("software-testing@gmail.ru");
           // .withGroup("test1");
    app.goTo().newContactPage();
    app.contact().create(contact, false);
    app.goTo().gotoHomepage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
  }

}
