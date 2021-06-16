<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<t:base>
    <jsp:attribute name="title">Meals</jsp:attribute>

    <jsp:body>
        <h2 class="page-header my-3">Meals</h2>
        <div class="col-md-6 alert alert-dark mb-5">
            <jsp:include page="WEB-INF/include/_filter_form.jsp"/>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Date / Time</th>
                <th scope="col">Description</th>
                <th scope="col">Calories</th>
                <th scope="col" colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="meals" scope="request" type="java.util.List"/>
            <c:forEach var="meal" items="${meals}">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr class="<c:out value="${meal.excess ? 'text-danger' : 'text-success'}"/>">
                    <td>${fn:formatDateTime(meal.dateTime)}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td>
                        <a href="meals?action=update&id=${meal.id}">
                            <svg class="octicon octicon-pencil" viewBox="0 0 14 16" version="1.1" width="14"
                                 height="16" aria-hidden="true">
                                <path fill-rule="evenodd"
                                      d="M0 12v3h3l8-8-3-3-8 8zm3 2H1v-2h1v1h1v1zm10.3-9.3L12 6 9 3l1.3-1.3a.996.996 0 0 1 1.41 0l1.59 1.59c.39.39.39 1.02 0 1.41z"></path>
                            </svg>
                        </a>
                    </td>
                    <td>
                        <a href="meals?action=delete&id=${meal.id}">
                            <svg class="octicon octicon-trashcan" viewBox="0 0 12 16" version="1.1" width="12" height="16" aria-hidden="true">
                                <path fill-rule="evenodd" d="M11 2H9c0-.55-.45-1-1-1H5c-.55 0-1 .45-1 1H2c-.55 0-1 .45-1 1v1c0 .55.45 1 1 1v9c0 .55.45 1 1 1h7c.55 0 1-.45 1-1V5c.55 0 1-.45 1-1V3c0-.55-.45-1-1-1zm-1 12H3V5h1v8h1V5h1v8h1V5h1v8h1V5h1v9zm1-10H2V3h9v1z"></path>
                            </svg>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="meals?action=create" class="btn btn-outline-primary" role="button">Add meal entry</a>
    </jsp:body>
</t:base>