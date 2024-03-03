package tests;

import entities.Address;
import entities.Animal;
import entities.Enterprise;
import helpers.AnimalFactory;
import org.checkerframework.checker.units.qual.A;

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
        Enterprise enterprise = new Enterprise();
        System.out.println(enterprise.getName()+"\n"+ enterprise.getDistrict()+"\n"+enterprise.getCity()+"\n"+enterprise.getStreet()+"\n"+enterprise.getHouse());
    }
}
