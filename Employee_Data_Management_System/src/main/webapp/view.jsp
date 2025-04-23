<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.DataStorage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view</title>
<link rel="stylesheet" href="view.css">
</head>
<body>
<%
   DataStorage DS = (DataStorage)session.getAttribute("session_DS");
%>

<div>
   <a href="profile.jsp" id="a1">Home</a>
   <a href="view.jsp" id="a2">View</a>
   <a href="reset_password.html" id="a3">Reset Password</a>
   <a href="index.html" id="a4">Logout</a>
</div>

<h1>Employee details</h1>

<table>
 
    <tr>
      <th>FirstName</th>
      <th>LastName</th>
      <th>Email</th>
      <th>Address</th>
      <th>City</th>
      <th>State</th>
      <th>Gender</th>
    </tr>

  <tr>
    <td><%=DS.getFirstName()%></td>
    <td><%=DS.getLastName()%></td>
    <td><%=DS.getEmail()%></td>
    <td><%=DS.getAddress()%></td>
    <td><%=DS.getCity()%></td>
    <td><%=DS.getState() %></td>
    <td><%=DS.getGender() %></td>
  </tr>

</table>
</body>
</html>