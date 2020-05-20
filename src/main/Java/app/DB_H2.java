package app;


import java.sql.*;

public class DB_H2 {

//    URL dbc:h2:tcp://localhost/~/db


    private static void createDB() {
        try (Connection h2 = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "")) {

            Statement stmt = h2.createStatement();

            String schemaCreate = "CREATE SCHEMA IF NOT EXISTS db AUTHORIZATION sa";
            stmt.executeUpdate(schemaCreate);

//        CREATE TABLE SERVICE
            String serviceCreate = "DROP TABLE IF EXISTS SERVICE;" +
                    "CREATE TABLE SERVICE " +
                    "(SERVICE_ID INTEGER NOT NULL PRIMARY KEY, " +
                    "NAME VARCHAR2(255), " +
                    "DESCRIPTION CLOB, " +
                    "IMAGE_ID INTEGER, " +
                    "DURATION INTEGER)";
            stmt.executeUpdate(serviceCreate);

//        CREATE TABLE IMAGE

            String imageCreate = "DROP TABLE IF EXISTS IMAGE; " +
                    "CREATE TABLE IMAGE " +
                    "(IMAGE_ID INTEGER NOT NULL PRIMARY KEY, " +
                    "FILENAME VARCHAR2(255), " +
                    "ALTNAME VARCHAR2(255), " +
                    "WIGHT INTEGER, " +
                    "HEIGHT INTEGER, " +
                    "BITMAP BLOB)";
            stmt.executeUpdate(imageCreate);

//        CREATE TABLE REPEAT
            String repeatCreate = "DROP TABLE IF EXISTS REPEAT; " +
                    "CREATE TABLE REPEAT " +
                    "(REPEAT_ID INTEGER NOT NULL PRIMARY KEY, " +
                    "SERVICE_ID INTEGER NOT NULL, " +
                    "DOW INTEGER, " +
                    "DURATION INTEGER," +
                    "TIME_FROM TIME, " +
                    "TIME_TO TIME, " +
                    "DATE_FROM DATE, " +
                    "DATE_TO DATE)";
            stmt.executeUpdate(repeatCreate);

//        CREATE TABLE SCHEDULE
            String scheduleCreate = "DROP TABLE IF EXISTS SCHEDULE; " +
                    "CREATE TABLE SCHEDULE " +
                    "(SCHEDULE_ID INTEGER NOT NULL PRIMARY KEY, " +
                    "REPEAT_ID INTEGER NOT NULL, " +
                    "CLIENT_ID INTEGER NOT NULL, " +
                    "DATE_FROM DATE, " +
                    "DURATION INTEGER, " +
                    "TITLE  VARCHAR2(255), " +
                    "HEIGHT INTEGER, " +
                    "DESCRIPTION CLOB, " +
                    "BITMAP BLOB)";
            stmt.executeUpdate(scheduleCreate);

            String clientCreate = "DROP TABLE IF EXISTS CLIENT; " +
                    "CREATE TABLE CLIENT " +
                    "(CLIENT_ID INTEGER NOT NULL PRIMARY KEY, " +
                    "NAME VARCHAR2(255), " +
                    "PHONE VARCHAR2(255), " +
                    "EMAIL VARCHAR2(255)," +
                    "EXTERNAL_REF  VARCHAR2(255))";
            stmt.executeUpdate(clientCreate);

//        CREATE TABLE SETTING
            String settingCreate = "DROP TABLE IF EXISTS SETTING; " +
                    "CREATE TABLE SETTING " +
                    "(SETTING_ID INTEGER NOT NULL PRIMARY KEY, " +
                    "ALIAS VARCHAR2(255), " +
                    "NAME VARCHAR2(1000), " +
                    "DESCRIPTION CLOB, " +
                    "VALUE VARCHAR2(4000), " +
                    "SERVICE_ID INTEGER)";
            stmt.executeUpdate(settingCreate);

//        CREATE TABLE USER
            String userCreate = "DROP TABLE IF EXISTS USER; " +
                    "CREATE TABLE USER " +
                    "(USER_ID INTEGER NOT NULL PRIMARY KEY, " +
                    "NAME VARCHAR2(1000), " +
                    "EMAIL VARCHAR2(255), " +
                    "PASSWORD VARCHAR2(100))";
            stmt.executeUpdate(userCreate);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Service service = new Service(1, "One", null, 1, 10);
    }

}
