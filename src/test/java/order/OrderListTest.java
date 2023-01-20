package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.yandex.praktikum.orders.AssertOrders;
import ru.yandex.praktikum.orders.OrderClient;

public class OrderListTest {
    OrderClient orderClient = new OrderClient();
    AssertOrders assertOrders = new AssertOrders();

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Успешное получение списка заказов")
    public void getOrderList() {
       ValidatableResponse orderList =  orderClient.getOrderList();
       assertOrders.successfulGetListOrders(orderList);
    }
}
