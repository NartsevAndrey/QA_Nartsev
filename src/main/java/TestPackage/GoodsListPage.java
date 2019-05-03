package TestPackage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class GoodsListPage extends TestsPreparation {

    private List<WebElement> goodsList;

    @Step("Enter a price range")
    public void inputPriceRange(int min, int max) {
        // вводим минимальное значение цены
        WebElement priceFrom = driver.findElement(By.id("glpricefrom"));
        priceFrom.click();
        priceFrom.sendKeys(Integer.toString(min));
        // ждем, пока элементы будут выбраны
        WebElement foundedGoods = driver.findElement(By.cssSelector("[class*='_1PQIIOelRL']"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOf(foundedGoods));

        // вводим минимальное значение цены
        WebElement priceTo = driver.findElement(By.id("glpriceto"));
        priceTo.click();
        priceTo.sendKeys(Integer.toString(max));
        // ждем, пока элементы будут выбраны
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOf(foundedGoods));

        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }

    @Step("Get a list of goods")
    public void getGoodsList() {
        // пока это возможно, нажимаем кнопку "показать еще", чтобы отобразить все щетки
        while(true) {
            try {
                WebElement buttonShowNewElement = driver.findElement(By.cssSelector("[class*='n-pager-more__button']"));
                buttonShowNewElement.click();
            } catch (Exception e) {
                break;
            }
        }
        // находим количество товаров, удовлетворяющих критерию поиска
        int countOfGoods =
                Integer.parseInt(driver.findElement(By.cssSelector(".n-search-preciser__results-count"))
                        .getAttribute("textContent").split(" ")[1]);
        // ждем, чтобы все товары прогрузились
        (new WebDriverWait(driver,30))
                .until(driver -> driver.findElements(By.cssSelector("[class*='grid-snippet_react']")).size() == countOfGoods);
        // получаем список товаров
        goodsList = driver.findElements(By.cssSelector("[class*='grid-snippet_react']"));
        // проверяем, что в списке есть по крайней мере 2 товара (чтобы взять предпоследний)
        Assert.assertTrue(goodsList.size() > 1);
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }

    @Step("Check that prices belong to the interval")
    public void checkPriceInRange(int min, int max) {
        // для каждой щетки из списка товаров парсим ее цену и проверяем, что она принадлежит интервалу
        for (WebElement element : goodsList) {
            int price = parser(element.findElement(By.cssSelector("[class*='grid-snippet'] [data-tid='c3eaad93']"))
                    .getAttribute("textContent"));
            Assert.assertTrue(min  <= price && price <= max);
        }
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }

    @Step("Add to Cart")
    public void addToShoppingCart() {
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
        // нажимаем кнопку "Добавить в корзину" для предпоследней щетки
        WebElement button = goodsList.get(goodsList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        // ждем пока появится сообщение "товар добавлен в корзину"
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='_1sjxYfIabK _26mXJDBxtH']")));
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
    }

    @Step("Click the shopping cart button")
    public ShoppingCartPage goToShoppingCart() {
        // нажимаем кнопку "В корзине" для предпоследней щетки
        goodsList.get(goodsList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']")).click();
        // делаем скриншот
        Screenshoter.makeScreenshot(driver);
        return new ShoppingCartPage();
    }
}
