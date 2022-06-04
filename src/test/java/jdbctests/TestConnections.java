package jdbctests;

import java.sql.*;

public class TestConnections {
    public static void main(String[] args) throws SQLException {

        String dbUrl="jdbc:oracle:thin:@52.207.61.129:1521:XE";
        String userName="hr";
        String passWord="hr";


        Connection connection = DriverManager.getConnection(dbUrl,userName,passWord);
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from regions");


        //move pointer to first row
        resultSet.next();


        //getting information with column name
        System.out.println("resultSet.getString(\"region_name\") = " + resultSet.getString("region_name"));

        //getting information with column index
        System.out.println("resultSet.getString(1) = " + resultSet.getString(2));

        while(resultSet.next()){
            System.out.println("resultSet.getInt(1) = " + resultSet.getInt(1) + " - " + resultSet.getString("region_name"));
        }

        //close connection in reverse order
        resultSet.close();
        statement.close();
        connection.close();

    }
}
