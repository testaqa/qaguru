import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;

public class Lesson5_Allure {
    private final static String REPOSITORY = "testaqa/qaguru";
    private final static String ISSUE_TITLE = "This is a test issue";

    Lesson5Steps steps = new Lesson5Steps();

    @BeforeAll
    public static void initSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }

    @Test
    void AllureCleanSelenide(){
        open("https://github.com/");

        $("input[name=q]").setValue(REPOSITORY);
        $("input[name=q]").submit();
        $("ul.repo-list li:first-child a.v-align-middle").click();

        $("span[data-content='Issues']").click();

        $$("a[id^=issue]").first().shouldHave(Condition.text(ISSUE_TITLE));
    }

    @Test
    void AllureLambda() {

        feature("Issues with Lambda");
        story("Тест который найдет issue по имени");
        parameter("Repository", REPOSITORY);

        step("Открыть главную страницу", () -> {
            open("https://github.com/");
        });

        step("Найти репозиторий " + REPOSITORY, (step) -> {
            step.parameter("Repo title", REPOSITORY);

            $("input[name=q]").setValue(REPOSITORY);
            $("input[name=q]").submit();
            $("ul.repo-list li:first-child a.v-align-middle").click();
        });

        step("Перейти на вкладку Issues", () -> {
            $("span[data-content='Issues']").click();
        });

        step("Проверить наличие бага по названию " + ISSUE_TITLE, (step) -> {
            step.parameter("Issue title", ISSUE_TITLE);

            $$("a[id^=issue]").first().shouldHave(Condition.text(ISSUE_TITLE));
        });
    }

    @Test
    void AllureSteps() {
        steps.openMainPage();
        steps.findRepo(REPOSITORY);
        steps.openIssuesTab();
        steps.verifyIfBugNameExists(ISSUE_TITLE);
    }

    @Test
    void AllureSteps_Failed() {
        steps.openMainPage();
        steps.findRepo(REPOSITORY);
        steps.openIssuesTab();
        steps.verifyIfBugNameExists(ISSUE_TITLE + "!!!");
    }
}
