import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class QaGuruTests {

    @Test
    void AutomationPracticeFormTest(){

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("testFirstName");

        $("#lastName").setValue("testLastName");

        $("#userEmail").setValue("testEmail@test.com");

        $("input[name=gender][value=Other] ~ label").click();

        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("#dateOfBirthInput").sendKeys("10 May 1979");
        $("#dateOfBirthInput").pressEnter();

        $("#subjectsInput").setValue("Maths").pressEnter();

        $x("//label[contains(text(), 'Reading')]").click();

        $("#uploadPicture").uploadFromClasspath("test.png");

        $("#currentAddress").setValue("testStreet 222, ap. 15");

        $("#state input").setValue("Haryana").pressEnter();

        $("#city input").setValue("Panipat").pressEnter();

        $("#submit").click();

        // Assert
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
}
