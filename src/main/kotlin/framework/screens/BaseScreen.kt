package framework.screens

import io.appium.java_client.android.AndroidDriver
import io.qameta.allure.Attachment
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

open class BaseScreen {
    private val defaultTimeout: Duration = Duration.ofSeconds(30)
    companion object {
        lateinit var appiumClientDriver: WebDriver
        val driver: WebDriver
            get() {
                return appiumClientDriver
            }

    }
    fun goToGoogle() {
        driver.get("https://www.google.com/")
    }

    fun tapOn(element: By) {
        WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.elementToBeClickable(element)).click()
    }

    fun sendKeys(element: By, value: String) {
        WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(value)
        (driver as AndroidDriver).hideKeyboard()
    }

    fun isElementDisplayed(element: By): Boolean {
        return WebDriverWait(driver, defaultTimeout).until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed
    }

    @Attachment(value = "Screenshot", type = "image/png")
    fun takeScreenshot(): ByteArray? {
        return (appiumClientDriver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    }
}