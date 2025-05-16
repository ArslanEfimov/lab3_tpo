package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
class HomeAviaSalesPage(driver: WebDriver) : Page(driver) {


    private val fromCity: By = By.xpath("//input[@data-test-id='origin-input']")
    private val toCity: By = By.xpath("//input[@data-test-id='destination-input']")

    private val dropDownFromCity: By = By.xpath("//li[@id='avia_form_origin-item-0']")

    private val dropDownToCity: By = By.xpath("//li[@id='avia_form_destination-item-0' and not(contains(., 'Умный'))]")

    private val hotelsLink: By = By.xpath("//a[@href='/hotels?adults=1&source=tab_change' and @class='s__copbTnCU01Z8RvQlTAy3']")

    private val shortsLink: By = By.xpath("//a[@href='/guides?source=tab' and @class='s__copbTnCU01Z8RvQlTAy3']")

    private val profileButton: By = By.xpath("//button[@data-test-id='profile-button']")

    private val dropDownProfiles: By = By.xpath("//ul/li[@data-test-id='settings-item']")

    private val acceptCookiesButton = By.xpath("//button[@data-test-id='accept-cookies-button']")

    private val arrivedDateField: By = By.xpath("//button[@data-test-id='start-date-field']")

    private val dropDownArrivedDate: By = By.xpath("//div[contains(@class,'today') and @aria-disabled='false']")

    private val dropDownDepartureDate: By = By.xpath("//div[not(contains(@class, 'today')) and @aria-disabled='false'][1]")

    private val buttonTicketsSearch: By = By.xpath("//button[@data-test-id='form-submit']")

    private val buttonToCloseHotelClue: By = By.xpath("//*[@id=\"tippy-5\"]/div/div[1]/button")

    private val buttonToDifficultPath: By = By.xpath("//button[@data-test-id='switch-to-multiwayform']")


    fun acceptCookiesIfExist(){
        try {
            waitToBeClickable(acceptCookiesButton, 2).click()
        }
        catch (_ : Exception){}
    }

    fun inputFromCity(city: String){
        val input: WebElement = waitForVisibility(fromCity)
        clearTextWithJavaScript(input)
        input.click()
        input.sendKeys(city)
    }

    fun inputToCity(city: String){
        val input: WebElement = waitForVisibility(toCity)
        input.click()
        input.sendKeys(city)
    }

    fun selectDropDownFromCity(){
        val dropDown: WebElement = waitToBeClickable(dropDownFromCity)
        dropDown.click()
    }

    fun selectDropDownToCity(): SearchFastAirlinePage{
        val dropDown = waitToBeClickable(dropDownToCity)
        dropDown.click()
        return SearchFastAirlinePage(driver)
    }

    fun goToHotelPage(): HotelAviaSalesPage{
        val tabHotel: WebElement = waitForVisibility(hotelsLink)
        tabHotel.click()
        return HotelAviaSalesPage(driver)
    }

    fun goToShortPage(): ShortAviaSalesPage{
        val shortLinkElement: WebElement = waitForVisibility(shortsLink)
        shortLinkElement.click()
        return ShortAviaSalesPage(driver)
    }


    fun goToSettingsPage(): SettingsAviaSalesPage{
        val profileButtonElement: WebElement = waitToBeClickable(profileButton)
        profileButtonElement.click()
        val dropDownProfilesElement: WebElement = waitToBeClickable(dropDownProfiles)
        dropDownProfilesElement.click()
        return SettingsAviaSalesPage(driver)
    }

    fun closeGoogleIFrameIfExist() {
        try {
            driver.switchTo().frame(waitForVisibility(By.xpath("/html/body/div[20]/iframe"), timeoutSeconds = 10))
            waitForVisibility(By.xpath("/html/body/div/div[1]/div/div[1]/div[2]"), timeoutSeconds = 10).click()
            driver.switchTo().parentFrame()
        } catch (_: Exception){}
    }

    fun closeHotelClue(){
        try {
            waitToBeClickable(buttonToCloseHotelClue, 3).click()
        } catch (_: Exception){}
    }

    fun selectArrivedAndDepartureDate(){
        waitToBeClickable(arrivedDateField).click()
        waitForVisibility(dropDownArrivedDate).click()
        waitToBeClickable(dropDownDepartureDate).click()
    }

    fun searchTicket(): SearchSlowAirlinePage{
        val searchButton = waitToBeClickable(buttonTicketsSearch)
        switchToNewTab { searchButton.click() }
        return SearchSlowAirlinePage(driver)

    }

    fun goToMultiWayAviaSalesPage(): MultiWayAviaSalesPage{
        waitToBeClickable(buttonToDifficultPath, 3).click()
        return MultiWayAviaSalesPage(driver)
    }







}