package handbooks;

import helpers.DBHelper;

import java.util.List;
import java.util.Random;

public class EnterpriseHandbooks {
    DBHelper dbHelper = new DBHelper();
    Random random = new Random();

    public String getRandomEnterpriseType() {
        int legalFormId = 1;
        List<Integer> enterpriseTypeIds = dbHelper.getIntValuesOfConditions("enterprise_type_id", "legal_form_id", "legal_form_enterprise_types", legalFormId);
        int randomEnterpriseTypeId = enterpriseTypeIds.get(random.nextInt(enterpriseTypeIds.size()));
        return dbHelper.getValuesOfConditions("name", "id", "enterprise_types", randomEnterpriseTypeId).get(0);
    }
}
