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
       // list the students ... in MVC fashion
      listStudents(req, resp);
    } 
    catch (Exception e) {
      throw new ServletException(e);
    }
  }

  private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    
    // get students from db dao
    List<Student> students = studentDAO.getStudents();

    // add student to the request
    req.setAttribute("STUDENT_LIST", students);

    // send to JSP page (view)
    RequestDispatcher dispatcher = req.getRequestDispatcher("/list-students.jsp");
    dispatcher.forward(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
