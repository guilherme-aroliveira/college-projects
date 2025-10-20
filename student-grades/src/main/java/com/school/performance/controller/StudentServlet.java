package com.school.performance.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.school.performance.dao.StudentDAO;
import com.school.performance.model.Student;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;

  private StudentDAO studentDAO;

  @Resource(name="jdbc/MariaDB")
  private DataSource dataSource;

  /* 
    called by the Jakarta EE Servlet by Tomcat 
    when this servlet is first loaded or initialized. 
  */ 
  @Override
  public void init() throws ServletException { 
    super.init();

    // create the studen DB DAO ... and pass in the conn pool / datasource
    try {
      studentDAO = new StudentDAO(dataSource);
    }
    catch (Exception e) {
      throw new ServletException(e);
    }
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    try {
      // read the "command" parameter
      String theCommand = req.getParameter("command");

      // if the command is missing, then default to listing students
      if (theCommand == null) {
        theCommand = "LIST";
      }

      // route to the apppropriate method
      switch (theCommand) {

        case "LIST": 
          listStudents(req, resp);
          break;

        case "ADD": 
          addStudents(req, resp);
          break;

        default:
          listStudents(req, resp);
      }

       // list the students ... in MVC fashion
      listStudents(req, resp);
    } 
    catch (Exception e) {
      throw new ServletException(e);
    }
  }

  private void addStudents(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    
    // read student info from data
    String firstName = req.getParameter("firstName");
    String lastName = req.getParameter("lastName");
    String email = req.getParameter("email");

    // create a new student object
    Student theStudent = new Student(firstName, lastName, email);

    // add the student to the database
    studentDAO.addStudent(theStudent);

    // send back to main page (the student list)
    listStudents(req, resp);
  }

  private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    
    // get students from db dao
    List<Student> students = studentDAO.getStudents();

    // add student to the request
    req.setAttribute("STUDENT_LIST", students);

    // send to JSP page (view)
    RequestDispatcher dispatcher = req.getRequestDispatcher("list-students.jsp");
    dispatcher.forward(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
