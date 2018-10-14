<%@ page import="com.gmail.sshekh.service.impl.RoleServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Users page</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="container">
    <jsp:include page="util/logo.jsp"/>

    <div class="row">
        <div class="col-md-2"></div>
        <a href="${pageContext.request.contextPath}/users/businessCard/create" class="btn btn-primary"
           aria-pressed="true" role="button">ADD</a>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Full name</th>
                <th scope="col">Telephone</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${businessCards}" var="businessCard">
                <tr>
                    <th scope="row"><input type="checkbox" name="ids" value="${businessCard.id}"></th>
                    </th>
                    <td>${businessCard.title}</td>
                    <td>${businessCard.fullName}</td>
                    <td>${businessCard.workingTelephone}</td>
                    <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                        <td>
                            <a href="${pageContext.request.contextPath}/news/${news.id}"
                               class="btn btn-primary" aria-pressed="true"
                               role="button">Update</a>
                        </td>
                    </security:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
