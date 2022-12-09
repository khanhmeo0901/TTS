package com.example.demoJDBC;
import java.sql.*;
/*
    Chung quy ở đây có vài bước để kết nối database:
    - Bước 1 : Cần khai báo đường link + tên tài khoản db + mật khẩu (Load Driver )
    - Bước 2 : Tạo kết nối (Open Connection)
    - Bước 3 : Tạo câu lệnh truy vấn SQL (Statement).
    - Bước 4 : Thực thi câu lệnh truy vấn SQL (Execute Query).
    - Bước 5 : Đóng kết nối (Close Connection).

    + Các câu lệnh kết nối db có thể sử dụng 1 số câu lệnh để làm việc dễ dàng
    VD:Tìm tên bảng ; Lấy tên hàng , tên cột ; Thêm , sửa ,xóa ...
*/

public class App {
    private static String URL_DB = "jdbc:mysql://localhost:3306/testdb2";
    private static String NAME = "root";
    private static String PASSWOR = "khanh7762144321";

    public static void main(String[] args) {
        nhomTableDb();
        usersTableDb();
        nameColumn();
        getDatabaseMetaData();
        System.out.println("\n");
        showTableData();
    }
    public static void nhomTableDb()
    {
        try {
            // connnect to database 'testdb'
            Connection connection = getconnection(URL_DB, NAME, PASSWOR);
            // crate statement
            Statement statement = connection.createStatement();
            // get data from table 'student'
            ResultSet resultSet = statement.executeQuery("SELECT * from nhom");
            // show data
            System.out.println("GroupID--Title--Level");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " \t " + resultSet.getInt(2) + "\t \t" + resultSet.getString(3));
            }
            // close connection
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public static void usersTableDb()
    {
        try {
            // connnect to database 'testdb'
            Connection connection = getconnection(URL_DB, NAME, PASSWOR);
            // crate statement
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            // get data from table 'student'
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            resultSet.absolute(3);
            // show data
            System.out.println("UserID--Name-----Gmail----------GroupID");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " \t " + resultSet.getString(2) + " \t" + resultSet.getString(3)+"\t"+resultSet.getInt(4));

            }
            // close connection
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public static void nameColumn()
    {
        try {
            Connection connection = getconnection(URL_DB, NAME, PASSWOR);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from nhom ");
            ResultSet rs = preparedStatement.executeQuery();
            // get metadata from rs
            ResultSetMetaData rsmd = rs.getMetaData();
            // show metadata
            System.out.println("Tong so column cua bang 'nhom': "
                    + rsmd.getColumnCount());
            System.out.println("Ten column thu 2: "
                    + rsmd.getColumnName(2));
            System.out.println("Column type cua column thu 2: "
                    + rsmd.getColumnTypeName(2));

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void getDatabaseMetaData()
    {
        try {
            Connection connection = getconnection(URL_DB, NAME, PASSWOR);
            DatabaseMetaData dbmd = connection.getMetaData();

            // show metadata of database
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            System.out.println("UserName: " + dbmd.getUserName());
            System.out.println("Database Product Name: "
                    + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: "
                    + dbmd.getDatabaseProductVersion());

            connection.close();
        }catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
    }
    public static void showTableData()
    {
        try {
            Connection connection = getconnection(URL_DB, NAME, PASSWOR);
            DatabaseMetaData databaseMetaData=connection.getMetaData();
            String table[] = {"TABLE"};
            ResultSet rs = databaseMetaData.getTables(null, null, null, table);
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }

            connection.close();

        }catch (Exception exception)
        {
            System.out.println("Connect failure!");
            exception.printStackTrace();
        }
    }
    public static Connection getconnection(String url_db, String name, String password) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url_db, name, password);
            System.out.println("Connect success!");
        } catch (Exception exception) {
            System.out.println("Connect fail !");
            exception.printStackTrace();
        }
        return connection;
    }
}

