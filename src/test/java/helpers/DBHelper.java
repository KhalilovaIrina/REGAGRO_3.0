package helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean isTableNotEmpty(String host) {
        try {
            var query = "SELECT COUNT(*) FROM animals";
            var conn = getConnection(host);
            var statement = conn.createStatement();
            var resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                var count = resultSet.getInt(1);
                return count > 0;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @DisplayName("Получить список значений типа String одного столбца")
    public List<String> getColumnData(String columnName, String table) throws SQLException {
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

    @DisplayName("Получить список значений одного столбца")
    public List<Integer> getColumnDataInt(String columnName, String table) throws SQLException {
        List<Integer> data = new ArrayList<>();
        Connection conn = getConnection(handbooks);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT " + columnName + " FROM " + table);
            while (resultSet.next()) {
                data.add(resultSet.getInt(columnName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @DisplayName("Получить данные и их id типа String:Integer")
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

    @DisplayName("Получить данные и их id типа Integer:String")
    public Map<Integer, String> getMapWithKeyInteger(String columnName, int id, String table) {
        Map<Integer, String> map = new HashMap<>();
        Connection conn = getConnection(handbooks);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT " + columnName + ", " + id + " FROM " + table);
            while (resultSet.next()) {
                map.put(resultSet.getInt(id), resultSet.getString(columnName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @DisplayName("Получить список значений одного столбца с 1 условием")
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
    @DisplayName("Получить список значений типа int одного столбца с 1 условием")
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
    @DisplayName("Получить список значений одного столбца с 2 условиями")
    public List<String> getValuesOfTwoConditions
            (String columnNameSelect, String columnNameFirstCondition, String columnNameSecondCondition,
             int condition1, String condition2, String table) {
        List<String> values = new ArrayList<>();
        Connection conn = getConnection(handbooks);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT " + columnNameSelect + " FROM " + table + " WHERE " + columnNameFirstCondition + " = " + condition1 +
                            " AND " + columnNameSecondCondition + " = " + condition2);
            while (resultSet.next()) {
                values.add(columnNameSelect);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    public List<Integer> getValuesOfTwoConditionsInt
            (String columnNameSelect, String columnNameFirstCondition, String columnNameSecondCondition,
             int condition1, int condition2, String table) {
        List<Integer> values = new ArrayList<>();
        Connection conn = getConnection(handbooks);
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT " + columnNameSelect + " FROM " + table + " WHERE " + columnNameFirstCondition + " = " + condition1 +
                            " AND " + columnNameSecondCondition + " = " + condition2);
            while (resultSet.next()) {
                values.add(Integer.valueOf(columnNameSelect));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    public boolean isAnimalInDatabase(String number) {
        Connection conn = getConnection(regagro_3_0);
        try {
            String select = "SELECT number FROM animals WHERE number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(select);
            preparedStatement.setString(1, number);
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


//    public boolean isAnimalInDatabase(String number) {
//        Connection conn = getConnection();
//        String select = "SELECT number FROM animals WHERE number = 'number'";
//        if (!isEmpty()) {
//            return true;
//        } else return false;
//    }
//
//    public boolean isEnterpriseInDatabase(String name) {
//        Connection conn = getConnection();
//        String select = "SELECT name FROM enterprises WHERE name = 'name'";
//        if
//    }

//    @SneakyThrows
//    public static String getStatusForCredit() {
//        var conn = getConnection();
//        var status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
//        var statusName = runner.query(conn, status, new ScalarHandler<String>());
//        return statusName;
//    }

//    public boolean isEmpty() {
//        try {
//            var query = "SELECT COUNT(*) FROM animals";
//            var conn = getConnection();
//            var statement = conn.createStatement();
//            var resultSet = statement.executeQuery(query);
//            resultSet.next();
//            var count = resultSet.getInt(1);
//            return count == 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    @SneakyThrows
//    public static void clearDbTable() {
//        var conn = getConnection();
//
//        runner.execute(conn, "DELETE FROM payment_entity;");
//        runner.execute(conn, "DELETE FROM order_entity;");
//        runner.execute(conn, "DELETE FROM credit_request_entity;");
//    }


}