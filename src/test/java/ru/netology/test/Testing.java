package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlRequests;
import ru.netology.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;


public class Testing {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clear() {
        SqlRequests.clearBD();
    }

    @Test
    void shouldTestHappyPath() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = SqlRequests.getVerificationCode(authInfo);
        val dashboardPage  = verificationPage.validVerify(verificationCode);
        dashboardPage.validFields();
    }

    @Test
    void shouldVerifyIfWrongLogin() {
        val loginPage = new LoginPage();
        val wrongAuthInfo = DataHelper.getWrongAuthInfo();
        loginPage.wrongLogin(wrongAuthInfo);
    }

    @Test
    void shouldNoticeIfIncorrectCode() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val incorrectVerificationCode = DataHelper.getIncorrectVerificationCode();
        verificationPage.incorrectVerify(incorrectVerificationCode);
    }
}