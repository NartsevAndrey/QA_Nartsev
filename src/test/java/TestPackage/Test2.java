package TestPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Test2 extends TestsPreparation {

    @DataProvider(name="SearchProvider")
    public Object[][] getCitiesList(){
        return new Object[][] { { "Хвалынск" }, { "Москва" }, { "Санкт-Петербург" } };
    }

    @Test(dataProvider="SearchProvider")
    public void test_2(String city) {
        StartPage startPage = new StartPage();
        // нажимаем кнопку с названием города
        startPage.clickButtonChangeCity();
        // вводим нужный город
        startPage.changeCity(city);
        // проверяем, что название города изменилось на нужный
        startPage.checkCity(city);

        // нажимаем кнопку "Войти в аккаунт"
        AuthorizationForm authorizationForm = startPage.clickButtonLogin();
        // вводим логин
        authorizationForm.enterLogin("adnartsev@yandex.ru");
        // вводим пароль
        authorizationForm.enterPassword("fylh.if");

        // переходим на страницу профиля пользователя
        UserProfile userProfile = startPage.clickButtonUserProfile();
        // сравниваем значение города в верхнем углу и значение города в адресе доставке
        userProfile.checkDeliveryAddress();
    }
}