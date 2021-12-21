<%@ page import="model.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Users" %><%--
  Created by IntelliJ IDEA.
  User: Azat.Kulimov
  Date: 06.12.2021
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserList</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: darkslategrey">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/LoginServlet">BACK</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<%
    List<Users> usersList = (List<Users>) request.getAttribute("user");

%>
<div class="container">
    <div class="row mt-5">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Firstname</th>
                <th scope="col">Username</th>
                <th scope="col">Balance</th>
            </tr>
            </thead>
            <tbody>
            <% for (Users u: usersList
            ) {%>

            <tr>
                <th scope="row"><%= u.getFirstname() %></th>
                <td><% out.print(u.getId()); %></td>
                <td><% out.print(u.getUsername()); %></td>
                <td><%= u.getBalance() %></td>

            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

