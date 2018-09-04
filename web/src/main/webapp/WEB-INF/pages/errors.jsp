<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Errors page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h1>Something goes wrong... Please check console for details <span class="badge badge-danger">Error</span></h1>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>