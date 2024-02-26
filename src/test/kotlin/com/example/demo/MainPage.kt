package com.example.demo

// page_url = https://www.jetbrains.com/
//class MainPage {
//    val seeDeveloperToolsButton = element(byXpath("//*[@data-test-marker='Developer Tools']"))
//    val findYourToolsButton = element(byXpath("//*[@data-test='suggestion-action']"))
//    val toolsMenu = element(byXpath("//div[@data-test='main-menu-item' and @data-test-marker = 'Developer Tools']"))
//    val searchButton = element("[data-test='site-header-search-action']")
//}

import com.codeborne.selenide.ClickOptions
import com.codeborne.selenide.Selectors.byId
import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.WebDriverRunner.driver
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration

import kotlin.random.Random

fun generateRandomEmail(): String {
    val domains = listOf("gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com")
    val randomDomain = domains[Random.nextInt(domains.size)]

    val usernameLength = Random.nextInt(5, 10)
    val username = (1..usernameLength)
        .map { ('a'..'z').random() }
        .joinToString("")

    return "$username@$randomDomain"
}

fun register(): String
{
    open("https://demowebshop.tricentis.com/")
    open("https://demowebshop.tricentis.com/login")
    `$`(byXpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[1]/div[3]/input")).click()
    `$`(byId("gender-male")).click()
    `$`(byId("FirstName")).sendKeys("Name")
    `$`(byId("LastName")).sendKeys("Name")
    val randomEmail = generateRandomEmail()
    `$`(byId("Email")).sendKeys(randomEmail)
    `$`(byId("Password")).sendKeys("123123")
    `$`(byId("ConfirmPassword")).sendKeys("123123")
    `$`(byId("register-button")).click()

    `$`(byXpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[2]/input")).click()
    return randomEmail
}

fun test(mail: String, filename: String)
{
//    open("https://demowebshop.tricentis.com/login")
//    `$`(byId("Email")).sendKeys(mail)
//    `$`(byId("Password")).sendKeys("123123")
//    `$`(byXpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click()
    open("https://demowebshop.tricentis.com/digital-downloads")

    val currentDir = System.getProperty("user.dir")
    println("Current directory: $currentDir")

    File(filename).forEachLine {line ->
        `$`(byXpath("//div[@class='item-box']//h2[@class='product-title']/a[text()='$line']/ancestor::div[@class='product-item']//input[@type='button'] ")).click()
        Thread.sleep(200)
    }

    open("https://demowebshop.tricentis.com/cart")
    `$`(byId("termsofservice")).click()
    `$`(byId("checkout")).click()

    `$`(byId("BillingNewAddress_CountryId")).selectOptionContainingText("United States")
    `$`(byId("BillingNewAddress_City")).sendKeys("Vilnius")
    `$`(byId("BillingNewAddress_Address1")).sendKeys("Gatves g.")
    `$`(byId("BillingNewAddress_ZipPostalCode")).sendKeys("123123")
    `$`(byId("BillingNewAddress_PhoneNumber")).sendKeys("37061498077")

    `$`(byXpath("//*[@id=\"billing-buttons-container\"]/input")).click()
    `$`(byXpath("//*[@id=\"payment-method-buttons-container\"]/input")).click()
    `$`(byXpath("//*[@id=\"payment-info-buttons-container\"]/input")).click()
    `$`(byXpath("//*[@id=\"confirm-order-buttons-container\"]/input")).click()
    val orderId = `$`(byXpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/ul/li[1]")).text
    println(orderId)
}

fun main() {

    val email = register()
    test(email, "data1.txt")
    Thread.sleep(50000)
    /// TEST 1
//    open("https://demoqa.com/")
//
//    `$`(byXpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]")).click()
//    `$`(byXpath("//*[@id=\"app\"]/div/div/div[2]/div/div[4]")).click()
//    open("https://demoqa.com/progress-bar")
//    `$`(byId("startStopButton")).click()
//    val wait = WebDriverWait(webdriver().`object`(), Duration.ofMillis(30000))
//    wait.until(ExpectedConditions.textToBePresentInElementLocated(byXpath("//*[@id=\"progressBar\"]/div"), "100%"))
//    `$`(byId("resetButton")).click()
//    if(`$`(byXpath("//*[@id=\"progressBar\"]/div")).text == "0%")
//    {
//        println("Progress baras resetinos")
//    }
//    Thread.sleep(50000)

    /// TEST 2

//    open("https://demoqa.com/")
//    `$`(byXpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]")).click()
//    `$`(byXpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]")).click()
//    //open("https://demoqa.com/webtables")
//    `$`(byXpath("//*[@id=\"item-3\"]")).click(ClickOptions.usingJavaScript())
//
//    repeat(8) {
//        `$`(byId("addNewRecordButton")).click()
//        `$`(byId("firstName")).sendKeys("Name")
//        `$`(byId("lastName")).sendKeys("Name")
//        `$`(byId("userEmail")).sendKeys("mail@mail.com")
//        `$`(byId("age")).sendKeys("123")
//        `$`(byId("salary")).sendKeys("123")
//        `$`(byId("department")).sendKeys("sales")
//        `$`(byId("submit")).click()
//    }
//
//    Thread.sleep(2000)
//
//    `$`(byXpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[2]/div/div[3]/button")).click()//(ClickOptions.usingJavaScript())
//    Thread.sleep(2000)
//    `$`(byId("delete-record-11")).click(ClickOptions.usingJavaScript())

    Thread.sleep(50000)
}