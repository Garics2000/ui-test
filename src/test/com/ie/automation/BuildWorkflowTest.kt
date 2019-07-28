package com.ie.automation

import com.ie.automation.core.pages.AgentsPage
import com.ie.automation.core.pages.DashboardPage
import com.ie.automation.core.pages.LoginPage
import io.qameta.allure.Description
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(PreInstalledEnvironment::class)
class BuildWorkflowTest: BaseTest() {

    @Test
    @Description ("Trigger the build and verify the results")
    fun runBuildAndCheckResultsTest() {
        val dashboardPage = DashboardPage()
        LoginPage().doAdminLogin()

        AgentsPage().navigate()
                    .authorize()
                    .waitForConnectedAgent()

        dashboardPage.navigate()
                     .getProject(dashboardPage.testProjectName)
                     .triggerBuild()
                     .expectLatestBuildStatus(dashboardPage.latestBuildStatus)
    }
}