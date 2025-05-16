package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class SearchMultiWayAirlinePage(driver: WebDriver): Page(driver) {

    private val ticketsPath: By = By.xpath("//div[@data-test-id='ticket-preview']")


    fun hasResults(): Boolean{
        return waitForPresenceOfAll(ticketsPath).isNotEmpty()
    }
}