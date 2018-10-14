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
            <form:form action="${pageContext.request.contextPath}/users/profile/update" modelAttribute="profile"
                       method="post">
                <div class="form-group">
                    <label>Name</label>
                    <input id="name" class="form-control input-md" name="name"
                           value="${user.name}"/>
                </div>
                <div class="form-group">
                    <label>Surname</label>
                    <input id="surname" class="form-control input-md" name="surname"
                           value="${user.surname}"/>
                </div>
                <div class="form-group">
                    <form:label path="address">Address</form:label>
                    <form:input path="address" class="form-control" placeholder="Address"/>
                </div>
                <div class="form-group">
                    <form:label path="telephone">Telephone</form:label>
                    <form:input path="telephone" class="form-control" placeholder="Telephone"/>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input id="password" class="form-control input-md" type="password" name="password"
                           value="${user.password}"/>
                </div>
                <div>
                    <a href=${pageContext.request.contextPath}/users/${user.id}/businessCard" />">Business cards</a>
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>