<%@ page import="com.gmail.sshekh.service.impl.RoleServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script>
        var numCount = document.getElementById('num_count');
        var plusBtn = document.getElementById('button_plus');
        var minusBtn = document.getElementById('button_minus');
        plusBtn.onclick = function () {
            var qty = parseInt(numCount.value);
            qty = qty + 1;
            numCount.value = qty;
        }
        minusBtn.onclick = function () {
            var qty = parseInt(numCount.value);
            qty = qty - 1;
            numCount.value = qty;
        }
    </script>
    <jsp:include page="util/head.jsp"/>
    <title>Items</title>
</head>
<body>
<%@ include file="util/menu.jsp" %>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-16">
        <form action="${pageContext.request.contextPath}/items/delete" method="post">
            <div class="row">
                <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                    <div class="col-md-16">
                        <a href="${pageContext.request.contextPath}/items/create" class="btn btn-outline-primary"
                           aria-pressed="true" role="button">ADD</a>
                        <a href="${pageContext.request.contextPath}/items/upload" class="btn btn-primary"
                           aria-pressed="true" role="button">Upload with XML</a>
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
                            <th scope="col">Item name</th>
                            <th scope="col">Item description</th>
                            <th scope="col">Unique number</th>
                            <th scope="col">Price, $</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <th scope="row"><input type="checkbox" name="ids" value="${item.id}"></th>
                                <td>${item.name}</td>
                                <td>${item.description}</td>
                                <td>${item.uniqueNumber}</td>
                                <td>${item.price}$</td>
                                <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/items/${item.id}"
                                           class="btn btn-primary" aria-pressed="true"
                                           role="button">Update</a>
                                    </td>
                                </security:authorize>
                                <security:authorize access="hasAuthority('VIEW_PROFILE')">
                                    <form action="${pageContext.request.contextPath}/items/${item.id}/orders/create"
                                          method="post">
                                        <td>
                                            <div class="input-group quantity_goods">
                                                <input type="number" step="1" min="1" id="num_count"
                                                       name="quantity" value="1" title="Qty">
                                                <input type="button" value="-" id="button_minus">
                                                <input type="button" value="+" id="button_plus">
                                            </div>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-outline-success">Add to cart</button>
                                        </td>
                                    </form>
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
                                   href="${pageContext.request.contextPath}/items?page=<%=i %>"><%=i %>
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
