package com.ie.automation.core.pages
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.ie.test.core.Configuration
import org.openqa.selenium.By

open class Page {

    val baseUrl = Configuration["base_url"]
    val initWait = Configuration["splash_screen_wait"].toLong()
    val refreshWait = Configuration["refresh_wait"].toLong()

    open fun navigate(): Page {
        return this
    }

    // Working with forms we need to make sure that http request is completed before moving next
    fun waitForRequestDone() {
        Selenide.sleep(refreshWait)
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


