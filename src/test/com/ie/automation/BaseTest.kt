package com.ie.automation

import com.codeborne.selenide.junit.TextReport
import com.codeborne.selenide.logevents.SelenideLogger.addListener
import com.codeborne.selenide.logevents.SelenideLogger.removeListener
import com.ie.test.core.Driver
import io.qameta.allure.selenide.AllureSelenide
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.rules.TestRule

interface CleanEnvironment
interface PreInstalledEnvironment

abstract class BaseTest {
    @Rule
    @JvmField
    var report: TestRule = TextReport().onFailedTest(true).onSucceededTest(true)

    companion object {

        @BeforeClass
        @JvmStatic
        @Suppress("DelegatingClassLoader","error")
        fun beforeClass() {
            Driver.setUp("chrome", "latest")
            addListener("allure", AllureSelenide())
            addListener("AllureSelenide",
            AllureSelenide().screenshots(true).savePageSource(false))
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            Driver.tearDown()
            removeListener<AllureSelenide>("allure")
        }

    }
}