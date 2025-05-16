package org.example.pages

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


abstract class Page(protected val driver: WebDriver) {


    fun open(){
        driver.get("https://www.aviasales.ru")
    }
    protected fun waitForVisibility(search: By?, timeoutSeconds: Long = 15): WebElement {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
        return wait.until(ExpectedConditions.visibilityOfElementLocated(search))
    }

    protected fun waitForVisibilityOfAll(search: By?, timeoutSeconds: Long = 15): List<WebElement> {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(search))
    }

    protected fun waitToBeClickable(search: By?, timeoutSeconds: Long = 15): WebElement {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
        return wait.until(ExpectedConditions.elementToBeClickable(search))
    }

    protected fun waitForPresenceOfAll(by: By, timeoutSeconds: Long = 40): List<WebElement> {
        return WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by))
    }

    protected fun waitForPresence(by: By, timeoutSeconds: Long = 40): WebElement {
        return WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(by))
    }

    protected fun clearTextWithJavaScript(webElement: WebElement){
        (driver as JavascriptExecutor)
            .executeScript("arguments[0].value = '';", webElement)
    }

    protected fun clickWithJavaScript(webElement: WebElement){
        (driver as JavascriptExecutor).executeScript("arguments[0].click();", webElement)
    }

    protected fun waitUntilTextInputReady(element: WebElement, timeoutSeconds: Long = 10) {
        WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until<Boolean> {
            try {
                element.isDisplayed && element.isEnabled
            } catch (e: StaleElementReferenceException) {
                false
            }
        }
    }

    protected fun retryOnStale(action: () -> Unit, retries: Int = 10) {
        var attempts = 0
        while (attempts < retries) {
            try {
                action()
                return
            } catch (e: StaleElementReferenceException) {
                attempts++
                Thread.sleep(400)
            }
        }
        throw StaleElementReferenceException("Element remains stale after $retries attempts")
    }


    protected fun waitForPageToLoad(driver: WebDriver, timeoutInSeconds: Long = 15) {
        WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until {
            (driver as JavascriptExecutor).executeScript(
                "return document.readyState"
            ) == "complete"
        }
    }

    fun switchToNewTab(action: () -> Unit) {
        waitForPageToLoad(driver)
        val oldWindows = driver.windowHandles // Все открытые вкладки
        action()
        // Ждем появления новой вкладки (максимум 10 секунд)
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until {
            driver.windowHandles.size > oldWindows.size
        }

        val newWindows = driver.windowHandles
        val newWindow = newWindows.minus(oldWindows).first()
        driver.switchTo().window(newWindow)
    }
}