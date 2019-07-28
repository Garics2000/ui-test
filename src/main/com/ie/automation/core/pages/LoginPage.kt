package com.ie.automation.core.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.ie.test.core.Configuration


class LoginPage : Page() {

    val pageURL = "login.html"

    val adminLogin = Configuration.get("admin_login")
    val adminPassword = Configuration.get("admin_password")

    val loginButton: SelenideElement = s(".loginButton")
    val usernameField: SelenideElement = s("#username")
    val passwordField: SelenideElement = s("#password")

    override fun navigate(): LoginPage {
        Selenide.open(baseUrl+pageURL)
        return this
    }

    fun  <T> doLogin (login: String, password: String, redirectPage: T): T  {
        this.navigate()
        loginButton.waitUntil(Condition.exist,initWait)
        usernameField.setValue(login)
        passwordField.setValue(password)
        loginButton.click()
        waitForRequestDone()
        return redirectPage
    }

    fun doAdminLogin() {
        this.doLogin(adminLogin, adminPassword, this)
    }
}