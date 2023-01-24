package ru.yandex.praktikum.auth;

import ru.yandex.praktikum.registration.Registration;

public class Authentication {
    private String login;
    private String password;
    public Authentication(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public static Authentication fromRegistrationCourier(Registration courier){
        return new Authentication(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
