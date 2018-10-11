<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <security:authorize access="hasAuthority('VIEW_USERS')">
                <li class="nav-item">
                    <a class="nav-link" href=${pageContext.request.contextPath}/users>Users</a>
                </li>
            </security:authorize>
            <security:authorize access="hasAuthority('MANAGE_ITEMS')">
                <li class="nav-item">
                    <a class="nav-link disabled" href=${pageContext.request.contextPath}/news>News</a>
                </li>
            </security:authorize>
        </ul>
        <security:authorize access="isAuthenticated()">
            <p class="navbar-text navbar-right">Signed in as <a
                    href="${pageContext.request.contextPath}/users/profile"
                    class="navbar-link"><security:authentication
                    property="principal.username"/></a></br>
                <a href="<c:url value="/logout" />">Logout</a>
            </p>
        </security:authorize>

    </div>
</nav>
