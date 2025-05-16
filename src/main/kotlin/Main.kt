package org.example

import org.example.factories.BrowserType
import org.example.factories.WebDriverFactory
import org.example.pages.HomeAviaSalesPage

fun main() {
    val chromeDriver = WebDriverFactory.createDriver(BrowserType.CHR0ME)
    try {

        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(chromeDriver)
        homePage.open()
        homePage.acceptCookiesIfExist()
        homePage.closeGoogleIFrameIfExist()
        val shortAviaSalesPage = homePage.goToShortPage()
        shortAviaSalesPage.getPlacesCity()




//        val homePage: HomeAviaSalesPage = HomeAviaSalesPage(chromeDriver)
//        homePage.open()
//        homePage.acceptCookiesIfExist()
//        homePage.closeGoogleIFrameIfExist()
//        homePage.inputFromCity("Моск")
//        homePage.selectDropDownFromCity()
//        homePage.inputToCity("Санкт-")
//        homePage.selectDropDownToCity()
//        homePage.selectArrivedAndDepartureDate()
//        val searchSlowAirlinePage = homePage.searchTicket()
//        homePage.acceptCookiesIfExist()
//        searchSlowAirlinePage.sortTicketsByPrice()
//        println(searchSlowAirlinePage.isSortedByPriceAscending())
//
        Thread.sleep(15000)
    }finally {
        chromeDriver.quit()
    }
}