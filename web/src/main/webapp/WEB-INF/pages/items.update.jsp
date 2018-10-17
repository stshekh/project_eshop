<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Items update</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <form:errors path="*" cssClass="error"/>
            <form:form action="${pageContext.request.contextPath}/items/create" modelAttribute="item" method="post">
                <div class="form-group">
                    <form:label path="name">Name</form:label>
                    <form:input path="name" class="form-control" placeholder="Item name"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" class="form-control" placeholder="Description"/>
                </div>
                <div class="form-group">
                    <form:label path="price">Price</form:label>
                    <form:input path="price" class="form-control" placeholder="Price"/>
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>
