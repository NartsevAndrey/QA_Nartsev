package TestPackage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Test1 extends TestsPreparation {
    @Test
    public void test1() {
        StartPage startPage = new StartPage();
        // нажимаем кнопку "Войти в аккаунт"
        AuthorizationForm authorizationForm = startPage.clickButtonLogin();
        // вводим логин
        authorizationForm.enterLogin("adnartsev@yandex.ru");
        // вводим пароль
        authorizationForm.enterPassword("fylh.if");
        // проверяем, что название кнопки изменилось на "Мой профиль"
        startPage.checkTextOnButton();
        // проверяем, что указан верный логин (email)
        startPage.checkLogin();
    }
}