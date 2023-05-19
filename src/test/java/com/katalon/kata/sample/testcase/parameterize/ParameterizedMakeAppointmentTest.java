package com.katalon.kata.sample.testcase.parameterize;

import com.katalon.kata.sample.Constants;
import com.katalon.kata.sample.page.CuraAppoinmentPage;
import com.katalon.kata.sample.page.CuraAppointmentConfirmPage;
import com.katalon.kata.sample.page.CuraHomePage;
import com.katalon.kata.sample.page.CuraLoginPage;
import com.katalon.kata.sample.testcase.helpers.login.Helper_AppointmentFiller;
import com.katalon.kata.testng.TestTemplate;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizedMakeAppointmentTest extends TestTemplate {

  private CuraHomePage curaHomePage = new CuraHomePage(Constants.baseUrl);

  private CuraLoginPage loginPage;

  private CuraAppoinmentPage curaAppoinmentPage;

  private CuraAppointmentConfirmPage curaAppointmentConfirmPage;

  public void setupTest() {
    curaHomePage.open();
    curaHomePage.makeAppointment();
    loginPage.login(Constants.username, Constants.password);
  }

  @Parameters({"facility", "visitDate", "comment"})
  @Test
  public void shoudMakeAppointmentWithParameters(@Optional(Constants.facility) String facility,
                                                @Optional(Constants.appointmentDate) String visitDate,
                                                @Optional(Constants.comment) String comment) {
    
    // This didn't work when i used @SetupTest ?? 
    setupTest();
    Helper_AppointmentFiller helper_appointmentFiller = new Helper_AppointmentFiller();
    helper_appointmentFiller.fill(curaAppoinmentPage, facility, visitDate, comment);
    curaAppoinmentPage.bookAppointment();
    curaAppointmentConfirmPage.checkInformation(facility, visitDate, comment);

    // This didn't work when i used @AfterTest ?? 
    teardownTest();
  }

  @Parameters({"facility", "visitDates", "comment"})
  @Test
  public void changeAppointmentDateBeforeConfirm(@Optional(Constants.facility) String facility,
                                                 @Optional String[] visitDates,
                                                 @Optional(Constants.comment) String comment) {
    if (visitDates == null) {
      visitDates = Constants.visitDates;
    }

    setupTest();
    Helper_AppointmentFiller helper_appointmentFiller = new Helper_AppointmentFiller();
    helper_appointmentFiller.fill(curaAppoinmentPage, facility, visitDates[0], comment);

    log.info("Change appointment date before confirm");
    for(String visitDate : visitDates) {
      log.info("VisitDate {}", visitDate);
      curaAppoinmentPage.changeAppointmentDate(visitDate);
    }

    curaAppoinmentPage.bookAppointment();
    curaAppointmentConfirmPage.checkInformation(facility, visitDates[visitDates.length - 1], comment);

    teardownTest();
  }

  public void teardownTest() {
    driver.quit();
  }
}
