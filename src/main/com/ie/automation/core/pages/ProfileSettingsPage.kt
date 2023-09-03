package com.ie.automation.core.pages

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide

class ProfileSettingsPage : Page() {
    companion object {
        const val PAGE_HEADER_LABEL = "My Settings & Tools"
    }

    private val pageURL = "profile.html"
    private val pageHeader = s("h1")

    override fun navigate(): Page {
        val appUrl = baseUrl + pageURL
        Selenide.open(appUrl)
        return this
    }

    fun assertUserIsLoggedIn(): Boolean {
        return pageHeader.shouldHave(text(PAGE_HEADER_LABEL)).exists()
    }
}