package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBUtils;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.CardInfo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DBtest {

    @BeforeAll
    static void setUpAll() { SelenideLogger.addListener("allure", new AllureSelenide()); }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    @SneakyThrows
    public void openSource() {
        open("http://localhost:8080");
        DBUtils.cleanTable();
    }

    @Test
    @SneakyThrows
    void shouldBeApprovedWithApprovedCard() {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val mainPage = new MainPage();
        val paymentPage = mainPage.payByCard();
        paymentPage.fillForm(cardInfo);
        assertEquals("APPROVED", DBUtils.getPaymentStatus());
    }

    @Test
    @SneakyThrows
    void shouldBeDeclinedWithDeclinedCard() {
        val cardInfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val mainPage = new MainPage();
        val paymentPage = mainPage.payByCard();
        paymentPage.fillForm(cardInfo);
        assertEquals("DECLINED", DBUtils.getPaymentStatus());
    }

}
