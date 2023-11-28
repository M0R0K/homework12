import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ввести в поле поиска 'Issues'")
    public void enterIssues() {
        $("[data-target='qbsearch-input.inputButton']").click();
        $("[name='query-builder-test']").sendKeys("Issues");
        $("[name='query-builder-test']").submit();
    }

    @Step("Перейти по ссылке 'microsoft/WSL'")
    public void followLink() {
        $(linkText("microsoft/WSL")).click();
    }

    @Step("Кликнуть таб 'Issues'")
    public void clickRepository() {
        $("#issues-tab").click();
    }

    @Step("Проверить наличие открытых issues")
    public void checkLabel() {
        $(".opened-by").shouldBe(Condition.exist);
    }
}