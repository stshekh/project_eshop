<%@ page import="com.gmail.sshekh.service.impl.RoleServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        td.clip {
            width: 50em;
        }
    </style>
    <jsp:include page="util/head.jsp"/>
    <title>News page</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>

<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-16">
        <h2 class="h2">${news.title}</h2><br>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">News text</th>
                <th scope="col">Creation time</th>
                <th scope="col">Author</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><input type="checkbox" name="ids" value="${news.id}"></th>
                <td class="clip">${news.content}</td>
                <td>${news.created}</td>
                <td>${news.user.getName()}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<br><br>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-16"><h3 class="h3">Comments</h3>
        <form action="${pageContext.request.contextPath}/news/${news.id}/comments/delete" method="post">
            <div class="row">
                <div class="col-md-16">
                    <security:authorize access="hasAuthority('VIEW_PROFILE')">
                        <a href="${pageContext.request.contextPath}/news/${news.id}/comments/create"
                           class="btn btn-outline-primary"
                           aria-pressed="true" role="button">ADD</a>
                    </security:authorize>
                    <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                        <button type="submit" class="btn btn-outline-warning">DELETE</button>
                    </security:authorize>
                </div>
            </div>
            <div class="row">
                <div class="col-md-16">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Content</th>
                            <th scope="col">Creation time</th>
                            <th scope="col">Who created</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${comments}" var="comment">
                            <tr>
                                <th scope="row"><input type="checkbox" name="ids" value="${comment.id}"></th>
                                </th>
                                <td>${comment.content}</td>
                                <td>${comment.created}</td>
                                <td>${comment.username}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <nav aria-label="...">
                        <ul class="pagination">
                            <%for (int i = 1; i <= (int) request.getAttribute("pages"); i++) {%>
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/news/show/${news.id}?page=<%=i %>"><%=i %>
                                </a>
                            </li>
                            <%} %>
                        </ul>
                    </nav>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>
