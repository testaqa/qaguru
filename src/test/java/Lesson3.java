import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Lesson3 {
    @Test
    void Lesson3_Task2_AutomationPracticeFormTest() {

        open("https://github.com/");

        $("input[name=q]").setValue("Selenide").pressEnter();
        $("ul.repo-list li:first-child a.v-align-middle").click();
        $("span[data-content='Wiki']").click();

        $$("li a").shouldHave(CollectionCondition.itemWithText("Soft assertions"));

        $$("li a").findBy(text("Soft assertions")).click();

        // Assert
        $x("//ol[li[contains(text(), 'JUnit5')]]/following-sibling::div[1][contains(@class, 'highlight-source-java')]").should(Condition.exist);
    }

    @Test
    void Lesson3_Task3_DragNDropTest() {

        open("https://the-internet.herokuapp.com/drag_and_drop");

        $("#column-a").dragAndDropTo("#column-b");

        $("#column-a header").shouldHave(text("A"));
        $("#column-b header").shouldHave(text("B"));
    }
}
