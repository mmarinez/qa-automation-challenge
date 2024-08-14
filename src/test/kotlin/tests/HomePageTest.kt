package tests

import framework.BaseTest
import framework.screens.screen
import io.qameta.allure.Description
import org.junit.jupiter.api.Test

class HomePageTest: BaseTest() {

    @Test
    @Description("[Home] - User is able to see the search criteria")
    fun testSearchCriteriaResult(): Unit= with(screen) {
        home.goToGoogle()
        home.typeGoogleSearchCriteria()
        assert(home.isSearchCriteriaVisible())
    }

    @Test
    @Description("[Home] - Test Current URL")
    fun testSearchCriteriaCurrentURL(): Unit= with(screen) {
        home.goToGoogle()
        home.typeGoogleSearchCriteria()
        home.pressTopResult()
        home.pressClosePanelButton()
        assert(home.isTeslaMenuDisplayed())

    }

}
