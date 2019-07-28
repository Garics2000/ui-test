package com.ie.automation.core.pages
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import com.ie.test.core.Configuration


class InstallPage : Page() {

    val welcomeLabel: String = "TeamCity First Start"
    val dbSetupLabel: String = "Database Connection Setup"
    val createAdminAccountLabel: String = "Create Administrator Account"

    val pageHeader: SelenideElement = s("h1")
    val proceedButton: SelenideElement = s("#proceedButton")
    val loginField: SelenideElement = s("#input_teamcityUsername")
    val passwordField: SelenideElement = s("#password1")
    val confirmPasswordField: SelenideElement = s("#retypedPassword")
    val submitButton: SelenideElement = s(".submitButton")
    val loginButton: SelenideElement = s(".loginButton")
    val acceptCheckbox: SelenideElement = s("#accept")
    val accountHeader: SelenideElement = s("#header")

    override fun navigate(): InstallPage {
        open(baseUrl)
        return this
    }

     fun startWizard(): InstallPage {
         pageHeader.shouldHave(text(welcomeLabel))
         proceedButton.click()
         return this
     }

     fun configureDb() : InstallPage {
         pageHeader.shouldHave(text(dbSetupLabel))
         proceedButton.click()
         waitForRequestDone()
         return this
     }

     fun acceptLicenceAgreement() : InstallPage {
         submitButton.waitUntil(exist,initWait)
         submitButton.shouldBe(disabled)
         acceptCheckbox.scrollIntoView(true)
         submitButton.scrollIntoView(true)
         acceptCheckbox.setSelected(true)
         submitButton.shouldBe(visible, enabled)
         submitButton.click()
         waitForRequestDone()
         return this
     }

     fun createAdminAccount() {
         val adminLogin = Configuration.get("admin_login")
         val adminPassword = Configuration.get("admin_password")
         accountHeader.shouldHave(text(createAdminAccountLabel))
         loginField.setValue(adminLogin)
         passwordField.setValue(adminPassword)
         confirmPasswordField.setValue(adminPassword)
         loginButton.click()
         waitForRequestDone()
     }
}