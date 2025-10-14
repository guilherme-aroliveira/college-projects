package com.school.performance.dao;

import java.util.ArrayList;
import java.util.List;

import com.school.performance.model.Student;

public class StudentDAO {
  
  public static List<Student> getStudent() {

    // create an empty list
    List<Student> students = new ArrayList<>();

    // add sample data
    students.add(new Student("Mary", "Public", "mary@luv2code.com"));
    students.add(new Student("John", "Doe", "john@luv2code.com"));
    students.add(new Student("Ajay", "Rao", "ajay@luv2code.com"));

    // return the list
    return students;
  }
}
