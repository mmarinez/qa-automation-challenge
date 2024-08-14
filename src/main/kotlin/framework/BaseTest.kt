package framework

import framework.driver.AppiumService
import framework.driver.Driver
import framework.screens.BaseScreen
import io.qameta.allure.Step
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest: BaseScreen() {
    private val appiumService = AppiumService()
    private val driverInstance = Driver()

    @BeforeEach
    fun setup() {
        appiumClientDriver = driverInstance.createDriver(appiumService.startAppiumServer())
    }

    @AfterEach
    @Step("Taking screenshot and closing mobile Appium driver")
    fun teardown() {
        driverInstance.takeScreenshot()
        driverInstance.closeWebDriver()
        appiumService.stopAppiumServer()
    }
}