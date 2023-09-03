package com.ie.automation.core.pages

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.SelenideElement
import com.ie.test.core.Configuration

class InstallPage : Page() {
    companion object {
        const val WELCOME_LABEL = "TeamCity First Start"
        const val DB_SETUP_LABEL = "Database Connection Setup"
        const val CREATE_ADMIN_ACCOUNT_LABEL = "Create Administrator Account"
    }

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
        pageHeader.shouldHave(text(WELCOME_LABEL))
        proceedButton.click()
        return this
    }

    fun configureDb(): InstallPage {
        doActionAndAwaitRequests {
            pageHeader.shouldHave(text(DB_SETUP_LABEL))
            proceedButton.click()
        }

        return this
    }

    fun acceptLicenceAgreement(): InstallPage {
        doActionAndAwaitRequests {
            submitButton.waitUntil(exist, initWait)
            submitButton.shouldBe(disabled)
            acceptCheckbox.scrollIntoView(true)
            submitButton.scrollIntoView(true)
            acceptCheckbox.setSelected(true)
            submitButton.shouldBe(visible, enabled)
            submitButton.click()
        }

        return this
    }

    fun createAdminAccount() {
        val adminLogin = Configuration.get("admin_login")
        val adminPassword = Configuration.get("admin_password")
        doActionAndAwaitRequests {
            accountHeader.shouldHave(text(CREATE_ADMIN_ACCOUNT_LABEL))
            loginField.value = adminLogin
            passwordField.value = adminPassword
            confirmPasswordField.value = adminPassword
            loginButton.click()
        }
    }
}