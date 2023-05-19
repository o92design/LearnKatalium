package com.katalon.kata.sample.testcase.helpers.login;

import org.slf4j.Logger;

import com.katalon.kata.helper.LogHelper;
import com.katalon.kata.sample.page.CuraAppoinmentPage;

public class Helper_AppointmentFiller {
    protected static final Logger log = LogHelper.getLogger();
    public void fill(CuraAppoinmentPage curaAppoinmentPage, String facility, String visitDate, String comment) {
        log.info("Make appointment with parameters");
        log.info("Facility {}", facility);
        log.info("VisitDate {}", visitDate);
        log.info("Comment {}", comment);
        curaAppoinmentPage.fillAppointmentDetails(facility, visitDate, comment);
    }
}
