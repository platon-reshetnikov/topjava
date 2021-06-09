<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<t:base>
    <jsp:attribute name="title">Meals</jsp:attribute>
    <jsp:body>
        <h2 class="page-header my-3">Meals</h2>

        <table class="table" border="1">
        <thead>
        <tr>
            <th scope="col">Дата / Время</th>
            <th scope="col">Описание</th>
            <th scope="col">Калории</th>
            <th scope="col" colspan="2">Действия</th>
        </tr>
        </thead>
            <tbody>
                <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
                <c:forEach var="meal" items="${mealList}">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
                    <tr class="<c:out value="${meal.excess?'text-danger':'text-success'}"/>">
                        <td>${fn:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy HH:mm')}</td>
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
                                <svg class="octicon octicon-x" viewBox="0 0 12 16" version="1.1" width="12" height="16"
                                     aria-hidden="true">
                                    <path fill-rule="evenodd"
                                          d="M7.48 8l3.75 3.75-1.48 1.48L6 9.48l-3.75 3.75-1.48-1.48L4.52 8 .77 4.25l1.48-1.48L6 6.52l3.75-3.75 1.48 1.48z"></path>
                                </svg>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="meals?action=create" class="btn btn-outline-primary" role="button">Добавить запись</a>
    </jsp:body>
</t:base>