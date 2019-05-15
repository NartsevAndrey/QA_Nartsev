package TestPackage;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Test4 extends TestsPreparation {
    @Test
    public void testCheckFiltersGoods() {
        StartPage startPage = new StartPage();
        // переходим в каталог планшетов (через запрос в поиске)
        GoodsListPage prodPage = startPage.findItem("Планшеты");
        // указываем фильтр по производителю - "Apple"
        prodPage.changeManufacturer("Apple");
        // пока это возможно, нажимаем кнопку "показать еще", чтобы отобразить все планшеты
        // также формируем список всех планшетов
        prodPage.getGoodsList();
        // проверяем, что отобразились все товары данного проиводителя
        prodPage.checkManufacturer("Apple");
        // указываем критерий сортировки - "сначала подешевле"
        prodPage.changeSortingСriterion("сначала подешевле");
        // пока это возможно, нажимаем кнопку "показать еще", чтобы отобразить все планшеты
        // также формируем список всех планшетов
        prodPage.getGoodsList();
        // проверяем, что отобразились все товары отсортированны по возрастанию цены
        prodPage.checkPriceIncrease();
        // указываем фильтр по цвету
        prodPage.changeColor("серебристый");
        // пока это возможно, нажимаем кнопку "показать еще", чтобы отобразить все планшеты
        // также формируем список всех планшетов
        prodPage.getGoodsList();
        // проверяем, что отобразились все товары с данным цветом
        prodPage.сheckColor("silver");
    }
}
