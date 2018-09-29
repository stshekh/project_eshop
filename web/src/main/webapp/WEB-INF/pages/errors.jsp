<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0l Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="util/head.jsp"/>
    <title>Errors page</title>
</head>
<body>
<div class="container">
    <h2>App Error, please contact support!</h2>
    <h3>Debug Information:</h3>

    Requested URL - ${url}<br><<br>

    Exception = ${exception.message}<<br><br>

    <strong>Exception Stack Trace</strong><<br>
    <c:forEach items="${exception.stackTrace}" var="stc">
        ${stc}
    </c:forEach>

</div>
<jsp:include page="util/js.jsp"/>
</body>
</html>