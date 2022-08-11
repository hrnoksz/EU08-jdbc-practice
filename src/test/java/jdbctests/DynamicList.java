package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicList {


    String dbUrl = "jdbc:oracle:thin:@3.83.123.243:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //In order to get column names we need ResultsetMetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //list of maps to keep all information
        List<Map<String, Object>> queryData = new ArrayList<>();

        //total number of columns
        int colCount = rsmd.getColumnCount();

        //loop through each row
        while (resultSet.next()){

            Map<String, Object> row = new HashMap<>();

            //some code to fill dynamically
            for (int i = 1; i <=colCount ; i++) {

                row.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }

            //add ready map row to the list
            queryData.add(row);
        }
        // to print each row line by line inside the list, we use for each loop
        for (Map<String, Object> eachRow : queryData) {
            System.out.println(eachRow);
        }



        resultSet.close();
        statement.close();
        connection.close();

    }
}
