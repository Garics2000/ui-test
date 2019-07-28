package com.ie.automation

import com.ie.automation.core.pages.InstallPage
import com.ie.automation.core.pages.ProfileSettingsPage
import io.qameta.allure.Description
import org.junit.*
import org.junit.experimental.categories.Category

@Category(CleanEnvironment::class)
class SetupWorkflowTest: BaseTest() {

    @Test
    @Description("Basic installation workflow test")
    fun installWizardWorkflowTest() {
        val installPage = InstallPage();
        val profileSettingsPage = ProfileSettingsPage()

        installPage.navigate()
                   .startWizard()
                   .configureDb()
                   .acceptLicenceAgreement()
                   .createAdminAccount()
        profileSettingsPage.assertUserIsLoggedIn()
    }
}
