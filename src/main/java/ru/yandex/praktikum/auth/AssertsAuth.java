package ru.yandex.praktikum.auth;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class AssertsAuth {
    public void successfullyAuth(ValidatableResponse response) {
         response.assertThat()
                .statusCode(200)
                .body("id", notNullValue());

    }

    public void filedAuthLoginOrPasswordNull(ValidatableResponse response){
        response.assertThat()
             .statusCode(400)
             .body("message", equalTo("Недостаточно данных для входа"));
    }

    public void filedAuthUserNotFound(ValidatableResponse response){
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
