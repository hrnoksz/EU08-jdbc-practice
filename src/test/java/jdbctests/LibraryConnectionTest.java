package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class LibraryConnectionTest {

    @Test
    public void test1() throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@3.83.123.243:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");

        // once you set up connection default pointer looks for 0
        //next() --> move pointer to first row
        resultSet.next();


        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }

}
