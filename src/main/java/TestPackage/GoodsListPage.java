package TestPackage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    }

    @Step("Get a list of goods")
    public void getGoodsList() {
        // пока это возможно, нажимаем кнопку "показать еще", чтобы отобразить все щетки
        while(true) {
            try {
                WebElement buttonShowNew = driver.findElement(By.cssSelector("[class*='n-pager-more__button']"));
                buttonShowNew.click();
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
                .until(driver ->
                        driver.findElements(By.cssSelector("[class*='grid-snippet_react']")).size() == countOfGoods);
        // получаем список товаров
        goodsList = driver.findElements(By.cssSelector("[class*='grid-snippet_react']"));
        // проверяем, что список не пуст
        Assert.assertNotEquals(goodsList.size(), 0);
    }

    @Step("Check that prices belong to the interval")
    public void checkPriceInRange(int min, int max) {
        // для каждой щетки из списка товаров парсим ее цену и проверяем, что она принадлежит интервалу
        for (WebElement element : goodsList) {
            int price = parser(element.findElement(By.cssSelector("[class*='grid-snippet'] [data-tid='c3eaad93']"))
                    .getAttribute("textContent"));
            Assert.assertTrue(min  <= price && price <= max);
        }
    }

    @Step("Add to Cart")
    public void addToShoppingCart() {
        // нажимаем кнопку "Добавить в корзину" для предпоследней щетки
        WebElement button = goodsList.get(goodsList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        // ждем пока появится сообщение "товар добавлен в корзину"
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .cssSelector("[class*='_1sjxYfIabK _26mXJDBxtH']")));
    }

    @Step("Click the shopping cart button")
    public ShoppingCartPage goToShoppingCart() {
        // нажимаем кнопку "В корзине" для предпоследней щетки
        goodsList.get(goodsList.size() - 2).findElement(By.cssSelector("[class*='_2w0qPDYwej']")).click();
        return new ShoppingCartPage();
    }

    @Step("Сhange the manufacturer")
    public void changeManufacturer(String manufacturer) {
        // получаем элемент, содержащий список производителей
        WebElement manufactureres = driver.findElement(By.xpath("//div[@class='_1vMoBTNhsM']"));
        // нажимаем на кнопку "Показать все"
        WebElement button = driver.findElement(By.xpath("//a[contains(text(), 'Показать все')]"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        // ищем нужного производителя и кликаем на нужную галочку
        button = manufactureres.findElement(By.xpath("//input[contains(@name, '" + manufacturer + "')]/.."));
        button.click();
    }

    @Step("Check the manufacturer")
    public void checkManufacturer(String manufacturer) {
        // для каждого товара из списка парсим его производителя и проверяем, что он является нужным
        for (WebElement element : goodsList) {
            String text = element.findElement(By.cssSelector("[class=\"_3bNl7A8hOl\"]")).getAttribute("textContent");
            Assert.assertTrue(text.contains(manufacturer));
        }
    }

    @Step("Сhange the sorting criteria")
    public void changeSortingСriterion(String criteria) {
        // нажимаем кнопку с выбором критерия сортировки
        WebElement button = driver.findElement(By.cssSelector("[class*='_3DZGZdv4tl']"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        // выбираем новый критерий
        button = driver.findElement(By.xpath("//span[contains(text(), '" + criteria + "')]"));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    @Step("Check that prices are sorted in increase order")
    public void checkPriceIncrease() {
        // для каждого товара из списка парсим его цену и проверяем, что они расположены в порядке неубывания
        int prevPrice = -1;
        for (WebElement element : goodsList) {
            int price = parser(element.findElement(By.cssSelector("[class*='grid-snippet'] [data-tid='c3eaad93']"))
                    .getAttribute("textContent"));
            Assert.assertTrue(price >= prevPrice);
            prevPrice = price;
        }
    }

    @Step("Choose the color")
    public void changeColor(String color) {
        // нажимаем кнопку с выбором цвета
        WebElement button = driver.findElement(By.xpath("//span[contains(text(), '" + color + "')]/.."));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    @Step("Check that all items have this color")
    public void сheckColor(String color) {
        // для каждого товара из списка парсим его цвет и проверяем, что он является нужным
        for (WebElement element : goodsList) {
            String text = element.findElement(By.cssSelector("[class=\"_3bNl7A8hOl\"]")).getAttribute("textContent");
            Assert.assertTrue(text.contains(color));
        }
    }
}
