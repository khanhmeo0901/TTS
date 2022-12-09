package ViDu8Bai6;

public class PostgreSqlConnector extends DatabaseConnector{
    @Override
    public void connect()
    {
        System.out.println("Đã kết nối tới Postgresql: " + getUrl());

    }
}
