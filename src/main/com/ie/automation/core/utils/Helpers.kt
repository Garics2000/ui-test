package com.ie.automation.core.utils

import com.codeborne.selenide.Selenide.executeJavaScript
import com.codeborne.selenide.WebDriverRunner
import junit.framework.TestCase.fail
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.concurrent.TimeoutException

// Working with forms we need to make sure that http requests is completed before moving next
fun waitForRequestsDone(timeout: Duration = Duration.ofSeconds(15)) {
    try {
        val webDriver = WebDriverRunner.getWebDriver() ?: throw IllegalStateException("No webdriver available")
        val wait = WebDriverWait(webDriver, timeout.seconds)

        val condition = ExpectedCondition<Boolean> {
            val js = """
                return (window.fetch == null || !window.__fetch_in_progress__);
            """
            try {
                executeJavaScript<Boolean>(js) == true
            } catch (e: Exception) {
                false
            }
        }

        wait.until(condition)
    } catch (e: TimeoutException) {
        fail("Failed to wait async requests completed.")
    }
}