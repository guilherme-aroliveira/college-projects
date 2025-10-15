<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html> 
  <head>
    <title>Student Table</title>
  </head>

  <body>
    <h2>Student Table Demo</h2>
    </br>

    <table border="1"> 

      <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email Name</th>
      </tr>

      <c:forEach var="tempStudent" items="${student_list}">
        <tr>
          <td>${tempStudent.firstName}</td>
          <td>${tempStudent.lastName}</td>
          <td>${tempStudent.email}</td>
        </tr>
      </c:forEach>

    </table>
  </body>
</html>