package org.regagro.handbooks;

import org.regagro.helpers.DBHelper;

import java.util.List;
import java.util.Random;

public class MarkerTypes {
    DBHelper dbHelper = new DBHelper();
    Random random = new Random();

    public String getRandomMarkerType(String kind) {
        AnimalKinds kinds = new AnimalKinds();
        int kindId = kinds.getKindId(kind);
        List<String> markerTypes = dbHelper.getValuesOfConditions
                ("marker_type_id", "kind_id", "kind_marker_places", kindId);
        return markerTypes.get(random.nextInt(markerTypes.size()));
    }
}
