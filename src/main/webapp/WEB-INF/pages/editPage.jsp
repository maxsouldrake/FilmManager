<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/style2.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
    <title>Film Manager</title>
</head>
<body>
<div align="center" class="inf-back">
    <div class="inf-title-back">
        <div class="inf-title">
            <input type="text" name="title" placeholder="title" value="${film.title}" maxlength="128" required autofocus
                   pattern="^[^\s]+(\s.*)?$" form="add-form">
        </div>
    </div>
    <div class="inf-poster">
        <img width="333" height="500" src="<c:url value="/res/posters/${film.poster}"/>" alt="poster">
    </div>
    <div class="inf-info">
        <table>
            <tr>
                <th colspan="2">Информация:</th>
                <td rowspan="6" width="10%"></td>
                <th>В ролях:</th>
            </tr>
            <tr>
                <td class="cell">Год:</td>
                <td class="cell">
                    <input type="number" name="year" placeholder="year" value="${film.year}" maxlength="4" required
                           oninput="if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           form="add-form">
                </td>
                <td class="cell">${film.actors.split(", ")[0]}</td>
            </tr>
            <tr>
                <td class="cell">Жанр:</td>
                <td class="cell" align="center">
                    <input type="text" name="genre" placeholder="genre" value="${film.genre}" maxlength="16" required
                           form="add-form">
                </td>
                <td class="cell">${film.actors.split(", ")[1]}</td>
            </tr>
            <tr>
                <td class="cell">Страна:</td>
                <td class="cell" align="center">
                    <input type="text" name="country" placeholder="country" value="${film.country}" maxlength="16" required
                           form="add-form">
                </td>
                <td class="cell">${film.actors.split(", ")[2]}</td>
            </tr>
            <tr>
                <td class="cell">Приоритет:</td>
                <td class="cell" align="center">
                    <input type="number" name="priority" placeholder="priority" value="${film.priority}" maxlength="2" required
                           oninput="if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           form="add-form">
                </td>
                <td class="cell">${film.actors.split(", ")[3]}</td>
            </tr>
            <tr>
                <td class="cell">Последнее обращение:</td>
                <td class="cell" align="center">${film.date}</td>
                <td class="cell">${film.actors.split(", ")[4]}</td>
            </tr>
        </table>
        <div class="inf-description">
            <p>Описание</p>
            <textarea name="description" placeholder="description" required>${film.description}</textarea>
        </div>
        <div class="inf-action">
            <c:if test="${pageContext.request.queryString != null}">
                <c:url value="/add" var="url"/>
                <c:set value="добавить" var="var"/>
            </c:if>
            <c:if test="${pageContext.request.queryString == null}">
                <c:url value="/edit" var="url"/>
                <c:set value="изменить" var="var"/>
            </c:if>
            <form action="<c:url value="${url}"/>" name="film" method="POST" id="add-form">
                <input type="hidden" name="id" value="${film.id}">
                <input type="hidden" name="actors" value="${film.actors}">
                <input type="hidden" name="date" value="${film.date}">
                <input type="hidden" name="description" value="${film.description}">
                <input type="hidden" name="poster" value="${film.poster}">
                <input type="submit" value="${var}">
            </form>
        </div>
    </div>
</div>
</body>
</html>

