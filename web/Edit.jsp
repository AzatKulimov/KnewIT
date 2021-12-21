<%@ page import="model.Users" %>
<%@ page import="model.Car" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<%  Users user = (Users) request.getAttribute("user");
    if (user != null){
%>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: darkslategrey">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <% if (user.isAdmin()){%>
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
    }
    Car c = (Car) request.getAttribute("car");
    if (c!=null){
%>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-6 offset-3">
            <form action="${pageContext.request.contextPath}/EditServlet" method="post">
                <div class="form-group">
                    <label>Mark : </label>
                    <input type="text" name="name" class="form-control" value="<%=c.getMark()%>">
                </div>
                <div class="form-group">
                    <label>Price : </label>
                    <input type="number" name="price" class="form-control" value="<%=c.getModel()%>">
                </div>
                <div class="form-group">
                    <label>Amount : </label>
                    <input type="number" name="amount" class="form-control" value="<%=c.getPrice()%>">
                </div>
                <br>
                <div class="form-group"  style="display: inline-flex" >
                    <button class="btn btn-success">Edit</button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#remove">
                        Remove
                    </button>

                </div>
            </form>

        </div>


    </div>
</div>
<% } %>
</body>
</html>