package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@3.83.123.243:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        resultSet.next();

        //System.out.println(resultSet.getString(2));

        //display departments table in 10 - Administration - 200 - 1700 format
        while (resultSet.next()){

            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - "
                    + resultSet.getString(3) + " - " + resultSet.getInt(4));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //how to find how many rows we have for the query
        //firstly we move to last row
        resultSet.last();

        //Second we get the row count
        int rowCount = resultSet.getRow(); //getRow() method returns current row
        System.out.println(rowCount);

        //We should use beforeFirst() method after last() method
        resultSet.beforeFirst();

        //print all second column information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        //get the resulsetmetadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have?
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //getting column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        //print all the column names dynamically
        //rsMetadata.getColumnCount --> total numbers of columns
        //rsMetadata.getColumnName(i)--> gets column names
        for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }




        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
}
