package com.katalon.kata.sample.testcase.simple;

import com.katalon.kata.sample.Constants;
import com.katalon.kata.sample.page.CuraAppoinmentPage;
import com.katalon.kata.sample.page.CuraAppointmentConfirmPage;
import com.katalon.kata.sample.page.CuraHomePage;
import com.katalon.kata.sample.page.CuraLoginPage;
import com.katalon.kata.testng.TestTemplate;
import org.testng.annotations.Test;

public class MakeAppointmentTest extends TestTemplate {

  private CuraHomePage curaHomePage = new CuraHomePage(Constants.baseUrl);

  private CuraLoginPage loginPage;

  private CuraAppoinmentPage curaAppoinmentPage;

  private CuraAppointmentConfirmPage curaAppointmentConfirmPage;

  @Test
  public void shouldMakeAppointment() {
    curaHomePage.open();
    curaHomePage.makeAppointment();
    loginPage.login(Constants.username, Constants.password);
    curaAppoinmentPage.fillAppointmentDetails(Constants.facility, Constants.appointmentDate,
      "Please make appointment as soon as possible.");
    curaAppointmentConfirmPage.checkInformation(Constants.facility, Constants.appointmentDate,
      "Please make appointment as soon as possible.");
    driver.quit();
  }
}
