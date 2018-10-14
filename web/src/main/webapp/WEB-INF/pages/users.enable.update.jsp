<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Update role</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <form:errors path="*" cssClass="error"/>
            <form:form action="${pageContext.request.contextPath}/users/enabled/${user.id}" modelAttribute="user"
                       method="post">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">FirstName</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${user.name}</td>
                        <td><select id="enabled" name="enabled">
                            <option value="true" ${user.enabled==true? 'selected':''}>Enabled</option>
                            <option value="false" ${user.enabled==false? 'selected':''}>Disabled</option>
                        </select>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary">Update status</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <a href="${pageContext.request.contextPath}/users">Back to users list</a>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>
