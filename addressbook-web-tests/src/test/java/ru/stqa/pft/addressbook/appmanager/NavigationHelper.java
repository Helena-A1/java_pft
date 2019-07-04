package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
 private WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
     wd.findElement(By.linkText("groups")).click();
  }

  public void gotoAddNewPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void gotoHomepage() {
    wd.findElement(By.linkText("home page")).click();
  }
}