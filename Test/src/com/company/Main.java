package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.company.Person;

public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<Person> personlist = new ArrayList<Person>();
        //List<Person> personlist = new List<Person>();
        try {
            // Step 1: Load the JDBC driver. jdbc:mysql://localhost:3306/travel
            Class.forName("com.mysql.jdbc.Driver");

            // Step 2: Establish the connection to the database.
            String url = "jdbc:mysql://localhost:3306/travel";
            Connection conn = DriverManager.getConnection(url, "root", "admin");
            Statement st = conn.createStatement();
            ResultSet srs = st.executeQuery("SELECT * FROM person");
            while (srs.next()) {
                Person person = new Person();
                person.setName(srs.getString("name"));
                person.setJobtitle(srs.getString("jobtitle"));
                person.setFrequentflyer(srs.getInt("frequentflyer"));
                personlist.add(person);
            }

            System.out.println(personlist.size());
            System.out.println(personlist.get(1).getName());
            System.out.println(personlist.get(2).getName());
            System.out.println(personlist.get(3).getName());
            System.out.println(personlist.get(4));

            //System.out.println(namelist.);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}