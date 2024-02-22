package handbooks;

import helpers.DBHelper;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class AnimalHandbooks {
    DBHelper dbHelper = new DBHelper();
    Random random = new Random();

    // Получить id вида животного по названию вида"
    public int getKindId(String kind) {
        Map<String, Integer> kindsAndIds = dbHelper.getMapOfKindAndId("name", "id", "kinds");
        return kindsAndIds.get(kind);
    }

    // Получить рандомный тип маркирования для животного определенного вида
    public String getRandomMarkerType(String kind) {
        int kindId = getKindId(kind);
        List<String> markerTypes = dbHelper.getValuesOfConditions
                ("marker_type_id", "kind_id", "kind_marker_places", kindId);
        return markerTypes.get(random.nextInt(markerTypes.size()));
    }

}
