<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Users page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>

    <div class="row">
        <div class="col-md-12">
            <img class="logo" src="${pageContext.request.contextPath}/resources/img/items.png" alt="Logo image">
        </div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>