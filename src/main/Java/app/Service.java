package app;

import org.h2.jdbc.JdbcSQLException;
import org.h2.message.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service implements WorkDB{
    Integer id_Service;
    String name;
    Clob description;
    Integer image_id;
    Integer duration;

    public Service(){

    }

    public Service (Integer id_Service, String name, Clob description, Integer image_id, Integer duration){
        this.id_Service = id_Service;
        this.name = name;
        this.description = description;
        this.image_id = image_id;
        this.duration = duration;
    }



    @Override
    public WorkDB getLineDB(Integer id) {

        Service service = null;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "")) {
            String sql = "SELECT * FROM SERVICE WHERE SERVICE_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id_service = resultSet.getInt("SERVICE_ID");
                String name = resultSet.getString("NAME");
                Clob description = resultSet.getClob("DESCRIPTION");
                Integer image_id = resultSet.getInt("IMAGE_ID");
                Integer duration = resultSet.getInt("DURATION");
                service = new Service(id_service, name, description, image_id, duration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public void updateLineDB() {

        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "")) {
            String sql = "UPDATE SERVICE SET NAME=?, DESCRIPTION=?, IMAGE_ID=?, DURATION=? WHERE SERVICE_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(5, this.id_Service);
            preparedStatement.setString(1, this.name);
            preparedStatement.setClob(2, this.description);
            preparedStatement.setInt(3, this.image_id);
            preparedStatement.setInt(4, this.duration);
            System.out.println(preparedStatement.toString());
            try{
            preparedStatement.executeUpdate();
            }catch (JdbcSQLException e){
                System.out.println("Запись не найдена");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertLineDB() {
        try (Connection h2 = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "")) {
            String sql = "INSERT INTO SERVICE (SERVICE_ID, NAME, DESCRIPTION, IMAGE_ID, DURATION) Values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = h2.prepareStatement(sql);
            preparedStatement.setInt(1, this.id_Service);
            preparedStatement.setString(2, this.name);
            preparedStatement.setClob(3, this.description);
            preparedStatement.setInt(4, this.image_id);
            preparedStatement.setInt(5, this.duration);
            try {
                preparedStatement.executeUpdate();
            }catch (Exception e){
                System.out.println("Запись не уникальна");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLineDB(Integer service_id) {
        String sql = "DELETE FROM SERVICE WHERE SERVICE_ID=?";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, service_id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Service> getDB() {
        List<Service> service = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "")) {
            String sql = "SELECT * FROM SERVICE";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id_service = resultSet.getInt("SERVICE_ID");
                String name = resultSet.getString("NAME");
                Clob description = resultSet.getClob("DESCRIPTION");
                Integer image_id = resultSet.getInt("IMAGE_ID");
                Integer duration = resultSet.getInt("DURATION");
                service.add(new Service(id_service, name, description, image_id, duration));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    public static void main(String[] args) {

        Service service = new Service(1, "One", null, 2, 2);
        service.insertLineDB();
        Service service1 = (Service) service.getLineDB(2);
        service1.name = "Two";
        service1.updateLineDB();
        for (Service service2 : service.getDB()){
            System.out.println(service2.name);
            System.out.println(service2.duration);
            System.out.println(service2.id_Service);
        }
        System.out.println();
//        service.deleteLineDB(1);
//        service.deleteLineDB(1);
//        service.updateLineDB();

    }
}
