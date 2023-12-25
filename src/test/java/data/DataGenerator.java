package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));


    public static String getNickname() {
        String name = faker.name().firstName();
        return name;
    }

    public static String getNumber(int lenght) {
        return faker.number().digits(lenght);
    }

    public static String getLocalDate() {
        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return (day+month+year);
    }
    public static String getPastDate() {
        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        String month = LocalDate.now().minusMonths(range(1, 11)).format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().minusYears(range(1, 3)).format(DateTimeFormatter.ofPattern("yyyy"));
        return (day+month+year);
    }


    private static int range(int min, int max) {
        Random random = new Random();
        int range = min + random.nextInt(max - min + 1);
        return range;
    }
}
