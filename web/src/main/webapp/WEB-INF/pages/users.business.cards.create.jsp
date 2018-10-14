<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Create users business card</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <form:errors path="*" cssClass="error"/>
            <form:form action="${pageContext.request.contextPath}/users/businessCard/create" modelAttribute="businessCard" method="post">
                <div class="form-group">
                    <form:label path="title">Title</form:label>
                    <form:input path="title" class="form-control" placeholder="Title"/>
                </div>
                <div class="form-group">
                    <form:label path="fullName">Full name</form:label>
                    <form:input path="fullName" class="form-control" placeholder="Full name"/>
                </div>
                <div class="form-group">
                    <form:label path="workingTelephone">Working telephone</form:label>
                    <form:input path="workingTelephone" class="form-control" placeholder="Working telephone"/>
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>
