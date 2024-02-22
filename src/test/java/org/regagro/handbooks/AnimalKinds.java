package org.regagro.handbooks;

import org.regagro.helpers.DBHelper;

import java.util.Map;

public class AnimalKinds {
    DBHelper dbHelper = new DBHelper();
    Map<String, Integer> kindsAndIds = dbHelper.getMapOfKindAndId("name", "id", "kinds");

    public int getKindId(String kind) {
        return kindsAndIds.get(kind);

    }
}
