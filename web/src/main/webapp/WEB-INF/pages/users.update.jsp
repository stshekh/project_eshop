<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Update user</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <form:errors path="*" cssClass="error"/>
            <form:form action="${pageContext.request.contextPath}/users/${user.id}" modelAttribute="user" method="post">
                <div class="form-group">
                    <form:label path="name">First name</form:label>
                    <form:input path="name" class="form-control" placeholder="First name"/>
                </div>
                <div class="form-group">
                    <form:label path="surname">Last name</form:label>
                    <form:input path="surname" class="form-control" placeholder="Last name"/>
                </div>
                <security:authorize access="hasAuthority('VIEW_USERS')">
                    <div class="form-group">
                        <form:label path="email">Email</form:label>
                        <form:input path="email" class="form-control" placeholder="Email"/>
                    </div>
                </security:authorize>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input path="password" class="form-control" type="password" placeholder="Password"/>
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>
