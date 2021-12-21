<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%--<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: darkslategrey">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">CarShop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/AddPhoneServlet">Cars</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/AddPhoneServlet">model.Users</a>
                </li>
            </ul>
        </div>
    </div>
</nav>--%>
<%
    if(request.getParameter("error")!=null) {
%>
        <div class="alert alert-danger" role = "alert" >
            Invalid value of login or password !
        </div >
<%
    }
%>

<div class="container">
    <div class="row mt-5">
        <div class="col-sm-6 offset-3">
                <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <div class="form-group">
                    <label>Username : </label>
                    <input type="text" name="username" class="form-control">
                </div>
                <div class="form-group">
                    <label>Password : </label>
                    <input type="password" name="password" class="form-control">
                </div>
                <br>
                <div class="form-group">
                    <button class="btn btn-success">Sign in</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
