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
                <td colspan="2" class="inf-head">Информация:</td>
                <td width="10%"></td>
                <td class="inf-head">В ролях:</td>
            </tr>
            <tr>
                <td class="inf-cell">Год:</td>
                <td class="inf-cell" align="center">${film.year}</td>

                <td></td>
                <td class="inf-cell">${film.actors.split(", ")[0]}</td>
            </tr>
            <tr>
                <td class="inf-cell">Жанр:</td>
                <td class="inf-cell" align="center">${film.genre}</td>

                <td></td>
                <td class="inf-cell">${film.actors.split(", ")[1]}</td>
            </tr>
            <tr>
                <td class="inf-cell">Страна:</td>
                <td class="inf-cell" align="center">${film.country}</td>
                <td></td>
                <td class="inf-cell">${film.actors.split(", ")[2]}</td>
            </tr>
            <tr>
                <td class="inf-cell">Последнее обращение:</td>
                <td class="inf-cell" align="center">${film.date}</td>
                <td></td>
                <td class="inf-cell">${film.actors.split(", ")[3]}</td>
            </tr>
            <tr>
                <td class="inf-cell">Приоритет:</td>
                <td class="inf-cell" align="center">${film.priority}</td>
                <td></td>
                <td class="inf-cell">${film.actors.split(", ")[4]}</td>
            </tr>
        </table>
        <div class="inf-description">
            <p>Описание</p>
            ${film.description}
        </div>
        <div class="inf-action">
            <div>ed
            </div>
            <div>del
            </div>
        </div>
    </div>
</div>
</body>
</html>
