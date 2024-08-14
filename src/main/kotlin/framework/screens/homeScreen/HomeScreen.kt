package framework.screens.homeScreen

import framework.screens.BaseScreen
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent
import io.qameta.allure.Step
import org.openqa.selenium.By

open class HomeScreen: BaseScreen() {
    protected open lateinit var continueForwardButton: By
    protected open lateinit var getStartedButton: By
    protected open lateinit var searchInputField: By
    protected open lateinit var searchInputFieldText: By
    protected open lateinit var searchCriteriaResult: By
    protected open lateinit var googleSearchInputField: By
    protected open lateinit var userWithoutAccountButton: By
    protected open lateinit var googleMagnifyGlassButton: By
    protected open lateinit var searchTopResult: By
    protected open lateinit var closePanelButton: By
    protected open lateinit var teslaMenuButton: By

    @Step("Apply search criteria")
    fun typeGoogleSearchCriteria() {
        tapOn(userWithoutAccountButton)
        Thread.sleep(2000)
        sendKeys(googleSearchInputField, "Tesla Car")
        tapOn(googleMagnifyGlassButton)
        (appiumClientDriver as AndroidDriver).pressKey(KeyEvent().withKey(AndroidKey.ENTER))
    }

    @Step("Press top search criteria result")
    fun pressTopResult() {
        tapOn(searchTopResult)
    }

    @Step("Press the tesla close panel button")
    fun pressClosePanelButton() {
        tapOn(closePanelButton)
    }

    @Step("Press the continue button")
    fun pressTheContinueButtonToMoveForward() {
        tapOn(continueForwardButton)
    }

    @Step("Press the get started button")
    fun pressTheGetStartedButton() {
        tapOn(getStartedButton)
    }

    @Step("Type search criteria")
    fun typeSearchCriteria() {
        tapOn(searchInputField)
        sendKeys(searchInputFieldText, "qwerty")
    }

    @Step("Validate search criteria visibility")
    fun isSearchCriteriaVisible(): Boolean {
        return isElementDisplayed(searchTopResult)
    }

    @Step("Validate tesla menu element visibility")
    fun isTeslaMenuDisplayed(): Boolean {
        return isElementDisplayed(teslaMenuButton)
    }
}

