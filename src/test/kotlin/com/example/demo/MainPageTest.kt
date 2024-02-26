//package com.example.demo
//
//import com.codeborne.selenide.Condition.attribute
//import com.codeborne.selenide.Condition.visible
//import com.codeborne.selenide.Configuration
//import com.codeborne.selenide.Selectors.*
//import com.codeborne.selenide.Selenide
//import com.codeborne.selenide.Selenide.*
//import org.openqa.selenium.chrome.ChromeOptions
//import com.codeborne.selenide.logevents.SelenideLogger
//import io.qameta.allure.selenide.AllureSelenide
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.*
//import org.openqa.selenium.By
//import org.openqa.selenium.Keys
//import org.openqa.selenium.WebDriver
//import org.openqa.selenium.WebElement
//import org.openqa.selenium.support.FindBy
//
//class MainPageTest {
//
//    companion object {
//
//        @JvmStatic
//        @BeforeAll
//        fun setUpAll() {
//            Configuration.browserSize = "1280x800"
//            SelenideLogger.addListener("allure", AllureSelenide())
//        }
//
////        @AfterAll
////        fun close()
////        {
////
////        }
//    }
//
//    @BeforeEach
//    fun setUp() {
//        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
//        Configuration.browserCapabilities = ChromeOptions().addArguments("--remote-allow-origins=*")
//        open("https://demowebshop.tricentis.com/")
//    }
//
//    @Test
//    fun test1() {
//        val xpaths = mapOf(
//            "giftcards" to "//a[@href='/gift-cards']",
//            "products" to "//div[@class='product-item']")
//        val giftcard = `$`(byXpath(xpaths["giftcards"]!!)).should(visible).click()
//
//        var productItems = `$`(byXpath("//div[@class='product-item'][descendant::span[@class='price actual-price' and number(translate(., '\$,', '')) > 99]]/d5" + "ns']/input")).should(visible).click()
//
//        var nameField = `$`(byXpath("//input[@class='recipient-name']"))
//        nameField.should(visible).click()
//        nameField.sendKeys("vardenis")
//
//        nameField = `$`(byXpath("//input[@class='sender-name']"))
//        nameField.should(visible).click()
//        nameField.sendKeys("vardenis")
//
//        `$`(byXpath("//input[@class='qty-input']")).apply {
//            sendKeys(Keys.chord(Keys.CONTROL, "a"))
//            sendKeys("5000")
//        }
//
//        `$`(byXpath("//input[@type='button' and contains(@class,'add-to-cart-button')][1]")).apply {
//            should(visible).click()
//        }
//
//        Thread.sleep(3000)
//
//        `$`(byXpath("//input[@type='button' and contains(@class,'add-to-wishlist-button')][1]")).apply {
//            click()
//        }
//
//        `$`(byXpath("//a[@href='/jewelry']")).should(visible).click()
//
//        `$`(byXpath("//a[@href='/create-it-yourself-jewelry']")).should(visible).click()
//
//        `$`(byId("product_attribute_71_9_15")).selectOptionContainingText("Silver (1")
//
//        `$`(byId("addtocart_71_EnteredQuantity")).apply {
//            sendKeys(Keys.chord(Keys.CONTROL, "a"))
//            sendKeys("26")
//        }
//
//        `$`(byId("product_attribute_71_10_16")).apply {
//            sendKeys(Keys.chord(Keys.CONTROL, "a"))
//            sendKeys("80")
//        }
//
//        `$`(byId("product_attribute_71_11_17_50")).click()
//
//
//        `$`(byId("add-to-cart-button-71")).click()
//
//        Thread.sleep(3000)
//
//        `$`(byId("add-to-wishlist-button-71")).sendKeys(Keys.ENTER)
//
//        `$`(byXpath("//span[@class='cart-label' and text()='Wishlist']\n")).apply {
//            click()
//        }
//
//        `$`(byXpath("(//input[@name='addtocart'])[1]")).click()
//        `$`(byXpath("(//input[@name='addtocart'])[2]")).click()
//
//        `$`(byXpath("//input[@class='button-2 wishlist-add-to-cart-button']")).click()
//
//        val a = byTagName("html").findElement(`$`(By.cssSelector("span.product-price")))
//
//        assertEquals("1002600.00", a.text)
//
//        Thread.sleep(10000)
//    }
//}
