import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureTests {
    @Test
    @DisplayName("Listener Test")
    public void listenerIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $("[data-target='qbsearch-input.inputButton']").click();
        $("[name='query-builder-test']").sendKeys("Issues");
        $("[name='query-builder-test']").submit();

        $(linkText("microsoft/WSL")).click();
        $("#issues-tab").click();
        $(".opened-by").shouldBe(Condition.exist);
    }

    @Test
    @DisplayName("Step Test")
    public void lambdaIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> open("https://github.com"));


        step("Ввести в поле поиска 'Issues'", () -> {
            $("[data-target='qbsearch-input.inputButton']").click();
            $("[name='query-builder-test']").sendKeys("Issues");
            $("[name='query-builder-test']").submit();

        });
        step("Перейти по ссылке 'microsoft/WSL'", () -> $(linkText("microsoft/WSL")).click());

        step("Кликнуть таб 'Issues'", () -> $("#issues-tab").click());

        step("Проверить наличие открытых issues", () -> {
            $(".opened-by").shouldBe(Condition.exist);
        });

    }

    @Test
    @DisplayName("Annotated Test")
    @Feature("Issues")
    @Owner("Konstantin Ponomarenko")
    @Story("Story")
    @Link("Links")
    public void stepsIssuesSearch() {
        Steps steps = new Steps();
        steps.openMainPage();
        steps.enterIssues();
        steps.followLink();
        steps.clickRepository();
        steps.checkLabel();
    }
}