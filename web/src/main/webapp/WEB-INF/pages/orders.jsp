<%@ page import="com.gmail.sshekh.service.impl.RoleServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Orders</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-16">
        <form action="${pageContext.request.contextPath}/orders/delete" method="post">
            <div class="row">
                <div class="col-md-16">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Created</th>
                            <th scope="col">Item name</th>
                            <th scope="col">Username</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>

                                <td>${order.created}</td>
                                <td>${order.item.getName()}</td>
                                <td>${order.user.getName()}</td>
                                <td>${order.quantity}</td>
                                <td>${order.status}</td>
                                <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/orders/${order.user.getId()}/${order.item.getId()}/status"
                                           class="btn btn-primary" aria-pressed="true"
                                           role="button">Change status</a>
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
                                   href="${pageContext.request.contextPath}/orders?page=<%=i %>"><%=i %>
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
