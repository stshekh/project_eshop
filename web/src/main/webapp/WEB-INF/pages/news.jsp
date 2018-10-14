<%@ page import="com.gmail.sshekh.service.impl.RoleServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        td.item-node {
            max-width: 30em;
        }

        div.ellipsis {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
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
        <form action="${pageContext.request.contextPath}/news/delete" method="post">
            <div class="row">
                <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                    <div class="col-md-16">
                        <a href="${pageContext.request.contextPath}/news/create" class="btn btn-outline-primary"
                           aria-pressed="true" role="button">ADD</a>
                        <button type="submit" class="btn btn-outline-warning">DELETE</button>
                    </div>
                </security:authorize>
            </div>
            <div class="row">
                <div class="col-md-16">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">News short text</th>
                            <th scope="col">Creation time</th>
                            <th scope="col">Who created</th>
                            <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                                <th scope="col">Actions</th>
                            </security:authorize>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${newsList}" var="news">
                            <tr>
                                <th scope="row"><input type="checkbox" name="ids" value="${news.id}"></th>
                                </th>
                                <td>${news.title}</td>
                                <td class="item-node">
                                    <div class="ellipsis">${news.content}</div>
                                    <a href="${pageContext.request.contextPath}/news/show/${news.id}">More</a>
                                </td>
                                <td>${news.created}</td>
                                <td>${news.user.getName()}</td>
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
                    <nav aria-label="...">
                        <ul class="pagination">
                            <%for (int i = 1; i <= (int) request.getAttribute("pages"); i++) {%>
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/news?page=<%=i %>"><%=i %>
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
