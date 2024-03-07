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
        //int kindId = getKindId(kind);
//        List<Integer> markerPlacesIds = dbHelper.getValuesOfTwoConditionsInt("marker_place_id", "kind_id",
//                "marker_type_id", kindId, markerTypeId, "kind_marker_places");
//        List<Integer> newMarkerPlacesIds = new ArrayList<>();
//        for (int i=0; i<markerPlacesIds.size(); i++) {
//            if (dbHelper.isEmpty("deleted_at", "marker_places", "id", markerPlacesIds.get(i))) {
//                newMarkerPlacesIds.add(markerPlacesIds.get(i));
//            }
//        }
        List<String> markerPlaces = dbHelper.values("SELECT name FROM marker_places JOIN kind_marker_places ON marker_places.id = kind_marker_places.marker_place_id WHERE kind_id = 1 AND marker_type_id = 2 AND deleted_at IS NULL");
        return markerPlaces.get(random.nextInt(markerPlaces.size()));
    }





    // Получить рандомное основание для регистрации
    public String getRandomRegistrationGround() {
        List<String> grounds = dbHelper.getColumnData("name", "registration_grounds");
        List<String> testGrounds = grounds.stream()
                .filter(newGrounds -> !newGrounds.matches("Ввоз в РФ"))
                .collect(Collectors.toList());
        return testGrounds.get(random.nextInt(testGrounds.size()));
    }

    // Получить рандомную масть у определенного вида животного
    public String getRandomSuit(String kind) {
        int kindId = getKindId(kind);
        List<String> suits = dbHelper.getValuesOfConditions("name", "kind_id", "suits", kindId);
        return suits.get(random.nextInt(suits.size()));
    }

    // Получить рандомный способ содержания у определенного вида животного
    public String getRandomKeepType(String kind) {
//        int kindId = getKindId(kind);
//        List<Integer> keepTypesIds = dbHelper.getIntValuesOfConditions("keep_type_id", "kind_id", "kind_keep_types", kindId);
//
//        List<Integer> newKeepTypesIds = new ArrayList<>();
//        for (Integer typesId : keepTypesIds) {
//            if (dbHelper.isEmpty("deleted_at", "keep_types", "id", typesId)) {
//                newKeepTypesIds.add(typesId);
//            }
//        }
//        int keepTypesId = newKeepTypesIds.get(random.nextInt(keepTypesIds.size()));
//
//        List<String> keepTypes = dbHelper.getValuesOfConditions("name", "id", "keep_types", keepTypesId);
        List<String> keepTypes = dbHelper.values("SELECT name \n" +
                "FROM keep_types\n" +
                "JOIN kind_keep_types\n" +
                "ON keep_types.id = kind_keep_types.keep_type_id \n" +
                "WHERE kind_id = 1 AND deleted_at IS NULL");
        return keepTypes.get(random.nextInt(keepTypes.size()));
    }

    // Получить рандомное место содержания у определенного вида животного
    public String getRandomKeepPlace(String kind) {
        int kindId = getKindId(kind);
        List<Integer> keepPlacesIds = dbHelper.getIntValuesOfConditions("keep_place_id", "kind_id", "kind_keep_places", kindId);
        int keepPlaceId = keepPlacesIds.get(random.nextInt(keepPlacesIds.size()));
        List<String> keepPlaces = dbHelper.getValuesOfConditions("name", "id", "keep_places", keepPlaceId);
        return keepPlaces.get(0);
    }

    // Получить рандомное направление продуктивности у определенного вида животного
    public String getRandomProductDirection(String kind) {
        int kindId = getKindId(kind);
        List<Integer> productDirectionIds = dbHelper.getIntValuesOfConditions("product_direction_id", "kind_id", "kind_product_directions", kindId);
        int productDirectionId = productDirectionIds.get(random.nextInt(productDirectionIds.size()));
        List<String> productDirections = dbHelper.getValuesOfConditions("name", "id", "product_directions", productDirectionId);
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