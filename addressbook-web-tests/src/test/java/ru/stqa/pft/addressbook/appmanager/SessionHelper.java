package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {


  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String user, String pass) {
    wd.findElement(By.name("user")).sendKeys(user);;
    wd.findElement(By.name("pass")).sendKeys(pass);
    wd.findElement(By.xpath("//input[@value='Login']")).click();

  }
}

