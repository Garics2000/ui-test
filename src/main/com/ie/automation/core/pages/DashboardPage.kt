package com.ie.automation.core.pages

import com.codeborne.selenide.*
import com.codeborne.selenide.Condition.text
import com.ie.test.core.Configuration
import org.openqa.selenium.By

class DashboardPage : Page() {

    val pageURL = "overview.html"
    val buildWait = Configuration["build_wait"]

    val testProjectName: String = "Teamcity Test Metadata Demo"
    val latestBuildStatus = "Tests failed: 2, passed: 2"

    val buildStatusesList: ElementsCollection = ss(".oneLineStatus")
    val triggerBuildButton: SelenideElement = s(Selectors.ByText("Run"))

    override fun navigate(): DashboardPage {
        Selenide.open(baseUrl + pageURL)
        return this
    }

    fun getProject(name: String): DashboardPage {
        this.navigate()
        s(By.linkText(name)).waitUntil(Condition.exist,initWait)
        return this;
    }

    fun triggerBuild() : DashboardPage {
        triggerBuildButton.click()
        return this
    }

    fun expectLatestBuildStatus(status: String) {
        buildStatusesList[0].waitUntil(text(status),buildWait.toLong(), 5000)
    }

}