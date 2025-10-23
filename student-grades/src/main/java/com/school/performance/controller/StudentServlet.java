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
public class StudentServlet extends HttpServlet {

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

    // create the student DB DAO ... and pass in the conn pool / datasource
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
          listStudent(req, resp);
          break;

        case "LOAD":
          loadStudent(req, resp);
          break;

        case "UPDATE":
          updateStudent(req, resp);
          break;

        case "DELETE":
          deleteStudent(req, resp);
          break;

        default:
          listStudent(req, resp);
      }

       // list the students ... in MVC fashion
      listStudent(req, resp);
    } 
    catch (Exception e) {
      throw new ServletException(e);
    }
  }

  private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    
    // read student id from form data
    String theStudentId = req.getParameter("studentId");

    // delete student from database
    studentDAO.deleteStudent(theStudentId);

    // send them back to the "list students" page
    listStudent(req, resp);
  }

  private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    
    // read student info from form data
    int id = Integer.parseInt(req.getParameter("studentId"));
    String firstName = req.getParameter("firstName");
    String lastName = req.getParameter("lastName");
    String email = req.getParameter("email");

    // create a new student object
    Student theStudent = new Student(id, firstName, lastName, email);

    // perform update on database
    studentDAO.updateStudent(theStudent);

    // send them back to the "list students" page
    listStudent(req, resp);
  }

  private void loadStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    // read student id from form data
    String theStudentId = req.getParameter("studentId");

    // get student from database (db util)
    Student theStudent = studentDAO.getStudent(theStudentId);

    // place student in the request attribute
    req.setAttribute("THE_STUDENT", theStudent);

    // send to jsp page (update-student form)
    RequestDispatcher dispatcher = req.getRequestDispatcher("/update-student-form.jsp");
    dispatcher.forward(req, resp);
  }

  private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    
    // read student info from form data
    String firstName = req.getParameter("firstName");
    String lastName = req.getParameter("lastName");
    String email = req.getParameter("email");

    // create a new student object
    Student theStudent = new Student(firstName, lastName, email);

    // add the student to the database
    studentDAO.addStudent(theStudent);

    // send back to main page (the student list)
    // SEND AS REDIRECT to avoid multiple-browser reload issue
    resp.sendRedirect(req.getContextPath() + "/StudentServlet?command=LIST");
  }

  private void listStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    
    // get students from db dao
    List<Student> students = studentDAO.getStudent();

    // add student to the request
    req.setAttribute("STUDENT_LIST", students);

    // send to JSP page (view)
    RequestDispatcher dispatcher = req.getRequestDispatcher("list-students.jsp");
    dispatcher.forward(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    try {
      // read the "command" parameter
      String theCommand = req.getParameter("command");

      // route to the appropriate method
      switch (theCommand) {
        
        case "ADD":
          addStudent(req, resp);
          break;

        default:
          listStudent(req, resp);
      }
    } 
    catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
