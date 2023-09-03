package com.ie.automation.core.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.ie.automation.core.utils.waitForRequestsDone
import com.ie.test.core.Configuration
import junit.framework.TestCase.fail
import org.openqa.selenium.By

open class Page {
    val baseUrl = Configuration["base_url"]
    val initWait = Configuration["splash_screen_wait"].toLong()

    open fun navigate(): Page {
        return this
    }

    protected fun doActionAndAwaitRequests(action: () -> Unit) {
        try {
            action()
            waitForRequestsDone()
        } catch (e: Exception) {
            fail(e.message)
        }
    }

    // Wrappers to make Selenide's lib short selectors work with Kotlin
    protected fun s(locator: String): SelenideElement {
        return Selenide.`$`(locator)
    }

    protected fun s(locator: By): SelenideElement {
        return Selenide.`$`(locator)
    }

    protected fun ss(locator: String): ElementsCollection {
        return Selenide.`$$`(locator)
    }

    protected fun ss(locator: By): ElementsCollection {
        return Selenide.`$$`(locator)
    }
}


