package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class SearchFastAirlinePage(driver: WebDriver): Page(driver) {

    private val ticketsPath: By = By.xpath("//a[contains(@data-test-id, 'ticket')]")


    fun hasResults(): Boolean {
        return waitForPresenceOfAll(ticketsPath).isNotEmpty()
    }
}