package ru.yandex.praktikum.auth.couriers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.auth.Authentication;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Endpoints.*;

public class AuthCouriers {
    @Step("Логин курьера в системе")
    public ValidatableResponse authenticationCourier(Authentication courier){
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(courier)
                .when()
                .post(AUTH_COURIER)
                .then();
    }
}
