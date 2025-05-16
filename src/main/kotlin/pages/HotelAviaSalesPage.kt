package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class HotelAviaSalesPage(driver: WebDriver): Page(driver) {

    private val cityHotelInput: By = By.xpath("//div/span/input[@data-test-id='hotel-autocomplete-input']")
    private val dropDownHotelCity: By = By.xpath("//div[@id='hotel_autocomplete-item-0']")

    private val arrivalDateField: By = By.xpath("//button[@data-test-id='start-date-field']")
    private val dropDownArrivalDate: By = By.xpath("//div[contains(@class,'today') and @aria-disabled='false']")
    private val dropDownDepartureDate: By = By.xpath("//div[not(contains(@class, 'today')) and @aria-disabled='false'][1]")

    private val buttonHotelSearch: By = By.xpath("//button[@data-test-id='form-submit']")

    fun inputCityHotel(city: String){
        retryOnStale(
            action =
            {
                val hotelInput = waitForVisibility(cityHotelInput)
                hotelInput.click()
                waitUntilTextInputReady(hotelInput)
                hotelInput.sendKeys(city)
            }
        )
    }

    fun selectDropdownCityHotel(){
        val dropDownCity = waitToBeClickable(dropDownHotelCity)
        clickWithJavaScript(dropDownCity)
    }

    fun selectArrivalAndDepartureDate(){
        waitToBeClickable(arrivalDateField).click()
        waitForVisibility(dropDownArrivalDate).click()
        waitToBeClickable(dropDownDepartureDate).click()
    }

    fun searchHotels(): SearchHotelPage{
        val searchButton = waitToBeClickable(buttonHotelSearch)
        searchButton.click()
        return SearchHotelPage(driver)
    }



}