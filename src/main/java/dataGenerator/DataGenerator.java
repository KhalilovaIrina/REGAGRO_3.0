package dataGenerator;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));


    public static String getNickname() {
        return  faker.name().firstName();
    }

    public static String getFullName() {
        return faker.name().fullName();
    }

    public static String getNumber(int length) {
        return faker.number().digits(length);
    }

    public static String getNumberWithFirst(String firstNumber, int length) {
        return firstNumber + faker.number().digits(length);
    }

    public static String getNumber(int from, int before) {
        return String.valueOf(range(from, before));
    }

    public static String getLocalDate() {
        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return (day + month + year);
    }

    public static String getPastDate() {
        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        String month = LocalDate.now().minusMonths(range(1, 11)).format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().minusYears(range(1, 3)).format(DateTimeFormatter.ofPattern("yyyy"));
        return (day + month + year);
    }

    public static String getPastDateForBirthGround() {
        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        String month = LocalDate.now().minusMonths(range(0, 6)).format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return (day + month + year);
    }

    public static List<String> getDateRange(String ground) {
        List<String> dateRange = new ArrayList<>();
        int randomInt;
        if (ground.equals("Рождение")) {
            randomInt = range(1, 5);
        } else {
            randomInt = range(1, 11);
        }
        String day = LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
        String monthFrom = LocalDate.now().minusMonths(randomInt).format(DateTimeFormatter.ofPattern("MM"));
        String monthBefore = LocalDate.now().minusMonths(randomInt - 1).format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().minusYears(range(1, 3)).format(DateTimeFormatter.ofPattern("yyyy"));
        dateRange.add(day + monthFrom + year);
        dateRange.add(day + monthBefore + year);
        return dateRange;
    }

    private static int range(int min, int max) {
        Random random = new Random();
        int range = min + random.nextInt(max - min + 1);
        return range;
    }

    public String getRandomValue(List<String> handbooks) {
        Random random = new Random();
        return handbooks.get(random.nextInt(handbooks.size()));
    }

    public static String getEnterpriseName(){
        return faker.company().name();
    }
}
