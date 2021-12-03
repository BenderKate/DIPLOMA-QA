package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBUtils;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.MainPage;
import page.PaymentPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.CardInfo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DBtest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() throws SQLException {
        SelenideLogger.removeListener("allure");
        DBUtils.cleanTable();
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBeApprovedWithApprovedCard() throws SQLException {
        val cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val mainPage = new MainPage();
        mainPage.payByCard();
        val paymentPage = new PaymentPage();
        paymentPage.fillForm(cardInfo);
        assertEquals("APPROVED", DBUtils.getPaymentStatus());
    }

    @Test
    void shouldBeDeclinedWithDeclinedCard() throws SQLException {
        val cardInfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        val mainPage = new MainPage();
        mainPage.payByCard();
        val paymentPage = new PaymentPage();
        paymentPage.fillForm(cardInfo);
        assertEquals("DECLINED", DBUtils.getPaymentStatus());
    }






}
