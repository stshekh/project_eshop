<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Login page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <form:errors path="*" cssClass="error"/>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" name="email" value="${email}" class="form-control" id="email"
                           placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" value="${password}" class="form-control"
                           id="password"
                           placeholder="Input password">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
                <a href="${pageContext.request.contextPath}/register"
                   class="btn btn-primary" aria-pressed="true"
                   role="button">Register</a>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>