package TestPackage;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Test3 extends TestsPreparation {
    @Test
    public void testCheckOrderGoods() {
        StartPage startPage = new StartPage();
        // переходим в каталог электрических зубных щеток (через запрос в поиске)
        GoodsListPage prodPage = startPage.findItem("электрические зубные щетки");
        // указываем диапазон цен
        prodPage.inputPriceRange(999, 1999);
        // пока это возможно, нажимаем кнопку "показать еще", чтобы отобразить все щетки
        // также формируем список всех щеток и проверяем, что он не пуст
        prodPage.getGoodsList();
        // проверяем, что отобразились все щетки с ценами в нужном диапазоне
        prodPage.checkPriceInRange(999, 1999);
        // добавляем предпоследнюю щетку в корзину
        prodPage.addToShoppingCart();
        // нажимаем кнопку "Перейти в корзину"
        ShoppingCartPage basketPage = prodPage.goToShoppingCart();
        // проверяем значение “До бесплатной доставки осталось”
        basketPage.checkDeliveryText("бесплатной доставки осталось");
        // убеждаемся, что итоговая цена посчитана корректно
        basketPage.checkPrice();
        // увеличиваем количество щеток, чтобы итоговая цена была выше 2999
        basketPage.addGoods(2999);
        // проверяем, что доставка стала бесплатной
        basketPage.checkDeliveryIsFree("бесплатную доставку");
        // убеждаемся, что итоговая цена посчитана корректно
        basketPage.checkPrice();
    }
}
