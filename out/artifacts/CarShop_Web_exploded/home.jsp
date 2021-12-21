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
    <title>Carshop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%  Users current_user = (Users) request.getSession().getAttribute("CURRENT_USER");
    if (current_user != null){
%>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: darkslategrey">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <% if (current_user.isAdmin()){%>
                <li class="nav-item">
                    <a class="nav-link" href="/UserListServlet">Users</a>
                </li>
                <% } %>
                <li class="nav-item">
                    <a class="nav-link" href="/LoginServlet">BACK</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<%
    List<Car> carArrayList = (List<Car>) request.getAttribute("cars");
%>
<div class="container">
    <div class="row mt-5">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Model</th>
                <th scope="col">Mark</th>
                <th scope="col">Price</th>
                <%if(current_user.isAdmin()){

                %>
                <th scope="col">Edit</th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <% for (Car c: carArrayList
            ) {%>

            <tr>
                <th scope="row"><%= c.getId() %></th>
                <td><% out.print(c.getMark()); %></td>
                <td><%= c.getModel() %></td>
                <td><%= c.getPrice() %></td>
                <%if(current_user.isAdmin()) {%>
                <td><a href="/EditServlet?id=<%=c.getId() %>" class="btn btn-info btn-sm">Editor</a> </td>
                <%}%>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<%} else{%>
<h1>ERROR-404</h1>
<%}%>

</body>
</html>

