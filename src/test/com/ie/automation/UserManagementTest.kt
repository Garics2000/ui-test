package com.ie.automation

import com.ie.automation.core.pages.DashboardPage
import com.ie.automation.core.pages.LoginPage
import com.ie.automation.core.pages.UserManagementPage
import io.qameta.allure.Description
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(PreInstalledEnvironment::class)
class UserManagementTest : BaseTest() {

    @Test
    @Description("Create a user with default access to project")
    fun createUserWithProjectAccess() {
        val loginPage = LoginPage()
        val userManagementPage = UserManagementPage()
        val dashboardPage = DashboardPage()
        val testLogin = "test1"
        val testPass = "test1"

        loginPage.doAdminLogin()

        userManagementPage.navigate()
            .createUser(testLogin, testPass)
            .doLogin(testLogin, testPass, dashboardPage)
            .getProject(DashboardPage.TEST_PROJECT_NAME)
    }
}