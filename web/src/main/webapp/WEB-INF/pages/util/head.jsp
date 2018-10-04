<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head></head>
<body>
<div class="col-md-2">
    <security:authorize access="isAuthenticated()">
        authenticated as <security:authentication property="principal.username"/>
        <a href="<c:url value="/logout" />">Logout</a>
    </security:authorize>
</div>

</body>
</html>