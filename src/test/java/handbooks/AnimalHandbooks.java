package handbooks;

import helpers.DBHelper;
import org.junit.jupiter.api.DisplayName;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    public String getRandomMarkerType(String kind) {
        int kindId = getKindId(kind);
        List<String> markerTypes = dbHelper.getValuesOfConditions
                ("marker_type_id", "kind_id", "kind_marker_places", kindId);
        return markerTypes.get(random.nextInt(markerTypes.size()));
    }

    // Получить рандомное места маркирования для животного определенного вида для определенного типа мартирования
    public String getRandomMarkerPlace(String kind) {
        int kindId = getKindId(kind);
        int markerTypeId = 2;
//        List<Integer> markerPlacesIds = dbHelper.getValuesOfTwoConditionsInt("marker_place_id", "kind_id",
//                "marker_type_id", kindId, markerTypeId, "kind_marker_places");
        Map<Integer, String> markerPlaces = dbHelper.getMapWithKeyInteger("name",2, "marker_places");
        return markerPlaces.get(random.nextInt(markerPlaces.size()));
    }

    // Получить рандомное основание для регистрации
    public String getRandomRegistrationGround() throws SQLException {
        List<String> grounds = dbHelper.getColumnData("name", "registration_grounds");
        return grounds.get(random.nextInt(grounds.size()));
    }

    // Получить рандомную масть у определенного вида животного
    public String getRandomSuit(String kind) {
        int kindId = getKindId(kind);
        List<String> suits = dbHelper.getValuesOfConditions("name", "kind_id", "suits", kindId);
        return suits.get(random.nextInt(suits.size()));
    }

    // Получить рандомный способ содержания у определенного вида животного
    public String getRandomKeepType(String kind) throws SQLException {
        int kindId = getKindId(kind);
        List<Integer> keepTypesIds = dbHelper.getIntValuesOfConditions("keep_type_id", "kind_id", "kind_keep_types", kindId);
        int keepTypesId = keepTypesIds.get(random.nextInt(keepTypesIds.size()));
        List<String> keepTypes = dbHelper.getValuesOfConditions("name", "id", "keep_types", keepTypesId);
        return keepTypes.get(0);
    }

    // Получить рандомное место содержания у определенного вида животного
    public String getRandomKeepPlace(String kind) throws SQLException {
        int kindId = getKindId(kind);
        List<Integer> keepPlacesIds = dbHelper.getIntValuesOfConditions("keep_place_id", "kind_id","kind_keep_places", kindId);
        int keepPlaceId = keepPlacesIds.get(random.nextInt(keepPlacesIds.size()));
        List<String> keepPlaces = dbHelper.getValuesOfConditions("name", "id", "keep_places", keepPlaceId);
        return keepPlaces.get(0);
    }

    // Получить рандомное направление продуктивности у определенного вида животного
    public String getRandomProductDirection(String kind) throws SQLException {
        int kindId = getKindId(kind);
        List<Integer> productDirectionIds = dbHelper.getColumnDataInt("product_direction_id", "kind_product_directions");
        int productDirectionId = productDirectionIds.get(random.nextInt(productDirectionIds.size()));
        List<String> productDirections = dbHelper.getValuesOfConditions("name", "id", "product_directions", productDirectionId);
        return productDirections.get(0);
    }

    public String getRandomGender(){
        List<String> gender = new ArrayList<>();
        gender.add("male");
        gender.add("female");
        return gender.get(random.nextInt(gender.size()));
    }
    public String getRandomGenderForGroup(){
        List<String> gender = new ArrayList<>();
        gender.add("male");
        gender.add("female");
        gender.add("mixed");
        return gender.get(random.nextInt(gender.size()));
    }
}