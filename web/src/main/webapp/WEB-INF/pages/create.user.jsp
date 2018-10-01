<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Create user</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <form:errors path="*" cssClass="error"/>
            <form:form action="${pageContext.request.contextPath}/users" modelAttribute="user" method="post">
                <div class="form-group">
                    <form:label path="name">First name</form:label>
                    <form:input path="name" class="form-control" placeholder="First name"/>
                </div>
                <div class="form-group">
                    <form:label path="surname">Last name</form:label>
                    <form:input path="surname" class="form-control" placeholder="Last name"/>
                </div>
                <div class="form-group">
                    <form:label path="email">Email</form:label>
                    <form:input path="email" class="form-control" placeholder="Email"/>
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input path="password" class="form-control" placeholder="Password"/>
                </div>

                <button type="submit" class="btn btn-primary">Save</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>
