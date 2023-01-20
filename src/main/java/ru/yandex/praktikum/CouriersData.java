package ru.yandex.praktikum;

import ru.yandex.praktikum.registration.Registration;

public class CouriersData {
    public Registration baseCourier(){
        return new Registration("testCourierEx", "12345678", "ExampleCourier");
    }

    public Registration secondCourier(){
        return new Registration("testSecondCourierEx", "12345678", "ExampleSecondCourier");
    }

    public Registration thirdCourier(){
        return new Registration("testThirdCourierEx", "12345678", "ExampleThirdCourier");
    }

    public Registration emptyLoginCourier(){
        return new Registration(null, "12345678","ExampleCourier");
    }

    public Registration emptyPasswordCourier(){
        return new Registration("testCourierEx",null, "ExampleCourier");
    }

    public Registration emptyFirstNameCourier() {
        return new Registration("testCourierEx", "12345678", null);
    }
}
