package com.andersen.dao;

import com.andersen.People;
import com.andersen.connectorDB.ConnectorDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public static void createSchema(){
        try {
            String SQL = "CREATE SCHEMA jdbcCRUD";
            Statement statement = ConnectorDB.getConnection().createStatement();
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try {
            Statement statement = ConnectorDB.getConnection().createStatement();
            String SQL = "CREATE TABLE jdbccrud.peopleList (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(20) NOT NULL, surName VARCHAR(20) NOT NULL," +
                    "age INT NOT NULL, phone VARCHAR(20), PRIMARY KEY (id))";
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void savePeople(People people) {
        try {
            String SQL = "INSERT  INTO jdbccrud.peoplelist (id, name, surName, age, phone) VALUES " +
                    "(NULL , ?, ?, ?, ?)";
            PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, people.getName());
            preparedStatement.setString(2, people.getSurName());
            preparedStatement.setInt(3, people.getAge());
            preparedStatement.setString(4, people.getPhone());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findPeopleByAnyInformation(String name) {
        String SQL = "SELECT * FROM jdbccrud.peoplelist WHERE jdbccrud.peoplelist.name LIKE ?";
        try {
            PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("There are next peoples in BD: ");
            while (resultSet.next()) {
                People people = new People();
                people.setId(resultSet.getInt("id"));
                people.setName(resultSet.getString("name"));
                people.setSurName(resultSet.getString("surName"));
                people.setAge(resultSet.getInt("age"));
                people.setPhone(resultSet.getString("phone"));
                System.out.println(people.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePeopleByName(String name) {
        String SQL = "DELETE FROM jdbccrud.peoplelist WHERE jdbccrud.peoplelist.name LIKE ?";
        try {
            PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, name+"%");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePeopleInformation(String name, String newPhone) {
        String SQL = "UPDATE jdbccrud.peoplelist SET phone = ? WHERE jdbccrud.peoplelist.name LIKE ?";
        try {
            PreparedStatement preparedStatement = ConnectorDB.getConnection().prepareStatement(SQL);
            preparedStatement.setString(1, newPhone);
            preparedStatement.setString(2, name+"%");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
