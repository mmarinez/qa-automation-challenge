package framework.screens.homeScreen

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By

class HomeScreenAndroid: HomeScreen() {
    override var continueForwardButton: By = AppiumBy.id("fragment_onboarding_forward_button")
    override var getStartedButton: By = AppiumBy.id("fragment_onboarding_done_button")
    override var searchInputField: By = AppiumBy.id("search_container")
    override var searchInputFieldText: By = AppiumBy.id("search_src_text")
    override var searchCriteriaResult: By = AppiumBy.xpath("//*[contains(@text,'QWERTY')]")
    override var googleSearchInputField: By = AppiumBy.xpath("(//android.widget.EditText)[1]")
    override var userWithoutAccountButton: By = AppiumBy.id("signin_fre_dismiss_button")
    override var googleMagnifyGlassButton: By = AppiumBy.xpath("(//android.widget.Button)[3]")
    override var searchTopResult: By = AppiumBy.xpath("//android.view.View[@content-desc=\"Tesla https://www.tesla.com Tesla\"]")
    override var closePanelButton: By = AppiumBy.xpath("//android.widget.Button[contains(@text, 'Close Panel')]")
    override var teslaMenuButton: By = AppiumBy.xpath("//android.widget.Button[contains(@text, 'Menu')]")
}