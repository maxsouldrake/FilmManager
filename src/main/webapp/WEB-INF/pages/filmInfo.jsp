<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Film Info</title>
    <link href="<c:url value="/res/style2.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
</head>

<body>
<div align="center" class="inf-back">
    <div class="inf-title-back">
        <div class="inf-title">${film.title}</div>
    </div>
    <div class="inf-poster">
        <img width="333" height="500" src="<c:url value="/res/posters/${film.poster}"/>" alt="poster">
    </div>
    <div class="inf-info">
        <table>
            <tr>
                <th colspan="2">Информация:</th>
                <th width="10%" rowspan="6"></th>
                <th>В ролях:</th>
            </tr>
            <tr>
                <td align="left">Год:</td>
                <td>${film.year}</td>
                <td>${film.actors.split(", ")[0]}</td>
            </tr>
            <tr>
                <td align="left">Жанр:</td>
                <td>${film.genre}</td>
                <td>${film.actors.split(", ")[1]}</td>
            </tr>
            <tr>
                <td align="left">Страна:</td>
                <td>${film.country}</td>
                <td>${film.actors.split(", ")[2]}</td>
            </tr>
            <tr>
                <td align="left">Приоритет:</td>
                <td>${film.priority}</td>
                <td>${film.actors.split(", ")[3]}</td>
            </tr>
            <tr>
                <td align="left">Последнее обращение:</td>
                <td>${film.date}</td>
                <td>${film.actors.split(", ")[4]}</td>
            </tr>
        </table>
        <div class="inf-description">
            <p>Описание</p>
            ${film.description}
        </div>
        <div class="inf-action">
            <div>
                <a href="<c:url value="/edit/${film.id}"/>" title="редактировать">
                    <span class="icon icon-edit"></span>
                </a>
            </div>
            <div>
                <a href="<c:url value="/delete/${film.id}"/>" title="удалить">
                    <span class="icon icon-delete"></span>
                </a>
            </div>
            <div>
                <c:url value="/" var="url">
                    <c:param name="page" value="${page}"/>
                    <c:if test="${!empty titleSearch}">
                        <c:param name="titleSearch" value="${titleSearch}"/>
                    </c:if>
                    <c:if test="${!empty yearSearch}">
                        <c:param name="yearSearch" value="${yearSearch}"/>
                    </c:if>
                    <c:if test="${!empty genreSearch}">
                        <c:param name="genreSearch" value="${genreSearch}"/>
                    </c:if>
                    <c:if test="${!empty countrySearch}">
                        <c:param name="countrySearch" value="${countrySearch}"/>
                    </c:if>
                </c:url>
                <a href="${url}" title="вернуться к списку фильмов">
                    <span class="icon icon-watch"></span>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
