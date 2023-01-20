package ru.yandex.praktikum.registration.couriers;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.registration.Registration;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Endpoints.*;

public class CreatingCouriers {
    @Step("Создание курьера")
    public ValidatableResponse creatCourier(Registration courier){
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(courier)
                .when()
                .post(CREATE_COURIER)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(int id){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .when()
                .delete(DELETE_COURIER + id)
                .then();
    }

}
