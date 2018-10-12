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
        <div class="col-md-16">
            <form action="${pageContext.request.contextPath}/users/delete" method="post">
                <div class="row">
                    <security:authorize access="hasAuthority('VIEW_USERS')">
                        <div class="col-md-16">
                            <a href="${pageContext.request.contextPath}/users/create" class="btn btn-primary"
                               aria-pressed="true" role="button">ADD</a>
                            <button type="submit" class="btn btn-warning">DELETE</button>
                        </div>
                    </security:authorize>
                </div>
                <div class="row">
                    <div class="col-md-16">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Email</th>
                                <th scope="col">FirstName</th>
                                <th scope="col">LastName</th>
                                <security:authorize access="hasAuthority('VIEW_USERS')">
                                    <th scope="col">Role</th>
                                </security:authorize>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <th scope="row"><input type="checkbox" name="ids" value="${user.id}"></th>
                                    <td>${user.email}</td>
                                    <td>${user.name}</td>
                                    <td>${user.surname}</td>
                                    <security:authorize access="hasAuthority('VIEW_USERS')">
                                        <td>${user.role.getRoleName()}</td>
                                    </security:authorize>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/users/${user.id}"
                                           class="btn btn-primary" aria-pressed="true"
                                           role="button">Update</a>
                                    </td>
                                    <security:authorize access="hasAuthority('VIEW_USERS')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/users/roles/${user.id}"
                                               class="btn btn-primary" aria-pressed="true"
                                               role="button">Update role</a>
                                        </td>
                                    </security:authorize>
                                    <security:authorize access="hasAuthority('VIEW_USERS')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/users/enabled/${user.id}"
                                               class="btn btn-primary" aria-pressed="true"
                                               role="button">Enable status</a>
                                        </td>
                                    </security:authorize>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>