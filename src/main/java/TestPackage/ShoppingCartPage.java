package TestPackage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends TestsPreparation {

    // список цен, отображаемых в корзине
    // price[0] = стоимость товаров
    // price[1] = стоимость доставки
    // price[2] = скидка
    // price[3] = итоговая цена
    private List<Integer> price;

    @Step("Check the value of \"To get free shipping\"")
    public void checkDeliveryText(String s) {
        // находим нужную надпись
        WebElement text = driver.findElement(By.cssSelector("[class *= '_3EX9adn_xp']"));
        // проверяем, что текст содержит нужную подстроку
        Assert.assertTrue(text.getAttribute("textContent").contains(s));
    }

    @Step("Get prices")
    private void getPrice() {
        price = new ArrayList<Integer>();
        // получаем список элементов, содержащих стоимости
        List<WebElement> priceList = driver.findElements(By.cssSelector("[class *= '_1Q9ASvPbPN']"));
        // получаем стоимость товаров
        String goodsPrice = priceList.get(0).findElement(
                By.cssSelector("[data-auto*='value']")).getAttribute("textContent");
        price.add(parser(goodsPrice));

        // получаем стоимость доставки
        String deliveryPrice = priceList.get(1).findElement(
                By.cssSelector("[data-auto*='value']")).getAttribute("textContent");
        price.add(deliveryPrice.contains("бесплатно") ? 0 : parser(deliveryPrice));
        // получаем стоимость скидки (если она есть)
        if (priceList.size() == 4) {
            String salePrice = priceList.get(2).findElement(
                    By.xpath("//span[text()[contains(., 'Скидка')]]/following-sibling::span"))
                    .getAttribute("textContent");
            price.add(parser(salePrice));
        }
        else {
            // иначе скидка равна нулю
            price.add(0);
        }
        // получаем итоговую сумму
        String totalPrice = priceList.get(priceList.size() - 1).findElement(
                By.cssSelector("[class*='_1oBlNqVHPq']"))
                .getAttribute("textContent");
        price.add(parser(totalPrice));
    }

    @Step("Check that the price is calculated correctly")
    public void checkPrice() {
        // формируем список цен
        getPrice();
        // проверяем, что итогова цена = стоимость товаров + стоимость доставки - скидка
        Assert.assertTrue(price.get(3) == price.get(0) + price.get(1) + price.get(2));
    }

    @Step("Increase the number of goods")
    public void addGoods(int price) {
        // получаем текущую цену
        int currentPrice = parser(driver.findElement(By.xpath("//div[@data-auto='CartOfferPrice']/span/span/span"))
                .getAttribute("textContent"));
        // пока текущая цена меньше, чем необходимый уровень цен
        while(currentPrice < price) {
            // нажимаем кнопку, чтобы добавить еще один товар
            driver.findElement(By.xpath("//button//span[text()='+']")).click();
            // смотрим на текущую цену
            currentPrice = parser(driver.findElement(By.xpath("//div[@data-auto='CartOfferPrice']/span/span/span"))
                    .getAttribute("textContent"));
        }
    }

    @Step("Check that the delivery is free")
    public void checkDeliveryIsFree(String s) {
        // ждем, пока сменится надпись
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.attributeContains(
                        By.cssSelector("[class*='_3EX9adn_xp']"), "textContent", s));
        // проверяем, что надпись поменялась
        checkDeliveryText(s);
        // заново получаем список цен
        getPrice();
        // проверяем, что стоимость доставки стала равной нулю
        Assert.assertTrue(price.get(1) == 0);
    }
}
