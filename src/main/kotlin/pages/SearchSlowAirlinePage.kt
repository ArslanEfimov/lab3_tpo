package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class SearchSlowAirlinePage(driver: WebDriver) : Page(driver) {

    private val ticketsPath: By = By.xpath("//div[@data-test-id='ticket-preview']")

    private val sortSelectorPath: By = By.xpath("//div[contains(text(), 'Сортировка')]")

    private val cheapestSortRadioButtonPath: By = By.xpath("//div[contains(text(), 'Самые дешёвые')]")

    private val pricesPath: By = By.xpath("//div[@class='s__mvNEtCM6SuXfR8Kopm7T s__pPCa7rJcciF16fYn5k_2 s__wfLcPf6IF1Ayy7uJmdtH']")

    private val favoriteButton: By = By.xpath("//button[@class='s__yqnlLVrbf93m9U6IyVAm']")

    fun hasResults(): Boolean{
        return waitForPresenceOfAll(ticketsPath).isNotEmpty()
    }

    fun sortTicketsByPrice(){
        waitForPresenceOfAll(ticketsPath)
        val sortSelectorElement: WebElement = waitToBeClickable(sortSelectorPath)
        sortSelectorElement.click()
        waitToBeClickable(cheapestSortRadioButtonPath).click()
    }

    fun addToFavorites(): String{
        waitForPresenceOfAll(favoriteButton).first().click()
        return waitForPresence(By.xpath("//div[@role='dialog']//h2")).text
    }

    fun isSortedByPriceAscending(): Boolean{
        waitForPageToLoad(driver, 50)
        Thread.sleep(1000)
        val priceElements = waitForPresenceOfAll(pricesPath)
        var nextPrice: Int = 0
        return priceElements.all {
            val price: Int = it.text.replace("₽", "")
                .replace(" ", "")
                .trim().toInt()

            val tmpMinPrice = nextPrice
            println("min price $tmpMinPrice")
            nextPrice = price
            println("next price $nextPrice")
            nextPrice >= tmpMinPrice
        }
    }

}