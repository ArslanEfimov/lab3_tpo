package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class SearchHotelPage(driver: WebDriver): Page(driver) {

    private val hotels: By = By.xpath("//div/a[@data-test-id='hotel-preview']")

    private val allFiltersButton: By = By.xpath("//button[@data-test-id='chip']//div[contains(text(), 'Все фильтры')]")

    private val filterHotelsStars: By = By.xpath("//div[@data-test-id='set-filter-hotel_stars_set']//div[@data-test-id='set-filter-row-5']")

    private val searchResultsButton: By = By.xpath("//button[@data-test-id='button']/div[contains(text(), 'Показать результаты')]")

    private val countOfHotelStarsDiv: By = By.xpath("//div[@class='s__zd5b4BW9AJ6975mGH405 s__hDvPAGB7oz4hyFri9_l2']")

    private val filters: By = By.xpath("//div[@class='s__T7_k8_bd1S8I6Pjq3vgM s__DB0DSeUy3jQ_Cmf0tTbK']")

    fun hasResults(): Boolean{
        return waitForPresenceOfAll(hotels).isNotEmpty()
    }

    fun filterHotelsByFiveStar(){
        waitForPresenceOfAll(hotels)
        waitForPresenceOfAll(allFiltersButton)[1].click()
        waitForPresenceOfAll(filters)
        waitForPresence(filterHotelsStars)
        val filterHotelsStarsElement: WebElement = waitToBeClickable(filterHotelsStars)
        clickWithJavaScript(filterHotelsStarsElement)
        waitToBeClickable(searchResultsButton).click()

    }

    fun isFilterByFiveStars(): Boolean{
        waitForPresenceOfAll(hotels)
        val hotelsStars: List<WebElement> = waitForVisibilityOfAll(countOfHotelStarsDiv)
        return hotelsStars.all {
            println(it.text)
            it.text.toInt() == 5
        }
    }



}