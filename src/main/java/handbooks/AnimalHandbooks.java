package handbooks;

import helpers.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class AnimalHandbooks {
    DBHelper dbHelper = new DBHelper();
    Random random = new Random();

    // Получить id вида животного по названию вида
    public int getKindId(String kind) {
        Map<String, Integer> kindsAndIds = dbHelper.getMapWithKeyString("name", "id", "kinds");
        return kindsAndIds.get(kind);
    }

    // Получить Название способа содержания по id

    // Получить рандомный тип маркирования для животного определенного вида
//    public String getRandomMarkerType(String kind) {
//        int kindId = getKindId(kind);
//        List<String> markerTypes = dbHelper.getValuesOfConditions
//                ("marker_type_id", "kind_id", "kind_marker_places", kindId);
//        return markerTypes.get(random.nextInt(markerTypes.size()));
//    }

    // Получить рандомное места маркирования для животного определенного вида для определенного типа маркирования
    public String getRandomMarkerPlace(String kind, String markerType) {
        int markerTypeId;
        if (markerType.matches("Табло")) {
            markerTypeId = 13;
        } else {
            markerTypeId = 2;
        }
        List<String> markerPlaces = dbHelper.values(

                "SELECT marker_places.name\n" +
                        "FROM marker_places\n" +
                        "JOIN kind_marker_places ON marker_places.id = kind_marker_places.marker_place_id\n" +
                        "JOIN kinds ON kinds.id = kind_marker_places.kind_id\n" +
                        "WHERE kinds.name = " + "'" + kind + "'" + " AND kind_marker_places.marker_type_id = "
                        + markerTypeId + " AND marker_places.deleted_at IS NULL", "regagro_3_0_handbooks", "name");
        return markerPlaces.get(random.nextInt(markerPlaces.size()));
    }

    // Получить рандомное основание для регистрации
    public String getRandomRegistrationGround() {
//        List<String> grounds = dbHelper.getColumnData("name", "registration_grounds");
        List<String> grounds = dbHelper.values("SELECT * FROM registration_grounds", "regagro_3_0_handbooks", "name");
        List<String> testGrounds = grounds.stream()
                .filter(newGrounds -> !newGrounds.matches("Ввоз в РФ"))
                .collect(Collectors.toList());
        return testGrounds.get(random.nextInt(testGrounds.size()));
    }

    // Получить рандомную масть у определенного вида животного
    public String getRandomSuit(String kind) {
        int kindId = getKindId(kind);
        String query = "SELECT * FROM suits WHERE kind_id = " + kindId;
        List<String> suits = dbHelper.values(query,"regagro_3_0_handbooks", "name");
        return suits.get(random.nextInt(suits.size()));
    }

    // Получить рандомный способ содержания у определенного вида животного
    public String getRandomKeepType(String kind) {
        List<String> keepTypes = dbHelper.values("SELECT keep_types.name FROM keep_types\n" +
                "JOIN kind_keep_types ON keep_types.id = kind_keep_types.keep_type_id \n" +
                "JOIN kinds ON kinds.id = kind_keep_types.kind_id\n" +
                "WHERE kinds.name = " + "'" + kind + "'" + " AND keep_types.deleted_at IS NULL", "regagro_3_0_handbooks", "name");
        return keepTypes.get(random.nextInt(keepTypes.size()));
    }

    // Получить рандомное место содержания у определенного вида животного
    public String getRandomKeepPlace(String kind) {
        int kindId = getKindId(kind);
        String query1 = "SELECT * FROM kind_keep_places WHERE kind_id = " + kindId;
        List<Integer> keepPlacesIds = dbHelper.valuesInt(query1, "regagro_3_0_handbooks", "keep_place_id");
        int keepPlaceId = keepPlacesIds.get(random.nextInt(keepPlacesIds.size()));
        String query2 = "SELECT * FROM keep_places WHERE id = " + keepPlaceId;
        List<String> keepPlaces = dbHelper.values(query2, "regagro_3_0_handbooks", "name");
        return keepPlaces.get(0);
    }

    // Получить рандомное направление продуктивности у определенного вида животного
    public String getRandomProductDirection(String kind) {
        int kindId = getKindId(kind);
        String query1 = "SELECT * FROM kind_product_directions WHERE kind_id = " + kindId;
        List<Integer> productDirectionIds = dbHelper.valuesInt(query1, "regagro_3_0_handbooks", "product_direction_id");
        int productDirectionId = productDirectionIds.get(random.nextInt(productDirectionIds.size()));
        String query2 = "SELECT * FROM product_directions WHERE id = " + productDirectionId;
        List<String> productDirections = dbHelper.values(query2, "regagro_3_0_handbooks", "name");
        return productDirections.get(0);
    }

    public String getRandomGender() {
        List<String> gender = new ArrayList<>();
        gender.add("male");
        gender.add("female");
        return gender.get(random.nextInt(gender.size()));
    }

    public String getRandomGenderForGroup() {
        List<String> gender = new ArrayList<>();
        gender.add("male");
        gender.add("female");
        gender.add("mixed");
        return gender.get(random.nextInt(gender.size()));
    }
}