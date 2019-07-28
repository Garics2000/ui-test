package com.ie.automation.core.pages

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide

class ProfileSettingsPage : Page() {

    val pageURL = "profile.html"

    val pageHeaderLabel: String = "My Settings & Tools"

    val pageHeader = s("h1")

    override fun navigate(): Page {
        val appUrl = baseUrl + pageURL
        Selenide.open(appUrl)
        return this
    }

    fun assertUserIsLoggedIn(): Boolean {
        return pageHeader.shouldHave(text(pageHeaderLabel)).exists()
    }
}