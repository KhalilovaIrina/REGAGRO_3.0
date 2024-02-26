package tests;

import entities.Animal;
import helpers.AnimalFactory;

import java.sql.SQLException;

public class TestTest {
//    public static void main(String[] args) throws SQLException {
//        helpers.DBHelper dbHelper = new helpers.DBHelper();
//        DataGenerator dataGenerator = new DataGenerator();
//        List<String> handbooks = dbHelper.getColumnData("name", "kinds");
//        System.out.println(dataGenerator.getRandomValue(handbooks));
//    }


//    public static void main(String[] args) {
//        MarkerTypes markerTypes = new MarkerTypes();
//        System.out.println(markerTypes.getRandomMarkerType("Пчёлы"));
//    }

    public static void main(String[] args) throws SQLException {
        AnimalFactory factory = new AnimalFactory();
        Animal chicken = factory.createChicken();
        System.out.println(chicken.getKeepPlace());
    }
}
