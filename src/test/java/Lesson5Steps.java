import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Lesson5Steps {
    private static final String BASE_URL = "http://github.com";

    @Step("Открыть главную страницу")
    public void openMainPage() {
        Selenide.open(BASE_URL);
    }

    @Step("Найти репозиторий {repository}")
    public void findRepo(String repository) {
        $("input[name=q]").setValue(repository);
        $("input[name=q]").submit();
        $("ul.repo-list").$("a").click();
    }

    @Step("Перейти на вкладку Issues")
    public void openIssuesTab() {
        $("span[data-content='Issues']").click();
    }

    @Step("Проверить наличие бага c названием {title}")
    public void verifyIfBugNameExists(String title) {
        $$("a[id^=issue]").first().shouldHave(Condition.text(title));
    }
}
