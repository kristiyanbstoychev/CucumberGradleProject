package utils;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDataGeneration {

    static Faker faker = new Faker();

    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String userName = faker.name().username();

    public static String formatDate(String requiredFormat, String region) {
        DateFormat dateFormat = new SimpleDateFormat(requiredFormat, new Locale(region));
        Date date = new Date();
        return dateFormat.format(date);
    }

}
