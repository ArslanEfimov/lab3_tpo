package org.example.factories

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions


enum class BrowserType{
    CHR0ME,
    FIREFOX
}

object WebDriverFactory {

    fun createDriver(type: BrowserType): WebDriver {
        return when (type) {
            BrowserType.CHR0ME -> {
                WebDriverManager.chromedriver().setup()
                val options = ChromeOptions().apply {
                    setPageLoadStrategy(PageLoadStrategy.NORMAL) // или EAGER
                    addArguments("--disable-extensions")
                    addArguments("--disable-dev-shm-usage")  // В Linux Chrome по умолчанию использует /dev/shm, что может вызывать crashes при нехватке памяти.
                    addArguments("--disable-notifications");
                }
                ChromeDriver(options).apply {
                    manage().window().maximize()
                }
            }

            BrowserType.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup()
                val options = FirefoxOptions().apply {
                    setPageLoadStrategy(PageLoadStrategy.NORMAL)
                    addArguments("--disable-notifications");
                }
                    FirefoxDriver(options).apply {
                    manage().window().maximize()
                }
            }
        }
    }
}