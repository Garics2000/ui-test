package com.ie.automation.core.pages


import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

class UserManagementPage : Page() {

    val pageURL = "admin/admin.html?item=users"

    val pageHeaderLabel = "Users"

    val addUserBtn: SelenideElement = s(Selectors.ByText("Create user account"))
    val pageHeader: SelenideElement = s(By.xpath("//*[@id='restPageTitle']/div[1]/span"))
    val submitButton: SelenideElement = s(".submitButton")
    val usernameField: SelenideElement = s("#input_teamcityUsername")
    val passwordField: SelenideElement = s("#password1")
    val passwordConfirmField: SelenideElement = s("#retypedPassword")

    override fun navigate(): UserManagementPage {
        Selenide.open(baseUrl+pageURL)
        return this
    }

    fun createUser(login: String, password: String): LoginPage {
        pageHeader.shouldHave(Condition.text(pageHeaderLabel))
        addUserBtn.click()
        usernameField.setValue(login)
        passwordField.setValue(password)
        passwordConfirmField.setValue(password)
        submitButton.click()
        return LoginPage()
    }


}