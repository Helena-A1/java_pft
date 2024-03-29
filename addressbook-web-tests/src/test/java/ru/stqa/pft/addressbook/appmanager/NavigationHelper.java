package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.linkText("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void newContactPage() {
    if (isElementPresent(By.tagName("h1"))
      && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")){
      return;
    }
    click(By.linkText("add new"));
  }

  public void gotoHomepage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home page"));
  }

  public void goHomeByLink() {
    click(By.linkText("home"));
  }
}
