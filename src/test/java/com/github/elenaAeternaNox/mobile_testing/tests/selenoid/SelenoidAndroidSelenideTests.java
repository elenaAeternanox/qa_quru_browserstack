package com.github.elenaAeternaNox.mobile_testing.tests.selenoid;

import annotations.Microservice;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("ekomarova")
@Story("Android tests")
@Feature("Android: Selenoid tests")
@Tags({@Tag("selenide_android"),@Tag("selenoid_android")})
public class SelenoidAndroidSelenideTests extends SelenoidTestBase {

    @Microservice("Search")
    @Test
    @DisplayName("Successful search in wikipedia android app")
    void searchTest() {
        back();

        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });
        step("Verify content found", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Microservice("Settings")
    @Test
    @DisplayName("Add new language for Wikipedia")
    void settingLanguageTest() {
        back();

        step("Open menu", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/menu_icon")).click()
        );
        step("Open settings", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click()
        );
        step("Open 'Wikipedia languages' section", () ->
                $(MobileBy.xpath(("//*[@text='Wikipedia languages']"))).click()
        );
        step("Click 'Add language' button", () ->
                $(MobileBy.xpath(("//*[@text='ADD LANGUAGE']"))).click()
        );
        step("Search and select Russian language", () -> {
                    $(MobileBy.id("org.wikipedia.alpha:id/menu_search_language")).click();
                    $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("russian");
                    $(MobileBy.id("org.wikipedia.alpha:id/localized_language_name")).shouldHave(text("Русский")).click();
                }
        );
        back();
        step("Check 'Wikipedia languages' has russian language", () ->
                $(MobileBy.xpath(("//*[@text='English, Русский']"))).shouldBe(visible)
        );
    }
}