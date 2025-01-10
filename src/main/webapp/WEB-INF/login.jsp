<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <legend>Login</legend>
    <div class="form-group">
        <div class="col-md-4">
            <form class="form-horizontal" action="login" method="GET">
                <label class="col-md-4 control-label" for="loginId">User name</label>
                <input class="form-control input-md" type="text" id="loginId" name="login" required>
                <label class="col-md-4 control-label" for="passwordId">Password</label>
                <input class="form-control input-md" type="text" id="passwordId" name="password" required>
                <button type="submit" class="btn btn-success" id="btLogin" name="btLogin" style="margin-top: 10px;">Login</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="footer.jsp" %>
