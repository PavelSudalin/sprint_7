package ru.yandex.praktikum.orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.Endpoints.*;

public class OrderClient {

    @Step("Создать новый заказ")
    public ValidatableResponse createNewOrder(Orders orders){
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(orders)
                .when()
                .post(ORDERS)
                .then();
    }

    @Step("Получить список заказов")
    public ValidatableResponse getOrderList(){
        return given()
                .baseUri(BASE_URL)
                .when()
                .get(ORDERS)
                .then();
    }

    @Step("Отмена заказ")
    public ValidatableResponse cancelOrder(int track){
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(track)
                .when()
                .post(CANCEL_ORDER)
                .then();
    }
}
