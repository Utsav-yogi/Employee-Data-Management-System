<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.DataStorage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>profile</title>
<link rel="stylesheet" href="profile.css">
</head>
<body>

<div id="navbar_container">
<a href="profile.jsp" id="a1">Home</a><br>
<a href="view.jsp" id="a2">View</a><br>
<a href="reset_password.html" id="a3">Reset Password</a><br>
<a href="logout" id="a4">Logout</a><br>
</div>

<%
    DataStorage DS = (DataStorage)session.getAttribute("session_DS");
%>

<div id="welcome_container">
<h1>Welcome <%=DS.getFirstName()%></h1>
</div>

</body>
</html>