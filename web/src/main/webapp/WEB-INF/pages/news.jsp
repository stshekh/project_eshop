<%@ page import="com.gmail.sshekh.service.impl.RoleServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>News page</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-16">
        <form action="${pageContext.request.contextPath}/news/delete" method="post">
            <div class="row">
                <div class="col-md-16">
                    <a href="${pageContext.request.contextPath}/news/create" class="btn btn-outline-primary"
                       aria-pressed="true" role="button">ADD</a>
                    <button type="submit" class="btn btn-outline-warning">DELETE</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-16">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Creation time</th>
                            <th scope="col">Who created</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${newsList}" var="news">
                            <tr>
                                <th scope="row"><input type="checkbox" name="ids" value="${news.id}"></th>
                                </th>
                                <td>${news.title}</td>
                                <td>${news.created}</td>
                                <td>${news.user.getName()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>
