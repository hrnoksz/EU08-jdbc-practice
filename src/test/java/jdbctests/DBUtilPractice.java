package jdbctests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DBUtilPractice {

    @Test
    public void test1(){

        //create connection
        DBUtils.createConnection();

        String query = "select first_name, Last_name, salary, job_id\n" +
                "from employees\n" +
                "where rownum < 6";

        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        //print the result
        for (Map<String, Object> eachRow : queryData) {
            System.out.println(eachRow);
        }

        //close the connection
        DBUtils.destroy();
    }

    @Test
    public void test2(){
        //create connection
        DBUtils.createConnection();

        String query = "select first_name, Last_name, salary, job_id\n" +
                "from employees\n" +
                "where rownum < 2";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap);

        //close the connection
        DBUtils.destroy();
    }
}
