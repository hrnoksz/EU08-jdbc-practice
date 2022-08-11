package spartanDB;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestConnection {

    // connection string
    String dbURL ="jdbc:oracle:thin:@44.202.119.26:1521:xe";
    String dbUsername = "SP";
    String dbPassword = "SP";
    String query = "select name, gender from spartans";
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @BeforeEach
    public void connectDB() throws SQLException {
        connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword); // step 1 connect
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // 2. for sending query I need statement object
        resultSet = statement.executeQuery(query);
    }
    @AfterEach
    public void closeDB() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void test1() throws SQLException {
        resultSet.next(); //move cursor to next row
        int row = resultSet.getRow();
        System.out.println("row = " + row);

        //go to last low and get row number
        resultSet.last();
        System.out.println("resultSet = " + resultSet.getRow());

    }
    @Test
    public void test2() throws SQLException {
        //boolean next = resultSet.next(); //if there is next row goes there and returns boolean.

        DatabaseMetaData dbmd = connection.getMetaData();
        System.out.println("dbmd.getDriverName() = " + dbmd.getDriverName());

        System.out.println("dbmd.getDatabaseProductName() = " + dbmd.getDatabaseProductName());

        ResultSetMetaData rsmd = resultSet.getMetaData();
        String columnName = rsmd.getColumnName(1);
        System.out.println("columnName = " + columnName);

        int columnCount = rsmd.getColumnCount();

        List<Map<String, Object>> queryList = new ArrayList<>();

        while (resultSet.next()){
            Map<String, Object> eachRow = new HashMap<>();

            for (int i = 1; i <= columnCount; i++) {

                eachRow.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }
           queryList.add(eachRow);
        }
        System.out.println("queryList = " + queryList);




    }
}