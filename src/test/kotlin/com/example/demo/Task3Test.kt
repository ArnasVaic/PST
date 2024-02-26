package com.example.demo

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeOptions
import kotlin.random.Random

class Task3Test {

    companion object {

        fun generateRandomEmail(): String {
            val domains = listOf("gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com")
            val randomDomain = domains[Random.nextInt(domains.size)]

            val usernameLength = Random.nextInt(5, 10)
            val username = (1..usernameLength)
                .map { ('a'..'z').random() }
                .joinToString("")

            return "$username@$randomDomain"
        }

        @JvmStatic
        @BeforeAll
        fun setUpAll() {
            Configuration.browserSize = "1280x800"
            SelenideLogger.addListener("allure", AllureSelenide())
        }
    }

    @BeforeEach
    fun setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = ChromeOptions().addArguments("--remote-allow-origins=*")
        Selenide.open("https://demoqa.com/")
    }

    @Test
    fun test()
    {

    }

}