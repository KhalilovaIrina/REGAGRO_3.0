package helpers;

import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DBHelper {
    private static final String regagro_3_0 = "regagro_3_0";
    private static final String handbooks = "regagro_3_0_handbooks";

    public Connection getConnection(String host) {
        try {
            String url = "jdbc:mariadb://vo.regagro.ru:33630/" + host;
            String user = "regagro";
            String pass = "TTcbH10CVIIL9";
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

   // Получить список значений типа String одного столбца
    public List<String> getColumnData(String columnName, String table) {
        List<String> data = new ArrayList<>();
        Connection conn = getConnection(handbooks);

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT " + columnName + " FROM " + table);
            while (resultSet.next()) {
                data.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

   // Получить данные и их id типа String:Integer
    public Map<String, Integer> getMapWithKeyString(String columnName, String id, String table) {
        Map<String, Integer> map = new HashMap<>();
        Connection conn = getConnection(handbooks);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT " + columnName + ", " + id + " FROM " + table);
            while (resultSet.next()) {
                map.put(resultSet.getString(columnName), resultSet.getInt(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // Получить список значений одного столбца с 1 условием
    public List<String> getValuesOfConditions(String columnNameSelect, String columnNameCondition, String table, int condition) {
        List<String> values = new ArrayList<>();
        Connection conn = getConnection(handbooks);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT " + columnNameSelect + " FROM " + table + " WHERE " + columnNameCondition + " = " + condition);
            while (resultSet.next()) {
                values.add(resultSet.getString(columnNameSelect));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

   // Получить список значений типа int одного столбца с 1 условием"
    public List<Integer> getIntValuesOfConditions(String columnNameSelect, String columnNameCondition, String table, int condition) {
        List<Integer> values = new ArrayList<>();
        Connection conn = getConnection(handbooks);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT " + columnNameSelect + " FROM " + table + " WHERE " + columnNameCondition + " = " + condition);
            while (resultSet.next()) {
                values.add(resultSet.getInt(columnNameSelect));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }
    public boolean isValueInDatabase(String columnName, String table, String value) {
        Connection conn = getConnection(regagro_3_0);
        try {
            String select = "SELECT * FROM " + table +" WHERE " + columnName +" = " + value;
            PreparedStatement preparedStatement = conn.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isEnterpriseInDatabase(String name) {
        Connection conn = getConnection(regagro_3_0);
        try {
            String select = "SELECT name FROM enterprises WHERE name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(select);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> values(String query, String dbName, String columnName) {
        List<String> values = new ArrayList<>();
        Connection conn = getConnection(dbName);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    (query);
            while (resultSet.next()) {
                values.add(resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    //Проверка успешного удаления объекта
    public boolean isDeleted(String name) {
        Connection conn = getConnection(regagro_3_0);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT deleted_at FROM enterprises WHERE name = '" + name + "'");

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Random random = new Random();

    public String getRandomAnimalNumber() {
        List<String> animalNumbers = values("SELECT number\n" +
                "FROM animals\n" +
                "WHERE is_super_group = 0 AND is_group = 0 AND deleted_at IS NULL", "regagro_3_0", "number");
        return animalNumbers.get(random.nextInt(animalNumbers.size()));
    }
    public String getRandomAnimalGroupNumber() {
        List<String> animalNumbers = values("SELECT number\n" +
                "FROM animals\n" +
                "WHERE is_super_group = 0 AND is_group = 1 AND deleted_at IS NULL", "regagro_3_0", "number");
        return animalNumbers.get(random.nextInt(animalNumbers.size()));
    }
}