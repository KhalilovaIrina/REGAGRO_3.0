import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class first {
    @BeforeEach
    void setup() {

        open("https://v3.dev.regagro.ru/");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void auto() {

        $("input[name=email]").setValue("tur@tur.tur");
        $("input[type=password]").setValue("33221100");
        $x("//button[contains(text(),'Авторизация')]").click();
        $x("//button[contains(text(),'Показать карту')]").shouldBe(visible, Duration.ofSeconds(10));
        $("#accordionMenu > div:nth-child(2)").click();
        $x("//div[contains(text(),'Животное')]").click();
        $x("//button[contains(text(),'Найти другой объект')]").shouldBe(visible, Duration.ofSeconds(10));
        $x("//button[contains(text(),'Найти другой объект')]").click();
        $x("//label[contains(text(),'ИНН')]").click();
        $("#searchOwnerStep1 > div.row.align-items-end.mb-3.inn > div:nth-child(1) > input").setValue("4598700098");
        $x("//button[contains(text(),'Найти')]").click();
        $("div[class=item-descritpion]").click();
        $x("//button[contains(text(),'Выбрать')]").click();

        $x("//span[@class='select2-selection select2-selection--single']/following-sibling::select[@name='kind_id']").click();
      $x("//select[@name='kind_id']").click();

//        $("select.form-select[name=kind_id]").click();
//        $("select.form-select[name=kind_id] option[value=1]").click();



//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(3) > div.col-6.mb-3.form-group.pb-3 > div > span > span.selection > span").click();
//        $("#select2--results > li:nth-child(1) > ul > li:nth-child(1)").click();
//
//
//        $("#addAnimalForm > div:nth-child(1) > div:nth-child(5) > div:nth-child(3) > div > div.row.mb-3 > div:nth-child(2) > div > div:nth-child(1) > div.col-5 > div > div > div > select").click();
//
//        $x("//select[@name='marker_type_id']").click();
//
//
//        $x("//div/select/option[text()='Чип']").click();
//        div/p/a[text()=”Пример текста”]
//         $("input[placeholder=Введите номер]").setValue("484889594777777");
//
//        $("select[id=marker_place_id]").click();
//
//        $x("//input[placeholder='Введите номер')]").setValue("484889594777777");
//
        $("#addAnimalForm > div:nth-child(1) > div:nth-child(15) > div > div:nth-child(2) > div > input").setValue("Лола");
        //$x("input[placeholder='Выберите дату']").setValue("14122023");
//        $x("//span[@class='flatpickr-day today']").click();


    }
}


