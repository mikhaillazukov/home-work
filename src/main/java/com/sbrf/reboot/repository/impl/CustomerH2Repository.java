package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:~/my_db";

    private final String USER = "sa";
    private final String PASS = "";

    public CustomerH2Repository(){
        String sqlQuery = "CREATE TABLE IF NOT EXISTS CUSTOMERS (" +
                "ID BIGINT NOT NULL AUTO_INCREMENT, " +
                "NAME VARCHAR(255), " +
                "EMAIL VARCHAR(255), " +
                "PRIMARY KEY(ID))";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sqlQuery)){
            Class.forName(JDBC_DRIVER);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Customer> getAll(){
        List<Customer> customers = new ArrayList<>();
        String sqlQuery = "SELECT * FROM CUSTOMERS";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            ResultSet resultSet = stmt.executeQuery()){
            Class.forName(JDBC_DRIVER);

            while (resultSet.next()){
                customers.add(new Customer(resultSet.getLong("ID"),
                                            resultSet.getString("NAME"),
                                            resultSet.getString("EMAIL")));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        String sqlQuery = "INSERT INTO CUSTOMERS (NAME, EMAIL) VALUES(?,?)";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(sqlQuery)){
            Class.forName(JDBC_DRIVER);

            stmt.setString(1, name);
            stmt.setString(2, eMail);

            return stmt.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}


