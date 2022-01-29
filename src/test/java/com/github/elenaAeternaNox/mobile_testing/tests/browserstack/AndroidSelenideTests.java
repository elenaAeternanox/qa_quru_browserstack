package com.github.elenaAeternaNox.mobile_testing.tests.browserstack;

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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Owner("ekomarova")
@Story("Android tests")
@Feature("Android: Selenide browserstack tests")
@Tags({@Tag("selenide_android"),@Tag("browserstack_android")})
public class AndroidSelenideTests extends TestBase {

    @Microservice("Search")
    @Test
    @DisplayName("Successful search in wikipedia android app")
    void searchTestBySelenide() {
        step("Type search", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).val("BrowserStack");
        });

        step("Verify content found", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Microservice("Settings")
    @Test
    @DisplayName("Open Settings in wikipedia android app")
    void openSettingsTest() {
        step("Open app menu", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click()
        );

        step("Open menu Settings", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_settings")).click()
        );

        step("Check: menu Settings is opened", () -> {
            $(MobileBy.xpath(("//*[@text='General']"))).shouldBe(visible);
            $(MobileBy.xpath(("//*[@text='Privacy']"))).shouldBe(visible);
        });
    }
}