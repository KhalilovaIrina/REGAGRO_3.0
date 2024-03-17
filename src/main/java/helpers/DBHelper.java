package helpers;

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
import java.util.stream.Collectors;

public class DBHelper {
    Random random = new Random();
    private static final String regagro_3_0 = "regagro_3_0";
    private static final String handbooks = "regagro_3_0_handbooks";

    public static Connection getConnectionRegagro() {
        try {
            String url = "jdbc:mariadb://vo.regagro.ru:33630/" + regagro_3_0;
            String user = "regagro";
            String pass = "TTcbH10CVIIL9";
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnectionHandbooks() {
        try {
            String url = "jdbc:mariadb://vo.regagro.ru:33630/regagro_3_0_handbooks";
            String user = "regagro";
            String pass = "TTcbH10CVIIL9";
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Получить данные и их id типа String:Integer
    public Map<String, Integer> getMapWithKeyString(String columnName, String id, String table) {
        Map<String, Integer> map = new HashMap<>();
        Connection conn = getConnectionHandbooks();
        try {
            assert conn != null;
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

    public List<String> values(String query, String dbName, String columnName) {
        List<String> values = new ArrayList<>();
        Connection conn;
        if (dbName.contains(handbooks)) {
            conn = getConnectionHandbooks();
        } else {
            conn = getConnectionRegagro();
        }
        try {
            assert conn != null;
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

    public List<Integer> valuesInt(String query, String dbName, String columnName) {
        List<Integer> values = new ArrayList<>();
        Connection conn;
        if (dbName.contains(handbooks)) {
            conn = getConnectionHandbooks();
        } else {
            conn = getConnectionRegagro();
        }
        try {
            assert conn != null;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    (query);
            while (resultSet.next()) {
                values.add(resultSet.getInt(columnName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    public boolean isValueInDatabase(String columnName, String table, String value) {
        Connection conn = getConnectionRegagro();
        try {
            String select = "SELECT * FROM " + table + " WHERE " + columnName + " = " + " '" + value + "'";
            assert conn != null;
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

    // Проверка успешного удаления из БД
    public boolean isDeleted(String name, String table) {
        Connection conn = getConnectionRegagro();
        try {
            assert conn != null;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT deleted_at FROM " + table + " WHERE name = '" + name + "'");

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomAnimalNumberFromDB() {
        List<String> animalNumbers = values("SELECT *\n" +
                "FROM animals\n" +
                "WHERE is_super_group = 0 AND is_group = 0 AND deleted_at IS NULL AND LENGTH (number) > 5", "regagro_3_0", "number");
        return animalNumbers.get(random.nextInt(animalNumbers.size()));
    }

    public String getRandomAnimalGroupNumberFromDB() {
        List<String> animalNumbers = values("SELECT *\n" +
                "FROM animals\n" +
                "WHERE is_super_group = 0 AND is_group = 1 AND deleted_at IS NULL AND LENGTH (number) > 5", "regagro_3_0", "number");
        return animalNumbers.get(random.nextInt(animalNumbers.size()));
    }
}