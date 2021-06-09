<%--
  Created by IntelliJ IDEA.
  User: Макс
  Date: 09.06.2021
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<t:base>
    <jsp:attribute name="title">Meals Form</jsp:attribute>

    <jsp:body>
        <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
        <h2 class="page-header my-3">Meals Form</h2>
        <hr/>
        <form method="POST" action="meals" accept-charset="UTF-8">
            <input type="hidden" name="id" value="${meal.id}">
            <div class="form-group row">
                <label for="dateTime" class="col-sm-2 col-form-label">Дата/Время</label>
                <div class="col-sm-5">
                    <input name="dateTime" type="datetime-local" class="form-control" id="dateTime" value="${meal.dateTime}">
                </div>
            </div>
            <div class="form-group row">
                <label for="description" class="col-sm-2 col-form-label">Описание</label>
                <div class="col-sm-5">
                    <input name="description" type="text" class="form-control" id="description" placeholder="Описание" value="${meal.description}">
                </div>
            </div>
            <div class="form-group row">
                <label for="calories" class="col-sm-2 col-form-label">Калории</label>
                <div class="col-sm-5">
                    <input name="calories" type="text" class="form-control" id="calories" placeholder="Калории" value="${meal.calories}">
                </div>
            </div>
            <div class="d-flex flex-row-reverse col-sm-7">
                <a href="meals" class="btn btn-secondary">Отмена</a>
                <button type="submit" class="btn btn-primary mr-2">Сохранить</button>
            </div>
        </form>
    </jsp:body>
</t:base>