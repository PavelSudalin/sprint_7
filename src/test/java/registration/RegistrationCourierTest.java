package registration;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.yandex.praktikum.auth.Authentication;
import ru.yandex.praktikum.auth.couriers.AuthCouriers;
import ru.yandex.praktikum.registration.AssertsRegistration;
import ru.yandex.praktikum.CouriersData;
import ru.yandex.praktikum.registration.couriers.CreatingCouriers;

public class RegistrationCourierTest {
    CouriersData courierData = new CouriersData();
    CreatingCouriers creatingCourier = new CreatingCouriers();
    AssertsRegistration assertsRegistration = new AssertsRegistration();
    AuthCouriers authCourier = new AuthCouriers();
    ValidatableResponse authBaseCourier;
    ValidatableResponse creatBaseCourier;
    Authentication auth;
    int courierId;

    @Test
    @DisplayName("Создание новой учетной записи курьера")
    @Description("Создается новая УЗ курьера с кодом 201")
    public void creatingCourier(){
        creatBaseCourier = creatingCourier.creatCourier(courierData.baseCourier());
        assertsRegistration.successfulCreation(creatBaseCourier);
    }

    @Test
    @DisplayName("Создание курьера без заполнения обязательного поля логин")
    @Description("Проверяем код и описание ошибки при создание курьера с логином null")
    public void creatingCourierWithNullLogin(){
        creatBaseCourier = creatingCourier.creatCourier(courierData.emptyLoginCourier());
        assertsRegistration.failedCreation(creatBaseCourier);
    }

    @Test
    @DisplayName("Создание курьера без заполнения обязательного поля пароль")
    @Description("Проверяем код и описание ошибки при создание курьера с паролем null")
    public void creatingCourierWithNullPassword(){
        creatBaseCourier = creatingCourier.creatCourier(courierData.emptyPasswordCourier());
        assertsRegistration.failedCreation(creatBaseCourier);
    }

    @Test
    @DisplayName("Создание курьера без заполнения обязательного поля имя")
    @Description("Проверяем код и описание ошибки при создание курьера с именем null")
    public void creatingCourierWithNullFirstName(){
        creatBaseCourier = creatingCourier.creatCourier(courierData.emptyFirstNameCourier());
        assertsRegistration.failedCreation(creatBaseCourier);
    }

    @Test
    @DisplayName("Повторная регистрация учетной записи курьера")
    @Description("Повторная регистрация УЗ курьера с теми же данными")
    public void reRegistrationOfCourier(){
        creatingCourier();
        creatBaseCourier = creatingCourier.creatCourier(courierData.baseCourier());
        assertsRegistration.creatingExistingAccount(creatBaseCourier);
    }

    @After
    public void deleteCourier(){
        if ((creatBaseCourier.extract().statusCode() == 201) || (creatBaseCourier.extract().statusCode() == 409)) {
            auth = Authentication.fromRegistrationCourier(courierData.baseCourier());
            authBaseCourier = authCourier.authenticationCourier(auth);
            courierId = authBaseCourier.extract().path("id");
            creatingCourier.deleteCourier(courierId);
        }
    }
}
