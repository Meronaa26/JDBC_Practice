package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {
    String dbUrl="jdbc:oracle:thin:@52.207.61.129:1521:XE";
    String userName="hr";
    String passWord="hr";

    @Test
    public void test1() throws SQLException {
        String dbUrl="jdbc:oracle:thin:@52.207.61.129:1521:XE";
        String userName="hr";
        String passWord="hr";

        Connection connection = DriverManager.getConnection(dbUrl,userName,passWord);
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from departments");


        resultSet.next();
        System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2) + " - " +resultSet.getString(3) + " - " +resultSet.getString(4));

//        while(resultSet.next()){
//            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2) + " - " +resultSet.getString(3) + " - " +resultSet.getString(4));
//        }

        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void test2() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl,userName,passWord);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select * from departments");


        //how to find how many rows we have for the query
        resultSet.last();

        //get rowCount
        int rowCount= resultSet.getRow();
        System.out.println(rowCount);

        //to move before first row after we use last method
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println("resultSet.getString(2) = " + resultSet.getString(2));

        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,userName,passWord);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select * from employees");


        //get the database related data inside the dbmetadata object
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("dbMetaData.getURL() = " + dbMetaData.getURL());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getConnection() = " + dbMetaData.getConnection());


        //get the resultmetadata //rsmd
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //how many columns we have?
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        // getting column names
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName(2) = " + resultSetMetaData.getColumnName(2));


        //print all the column names dynamically
        for (int i = 1; i <=columnCount ; i++) {
            System.out.println("resultSetMetaData.getColumnName(i) = " + resultSetMetaData.getColumnName(i));

        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
