package com.school.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.school.performance.model.Student;

public class StudentDAO {
  
  private DataSource dataSource;

  public StudentDAO(DataSource theDataSource) {
    dataSource = theDataSource;
  }

  public List<Student> getStudents() throws Exception {

    List<Student> students = new ArrayList<>();

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      // get a connection
      conn = dataSource.getConnection();

      // create a sql statement
      String sql = "select * from student order by last_name";
      stmt = conn.createStatement();

      // execute query
      rs = stmt.executeQuery(sql);

      // process result set
      while (rs.next()) {

        // retrieve data from result set row
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");

        // create a new student object
        Student tempStudent = new Student(id, firstName, lastName, email);

        // add it to the list of students
        students.add(tempStudent);
      }

      return students;

    }
    finally {
      // close JDBC objects
      close(conn, stmt, rs);
    }

  }

  private void close(Connection conn, Statement stmt, ResultSet rs) {
    
    try {

      if (rs != null) {
        rs.close();
      }

      if (stmt != null) {
        stmt.close();
      }

      if (conn != null) {
        conn.close(); // puts back the connection pool
      }
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addStudent(Student theStudent) throws Exception {

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      // get the db connection
      conn = dataSource.getConnection();

      // create sql for insert
      String sql = "INSERT INTO student " 
                  + "(first_name, last_name, email) " 
                  + "VALUES (?,?,?)";

      stmt = conn.prepareStatement(sql);

      // set the param values for tthe student
      stmt.setString(1, theStudent.getFirstName());
      stmt.setString(2, theStudent.getLastName());
      stmt.setString(3, theStudent.getEmail());

      // execute sql inserts
      stmt.execute();
    } 
    finally {
      // clean up TDBC objects
      close(conn, stmt, null);
    }

  }
}
