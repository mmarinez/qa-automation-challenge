package framework.driver

import framework.screens.BaseScreen
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.options.XCUITestOptions
import io.appium.java_client.service.local.AppiumDriverLocalService
import org.junit.jupiter.api.fail
import org.openqa.selenium.SessionNotCreatedException
import org.openqa.selenium.WebDriver
import java.io.File
import java.time.Duration


class Driver: BaseScreen() {
    private val platform = System.getProperty("platform") ?: "Android"
    private val appPackage: String = "com.android.chrome"
    private val appActivity: String = "com.google.android.apps.chrome.Main"
    private val iOSLaunchTimeout: Duration = Duration.ofSeconds(240)

    private fun startActivity(driver: AndroidDriver) {
        val params = mapOf("intent" to "{$appPackage}/{$appActivity}")
        driver.executeScript("mobile: startActivity", params)
    }

    fun createDriver(service: AppiumDriverLocalService): WebDriver {
        return when (platform) {
            "Android" -> getAndroidAppiumClientDriver(service)
            "iOS" -> getIOSAppiumClientDriver(service)
            else -> fail { "Invalid PLATFORM! Please, try again!" }
        }
    }

    private fun getAndroidAppiumClientDriver(service: AppiumDriverLocalService): WebDriver {
        val options = UiAutomator2Options()
            .setDeviceName("Samsung A22")
            .allowTestPackages()
//            .setApp(File("./src/test/resources/apps/sample.apk").absolutePath)
            .eventTimings()
        options.setCapability("appium:dontStopAppOnReset", true)
        options.setCapability("autoAcceptAlerts", "true")
        options.setCapability("autoGrantPermissions", "true")
        options.setCapability("appPackage", appPackage)
        options.setCapability("appActivity", appActivity)
        options.setCapability("browser", "Chrome")
        appiumClientDriver = AndroidDriver(service.url, options)
        startActivity(appiumClientDriver as AndroidDriver)
        return appiumClientDriver
    }

    private fun getIOSAppiumClientDriver(service: AppiumDriverLocalService): WebDriver {
        val options = XCUITestOptions()
            .setPlatformVersion("17.4")
            .setDeviceName("iPhone 15 Pro Max")
            .setCommandTimeouts(Duration.ofSeconds(240))
            .autoAcceptAlerts()
            .fullReset()
            .setApp(File("./src/test/resources/apps/sample.app").absolutePath)
            .setWdaLaunchTimeout(iOSLaunchTimeout)
        appiumClientDriver = try {
            IOSDriver(service.url, options)
        } catch (e: SessionNotCreatedException) {
            options.useNewWDA()
            IOSDriver(service.url, options)
        }
        return appiumClientDriver
    }

    fun closeWebDriver() {
        appiumClientDriver.quit()
    }
}