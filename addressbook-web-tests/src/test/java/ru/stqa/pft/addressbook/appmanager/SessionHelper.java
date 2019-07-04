package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
  public WebDriver wd;

  public SessionHelper(WebDriver wd) {

    this.wd = wd;
  }

  public void login(String user, String admin, String pass, String secret, By xpath) {
    wd.findElement(By.name(user)).click();
    wd.findElement(By.name(user)).clear();
    wd.findElement(By.name(user)).sendKeys(admin);
    wd.findElement(By.name(pass)).click();
    wd.findElement(By.name(pass)).clear();
    wd.findElement(By.name(pass)).sendKeys(secret);
    wd.findElement(xpath).click();
  }
}

