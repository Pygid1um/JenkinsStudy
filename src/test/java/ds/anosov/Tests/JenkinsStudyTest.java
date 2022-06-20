package ds.anosov.Tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class JenkinsStudyTest extends TestBase {

    @Test
    @Owner("Anosov D.")
    @DisplayName("Тест на заполнение тестовой страницы")
    void fillFormTest() {

        String firstName =  "Dmitry";
        String lastName = "Gromov";
        String email = "gromov@yandex.ru";
        String gender = "Male";
        String phoneNumber = "8905674575";
        String dayOfBirth = "22";
        String monthOfBirth = "June";
        String yearOfBirth = "1995";
        String subject = "English";
        String subject01 = "Chemistry";
        String hobbi = "Music";
        String hobbi01 = "Reading";
        String file = "123.PNG";
        String adress = "Moscow, st. Lenina, d.5";
        String state = "NCR";
        String city = "Noida";

        step("Open test page", () -> {
            open("/automation-practice-form");
        });

        step("Fill page", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(phoneNumber);

            //выбор даты рождения
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(byText(dayOfBirth)).click();

            //выбор хобби
            $("#subjectsInput").setValue(subject).pressEnter();
            $("#subjectsInput").setValue(subject01).pressEnter();
            $("#hobbiesWrapper").$(byText(hobbi)).click();
            $("#hobbiesWrapper").$(byText(hobbi01)).click();

            $("#uploadPicture").uploadFromClasspath(file); //Выбор картинки
            //выбор штата и города
            $("#currentAddress").setValue(adress);
            $(byText("Select State")).click();
            $(byText(state)).click();
            $(byText("Select City")).click();
            $(byText(city)).click();
            $("#submit").click();

        });

        //Проверки
        step("Filling check", () -> {
            $(".modal-body").shouldHave(
                    text(firstName + " " + lastName),
                    text(email),
                    text(gender),
                    text(phoneNumber),
                    text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                    text(subject + "," + " " + subject01),
                    text(hobbi + "," + " " + hobbi01),
                    text(file),
                    text(adress),
                    text(state + " " + city)
            );
        });
    }
}
