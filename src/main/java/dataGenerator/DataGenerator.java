package dataGenerator;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

import java.util.Random;

public class DataGenerator {
    private final Faker faker = new Faker(new Locale("ru"));
    Random random = new Random();

    public String getNickname() {
        return  faker.name().firstName();
    }

    public String getFullName() {
        return faker.name().fullName();
    }

    public String getNumber(int length) {
        return faker.number().digits(length);
    }

    public String getNumberWithFirst(String firstNumber, int length) {
        return firstNumber + faker.number().digits(length);
    }

    public String getNumberRange(int from, int before) {
        return String.valueOf(range(from, before));
    }

    public String getLocalDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }
    public String getPastDate() {
        LocalDate birthDate = LocalDate.now().minusMonths(range(1,11)).minusYears(range(1,3));
        return birthDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    public String getPastDateForBirthGround() {
        int randomInt = random.nextInt(6)+1; // Рандомное число от 1 до 6 для "Рождение"
      LocalDate birthDate = LocalDate.now().minusMonths(randomInt);
        return birthDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    public List<String> getDateRange(String ground) {
        List<String> dateRange = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int randomInt;

        if (ground.contains("Рождение")) {
            randomInt = random.nextInt(6); // Рандомное число от 1 до 6 для "Рождение"
        } else {
            randomInt = random.nextInt(10) + 1; // Рандомное число от 1 до 10 для других случаев
        }

        LocalDate fromDate = currentDate.minusMonths(randomInt);
        LocalDate toDate = fromDate.plusMonths(1);

        if (fromDate.isAfter(currentDate)) {
            fromDate = currentDate;
        }

        dateRange.add(fromDate.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        dateRange.add(toDate.format(DateTimeFormatter.ofPattern("ddMMyyyy")));

        return dateRange;
    }

    public int range(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    public String getEnterpriseName(){
        return faker.company().name();
    }
}
