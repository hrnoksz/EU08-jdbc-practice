package bookITdb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestConnection {

    /*
    Establish a connection
    Execute queries and update statements to the DB
    retrieve the results and use it in your test cases
     */
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @BeforeEach
    public void setConnection() throws SQLException {
        String dbUsername="qa_user";
        String dbPassword="Cybertek11!";
        String dbUrl="jdbc:postgresql://room-reservation-qa3.cxvqfpt4mc2y.us-east-1.rds.amazonaws.com:5432/room_reservation_qa3";
        String query = "select firstname, lastname,role,t.name,t.batch_number,c.location\n" +
                "from users s join team t on s.team_id = t.id join campus c on s.campus_id = c.id\n" +
                "where s.firstname = 'Ase';";
        connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
    }
    @AfterEach
    public void closeConnection() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void connectionTest() throws SQLException {

        resultSet.next(); //moving to cursor to the next row

        System.out.println(resultSet.getString(3));

        while (resultSet.next()){

            System.out.println(resultSet.getString(3));
        }
    }

    @Test
    public void rsmdTest() throws SQLException {

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columCount = resultSetMetaData.getColumnCount();
        System.out.println("columCount = " + columCount);
        System.out.println(resultSetMetaData.getColumnName(5));

        Map<String,Object> resultOfQuery = new LinkedHashMap<>();
        resultSet.next();
        for (int i = 1; i <= columCount; i++) {
             resultOfQuery.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
        }
        System.out.println("resultOfQuery = " + resultOfQuery);
    }
}
/*
dbUsername=qa_user
dbPassword=Cybertek11!
dbUrl=jdbc:postgresql://room-reservation-qa3.cxvqfpt4mc2y.us-east-1.rds.amazonaws.com:5432/room_reservation_qa3
username=sbirdbj@fc2.com
password=asenorval
[18:18]
url=https://cybertek-reservation-qa3.herokuapp.com/

1.select Sp_name from SalesPerson when Sp_name like '%th';

2.select Company_name
from Company
where Company_id in (21,32,412,43,25);

3.select count(*)
from SalesPerson;

4.select Sp_id, avg(Amount)
from Sales
group by Sp_id;

5.select Sp_name
from SalesPerson sp join Sales s on sp.Sp_id=s.Sp_id
join Company c on c.Company_id=s.Company_id
where c.CompanyName='Walmart'

 */