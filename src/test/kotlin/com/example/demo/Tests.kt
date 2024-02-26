package com.example.demo

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.logevents.SelenideLogger
import dev.failsafe.internal.util.Assert
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class Tests {

    companion object {

        var userEmail: String = ""

        fun chooseAddressSecondTime()
        {
            Selenide.`$`(Selectors.byId("billing-address-select")).selectOptionContainingText("United States")
        }

        fun chooseAddressFirstTime()
        {
            Selenide.`$`(Selectors.byId("BillingNewAddress_CountryId")).selectOptionContainingText("United States")
            Selenide.`$`(Selectors.byId("BillingNewAddress_City")).sendKeys("Vilnius")
            Selenide.`$`(Selectors.byId("BillingNewAddress_Address1")).sendKeys("Gatves g.")
            Selenide.`$`(Selectors.byId("BillingNewAddress_ZipPostalCode")).sendKeys("123123")
            Selenide.`$`(Selectors.byId("BillingNewAddress_PhoneNumber")).sendKeys("37061498077")
        }

        @JvmStatic
        @BeforeAll
        fun setUpAll() {
            Configuration.browserSize = "1280x800"
            SelenideLogger.addListener("allure", AllureSelenide())

            Selenide.open("https://demowebshop.tricentis.com/")
            Selenide.open("https://demowebshop.tricentis.com/login")
            Selenide.`$`(Selectors.byXpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[1]/div[3]/input")).click()
            Selenide.`$`(Selectors.byId("gender-male")).click()
            Selenide.`$`(Selectors.byId("FirstName")).sendKeys("Name")
            Selenide.`$`(Selectors.byId("LastName")).sendKeys("Name")
            userEmail = generateRandomEmail()
            Selenide.`$`(Selectors.byId("Email")).sendKeys(userEmail)
            Selenide.`$`(Selectors.byId("Password")).sendKeys("123123")
            Selenide.`$`(Selectors.byId("ConfirmPassword")).sendKeys("123123")
            Selenide.`$`(Selectors.byId("register-button")).click()
            Selenide.`$`(Selectors.byXpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[2]/input")).click()
            webdriver().driver().webDriver.quit()
        }

        @BeforeEach
        fun setUp() {
            webdriver().driver().webDriver.quit()
        }
    }

    fun test(filename: String, chooseAddress: () -> Unit)
    {
        Selenide.open("https://demowebshop.tricentis.com/login")
        Selenide.`$`(Selectors.byId("Email")).sendKeys(userEmail)
        Selenide.`$`(Selectors.byId("Password")).sendKeys("123123")
        Selenide.`$`(Selectors.byXpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click()

        Selenide.open("https://demowebshop.tricentis.com/digital-downloads")

        File(filename).forEachLine { line ->
            Selenide.`$`(Selectors.byXpath("//div[@class='item-box']//h2[@class='product-title']/a[text()='$line']/ancestor::div[@class='product-item']//input[@type='button'] ")).click()
            Thread.sleep(200)
        }

        Selenide.open("https://demowebshop.tricentis.com/cart")
        Selenide.`$`(Selectors.byId("termsofservice")).click()
        Selenide.`$`(Selectors.byId("checkout")).click()

        chooseAddress()

        Selenide.`$`(Selectors.byXpath("//*[@id=\"billing-buttons-container\"]/input")).click()
        Selenide.`$`(Selectors.byXpath("//*[@id=\"payment-method-buttons-container\"]/input")).click()
        Selenide.`$`(Selectors.byXpath("//*[@id=\"payment-info-buttons-container\"]/input")).click()
        Selenide.`$`(Selectors.byXpath("//*[@id=\"confirm-order-buttons-container\"]/input")).click()

        // will throw if this element does not exist
        val orderId = Selenide.`$`(Selectors.byXpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/ul/li[1]")).text
        println(orderId)
    }

    @Test
    fun test1()
    {
        test("data1.txt") { chooseAddressFirstTime() }
    }

    @Test
    fun test2()
    {
        test("data2.txt") { chooseAddressSecondTime() }
    }
}