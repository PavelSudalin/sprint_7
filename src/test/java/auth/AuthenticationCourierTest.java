package auth;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import ru.yandex.praktikum.auth.AssertsAuth;
import ru.yandex.praktikum.auth.Authentication;
import ru.yandex.praktikum.auth.couriers.AuthCouriers;
import ru.yandex.praktikum.CouriersData;
import ru.yandex.praktikum.registration.couriers.CreatingCouriers;

public class AuthenticationCourierTest {
    static CreatingCouriers creatingCourier = new CreatingCouriers();
    Authentication auth;
    static ValidatableResponse authBaseCourier;
    AuthCouriers authCourier = new AuthCouriers();
    AssertsAuth assertsLogin = new AssertsAuth();
    static CouriersData courierData = new CouriersData();
    static int courierId;

    @BeforeClass
    public static void creatBaseCourier(){
        creatingCourier.creatCourier(courierData.secondCourier());
    }

    @Test
    @DisplayName("Успешная аутентификация")
    @Description("Успешная аутентификация")
    public void successfulCourierAuthentication(){
        auth = Authentication.fromRegistrationCourier(courierData.secondCourier());
        authBaseCourier = authCourier.authenticationCourier(auth);
        assertsLogin.successfullyAuth(authBaseCourier);
    }

    @Test
    @DisplayName("Аутентификация login null")
    @Description("Аутентификация login null")
    public void courierAuthenticationWithEmptyLogin(){
        auth = Authentication.fromRegistrationCourier(courierData.emptyLoginCourier());
        authBaseCourier = authCourier.authenticationCourier(auth);
        assertsLogin.filedAuthLoginOrPasswordNull(authBaseCourier);

    }

    @Test
    @DisplayName("Аутентификация password null")
    @Description("Аутентификация password null")
    public void courierAuthenticationWithEmptyPassword(){ //Оставил так специально т.к должна тут была вернутся 400 ошибка
            auth = Authentication.fromRegistrationCourier(courierData.emptyPasswordCourier());
            authBaseCourier = authCourier.authenticationCourier(auth);
            assertsLogin.filedAuthLoginOrPasswordNull(authBaseCourier);
    }

    @Test
    @DisplayName("Аутентификация не зарегистрированного курьера")
    @Description("Аутентификация не зарегистрированного курьера")
    public void unregisteredCourierAuthentication(){
            auth = Authentication.fromRegistrationCourier(courierData.thirdCourier());
            authBaseCourier = authCourier.authenticationCourier(auth);
            assertsLogin.filedAuthUserNotFound(authBaseCourier);
    }

    @AfterClass
    public static void deleteCourier(){
            courierId = authBaseCourier.extract().path("id");
            creatingCourier.deleteCourier(courierId);
    }
}
