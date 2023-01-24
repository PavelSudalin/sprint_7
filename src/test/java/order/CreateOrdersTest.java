package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.OrdersData;
import ru.yandex.praktikum.orders.AssertOrders;
import ru.yandex.praktikum.orders.OrderClient;

@RunWith(Parameterized.class)
public class CreateOrdersTest {
    OrdersData ordersData = new OrdersData();
    OrderClient orderClient = new OrderClient();
    AssertOrders assertOrders = new AssertOrders();
    private final String[] colors;
    int track;


    public CreateOrdersTest(String[] colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "Цвет самоката: {0}")
    public static Object[][] getData() {
        return new Object[][] {
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа с одним цветом/двумя цветами/без цвета")
    public void CreatingOrderWithDifferentColors() {
        ValidatableResponse createOrder = orderClient.createNewOrder(ordersData.baseOrderChangeableColor(colors));
        assertOrders.successfulCreation(createOrder);
        track = createOrder.extract().path("track");
    }

    @After
    public void CancelTestOrder() {
        orderClient.cancelOrder(track);
    }
}
