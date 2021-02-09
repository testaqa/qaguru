import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Lesson4 {

    @Test
    void AutomationPracticeFormTest() {
        Faker faker = new Faker();

        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();
        var email = faker.internet().emailAddress();
        var gender = "Other";
        var mobile = faker.phoneNumber().subscriberNumber(10);
        var birthday = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();// LocalDate.of(1979, Month.MAY, 10);
        var subjects = "Maths";
        var hobby = "Reading";
        var pictureFile = "test.png";
        var address = faker.address().fullAddress();
        var state = "Haryana";
        var city = "Panipat";

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);

        $("#lastName").setValue(lastName);

        $("#userEmail").setValue(email);

        $("input[name=gender][value=" + gender + "] ~ label").click();

        $("#userNumber").setValue(mobile);

        $("#dateOfBirthInput").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("#dateOfBirthInput").sendKeys(birthday.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")));
        $("#dateOfBirthInput").pressEnter();

        $("#subjectsInput").setValue(subjects).pressEnter();

        $x("//label[contains(text(), '" + hobby + "')]").click();

        $("#uploadPicture").uploadFromClasspath(pictureFile);

        $("#currentAddress").setValue(address);

        $("#state input").setValue(state).pressEnter();

        $("#city input").setValue(city).pressEnter();

        $("#submit").click();

        // Assert
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $x("//td[text()='Student Name']").sibling(0).shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").sibling(0).shouldHave(text(email));
        $x("//td[text()='Gender']").sibling(0).shouldHave(text(gender));
        $x("//td[text()='Mobile']").sibling(0).shouldHave(text(mobile));
        $x("//td[text()='Date of Birth']").sibling(0).shouldHave(text(birthday.format(DateTimeFormatter.ofPattern("dd LLLL,yyyy"))));
        $x("//td[text()='Subjects']").sibling(0).shouldHave(text(subjects));
        $x("//td[text()='Hobbies']").sibling(0).shouldHave(text(hobby));
        $x("//td[text()='Picture']").sibling(0).shouldHave(text(pictureFile));
        $x("//td[text()='Address']").sibling(0).shouldHave(text(address));
        $x("//td[text()='State and City']").sibling(0).shouldHave(text(state + " " + city));
    }
}