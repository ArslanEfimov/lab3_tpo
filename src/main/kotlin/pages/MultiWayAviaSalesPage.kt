package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class MultiWayAviaSalesPage(driver: WebDriver): Page(driver) {

    private val fromCityFirst: By = By.xpath("//fieldset[@data-test-id='multiway-direction-1']//input[@data-test-id='multiway-origin-input']")
    private val toCityFirst: By = By.xpath("//fieldset[@data-test-id='multiway-direction-1']//input[@data-test-id='multiway-destination-input']")

    private val fromCitySecond: By = By.xpath("//fieldset[@data-test-id='multiway-direction-2']//input[@data-test-id='multiway-origin-input']")
    private val toCitySecond: By = By.xpath("//fieldset[@data-test-id='multiway-direction-2']//input[@data-test-id='multiway-destination-input']")

    private val dropDownFromCityFirst: By = By.xpath("//fieldset[@data-test-id='multiway-direction-1']//li[@id='multiway_form_origin-item-0']")

    private val dropDownToCityFirst: By = By.xpath("//fieldset[@data-test-id='multiway-direction-1']//li[@id='multiway_form_destination-item-0' and not(contains(., 'Умный'))]")

    private val dropDownFromCitySecond: By = By.xpath("//fieldset[@data-test-id='multiway-direction-2']//li[@id='multiway_form_origin-item-0']")

    private val dropDownToCitySecond: By = By.xpath("//fieldset[@data-test-id='multiway-direction-2']//li[@id='multiway_form_destination-item-0' and not(contains(., 'Умный'))]")

    private val arrivedDateFieldFirst: By = By.xpath("//fieldset[@data-test-id='multiway-direction-1']//button[@data-test-id='multiway-date']")

    private val arrivedDateFieldSecond: By = By.xpath("//fieldset[@data-test-id='multiway-direction-2']//button[@data-test-id='multiway-date']")

    private val dropDownArrivedDateFirst: By = By.xpath("//div[contains(@class,'today') and @aria-disabled='false']")

    private val dropDownArrivedDateSecond: By = By.xpath("//div[not(contains(@class, 'today')) and @aria-disabled='false'][1]")

    private val buttonTicketsSearch: By = By.xpath("//button[@data-test-id='form-submit']")

    fun inputFromCityFirst(city: String){
        val input: WebElement = waitForVisibility(fromCityFirst)
        clearTextWithJavaScript(input)
        input.click()
        input.sendKeys(city)
    }

    fun inputToCityFirst(city: String){
        val input: WebElement = waitForVisibility(toCityFirst)
        input.click()
        input.sendKeys(city)
    }

    fun inputFromCitySecond(city: String){
        val input: WebElement = waitForVisibility(fromCitySecond)
        clearTextWithJavaScript(input)
        input.click()
        input.sendKeys(city)
    }

    fun inputToCitySecond(city: String){
        val input: WebElement = waitForVisibility(toCitySecond)
        input.click()
        input.sendKeys(city)
    }

    fun selectDropDownFromCityFirst(){
        val dropDown: WebElement = waitToBeClickable(dropDownFromCityFirst)
        dropDown.click()
    }

    fun selectDropDownToCityFirst(){
        val dropDown = waitToBeClickable(dropDownToCityFirst)
        dropDown.click()
    }

    fun selectDropDownFromCitySecond(){
        val dropDown: WebElement = waitToBeClickable(dropDownFromCitySecond)
        dropDown.click()
    }

    fun selectDropDownToCitySecond(){
        val dropDown = waitToBeClickable(dropDownToCitySecond)
        dropDown.click()
    }

    fun selectArrivedDateFirst(){
        waitToBeClickable(arrivedDateFieldFirst).click()
        waitToBeClickable(dropDownArrivedDateFirst).click()
    }

    fun selectArrivedDateSecond(){
        waitToBeClickable(arrivedDateFieldSecond).click()
        waitToBeClickable(dropDownArrivedDateSecond).click()
    }

    fun searchTicket(): SearchMultiWayAirlinePage{
        val searchButton = waitToBeClickable(buttonTicketsSearch)
        searchButton.click()
        return SearchMultiWayAirlinePage(driver)

    }

}