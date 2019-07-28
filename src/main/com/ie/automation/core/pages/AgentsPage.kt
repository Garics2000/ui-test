package com.ie.automation.core.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import java.time.LocalDate

class AgentsPage : Page() {

    val pageURL = "agents.html"

    val commentFieldLayers: ElementsCollection = ss(".commentTextArea")
    val submitButtonLayers: ElementsCollection = ss(".submitButton")
    val agentsLinks: ElementsCollection = ss(By.ByXPath("//*[@data-test='ring-link']"))
    val authButtons: ElementsCollection = ss(".btn_mini")

    val unauthorizedTab: SelenideElement = s(By.ByLinkText("Unauthorized"))
    val connectedCounter: SelenideElement = s(By.xpath("//*[@id='registeredAgents_Tab']/p[1]/span"))
    val disconnectedCounter: SelenideElement = s(By.xpath("//*[@id='unregisteredAgents_Tab']/p[1]/span"))
    val connectedStatus: SelenideElement = s(By.ByXPath("//span[text()='Connected']"))
    val unauthorizedCounter:SelenideElement = s(By.xpath("//*[@id='unauthorizedAgents_Tab']/p[1]/span"))
    val authorizationDialog: SelenideElement = s("#changeAuthorizeStatusTitle")
    val removeAgentButton: SelenideElement = s(By.ByXPath("//*[@id='removeAgentForm']/input[1]"))

    override fun navigate(): AgentsPage {
        Selenide.open(baseUrl+pageURL)
        return this
    }

    private fun countUnauthorized(): Int {
        return unauthorizedCounter.text().toInt()
    }

    private fun countDisconnected(): Int {
        return disconnectedCounter.text().toInt()
    }

    fun authorize(): AgentsPage {
        val unauthorizedNum = countUnauthorized()
        if(unauthorizedNum > 1 || countDisconnected() > 1) {
            removeAgent()
        }

        if(unauthorizedNum > 0 ) {
           unauthorizedTab.click()
           val authButtons = authButtons.shouldHave(CollectionCondition.sizeGreaterThan(0))

           for(button in authButtons) {
               button.should(exist).click()
               authorizationDialog.should(exist)
               commentFieldLayers.filter(visible).get(0).setValue("AT session: " + LocalDate.now())
               submitButtonLayers.filter(visible).get(0).click()
           }
        }
        return this
    }

    fun waitForConnectedAgent() {
        this.navigate()
        connectedCounter.click()
        // Giving TC some time to update UI elements according latest changes of the agent's status
        connectedCounter.waitUntil(text("1"), 120000, 5000)
    }

    private fun removeAgent() {
        unauthorizedTab.click()
        if(!connectedStatus.exists()) {
            val link = agentsLinks[0].should(exist).getAttribute("href")
            Selenide.open(link)
            removeAgentButton.should(exist).click()
            Selenide.confirm()
            this.navigate()
        }
    }

}