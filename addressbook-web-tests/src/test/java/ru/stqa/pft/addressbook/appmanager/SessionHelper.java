package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {


  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String user, String admin, String pass, String secret, By xpath) {
    type(By.name(user), admin);
    type(By.name(pass), secret );
    click(xpath);

  }
}

