package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

public class DataHelper {
    private static Faker faker = new Faker();

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getWrongAuthInfo() {
        return new AuthInfo(faker.name().username(), faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String getIncorrectVerificationCode() {
        return String.valueOf(faker.random().nextLong());
    }
}