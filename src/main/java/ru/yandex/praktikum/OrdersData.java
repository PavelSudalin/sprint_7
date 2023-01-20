package ru.yandex.praktikum;

import ru.yandex.praktikum.orders.Orders;

public class OrdersData {
    public Orders baseOrderChangeableColor(String[] colors) {
        return new Orders("FirstNameExample", "LastNameExample", "AdreesExample",
                4, "+78769654444", 5,"2020-06-06", "Some_comment", colors);
    }
}
