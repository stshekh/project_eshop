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
            <form:form action="${pageContext.request.contextPath}/orders/${order.user.getId()}/${order.item.getId()}/status" modelAttribute="order"
                       method="post">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Item name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${order.user.getName()}</td>
                        <td><select id="status" name="status">
                            <option value="NEW" ${order.status.equals("NEW")? 'selected':''}>NEW</option>
                            <option value="REVIEWING" ${order.status.equals("REVIEWING")? 'selected':''}>REVIEWING</option>
                            <option value="IN_PROGRESS" ${order.status.equals("IN_PROGRESS")? 'selected':''}>IN_PROGRESS</option>
                            <option value="DELIVERED" ${order.status.equals("DELIVERED")? 'selected':''}>DELIVERED</option>
                        </select>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary">Update status</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <a href="${pageContext.request.contextPath}/orders">Back to orders list</a>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>