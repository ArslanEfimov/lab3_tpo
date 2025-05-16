package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class ShortAviaSalesPage(driver: WebDriver) : Page(driver) {

    private val citiesCards: By = By.xpath("//li[contains(@data-test-id,'place-card')]")
    private val travelInput: By = By.xpath("//input[@id='travel_map_cities_search']")
    private val seaAndBeachesButton: By = By.xpath("//button//div[contains(text(), 'Море и пляжи')]")
    private val citiesListPath: By = By.xpath("//button[@class='s__EJ4rq1YzvicZGxVybeBN']")
    private val placesListPath: By = By.xpath("//div[contains(@id, 'travel-map-layer-selector')]")


    fun hasResults(): Boolean{
        val citiesCardElements: List<WebElement> = waitForPresenceOfAll(citiesCards)
        return citiesCardElements.isNotEmpty()
    }

    fun getCityName(city: String): String{
        val citiesCardElements: List<WebElement> = waitForPresenceOfAll(citiesCards)
        var cityElement: WebElement? = null
        citiesCardElements.forEach {
            cityElement = it.findElement(By.xpath("//div[contains(text(), '$city')]"))
        }

        return cityElement?.text ?: ""
    }

    fun searchCity(city: String){
        val inputElement: WebElement = waitToBeClickable(travelInput)
        inputElement.click()
        inputElement.sendKeys(city)
    }

    fun getPlacesCity(): List<WebElement>{
        waitForVisibility(seaAndBeachesButton).click()
        val cityFirst: WebElement = waitForPresenceOfAll(citiesListPath).first()
        cityFirst.click()
        return waitForPresenceOfAll(placesListPath)

    }


}