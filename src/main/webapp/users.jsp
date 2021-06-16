<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:attribute name="title">Users</jsp:attribute>

    <jsp:body>
        <h2 class="page-header my-3">Users</h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#Id</th>
                <th scope="col">Name</th>
                <th scope="col">E-mail</th>
                <th scope="col">Password</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <jsp:useBean id="users" type="java.util.List" scope="request" />
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="page" />
                <c:url value="meals" var="mealsUrl"><c:param name="userId" value="${user.id}"/></c:url>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td><a href="${mealsUrl}">meals</a></td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:base>