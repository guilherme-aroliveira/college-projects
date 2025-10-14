package com.school.performance.controller;

import java.io.IOException;
import java.util.List;

import com.school.performance.dao.StudentDAO;
import com.school.performance.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
  
  public StudentServlet() {
    super();
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // step 1: get the student data from the helper class (model)
    List<Student> students = StudentDAO.getStudent();

    // step 2: add student to request object 
    req.setAttribute("student_list", students);

    // Step 1: get request dispatcher
    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");

    // Step 2: forward teh request to JSP
    dispatcher.forward(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}
