package org.example.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

class SettingsAviaSalesPage(driver: WebDriver) : Page(driver) {

    private val switchThemeInput: By = By.xpath("//div[@title='dark'][.//*[@id=':profile_settingsr1:']]")

    private val themeIconDark: By = By.xpath("//*[local-name()='svg'][@viewBox='0 0 24 24']")

    fun switchToDarkUserTheme(){
        val switchThemeInputElement: WebElement = waitForVisibility(switchThemeInput)
        switchThemeInputElement.click()
    }

    fun isDarkThemeIcon(): Boolean{
        return waitForPresenceOfAll(themeIconDark).isNotEmpty()
    }



}