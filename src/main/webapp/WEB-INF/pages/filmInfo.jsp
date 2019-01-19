<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Film Info</title>
    <link href="<c:url value="/res/style2.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
</head>

<body>
<div align="center" class="back">
    <div class="title-back">
        <div class="title">${film.title}</div>
    </div>
    <div class="poster">
        <img width="333" height="500" src="<c:url value="/res/posters/${film.poster}.jpg"/>" alt="poster">
    </div>
    <div class="info">
        <table>
            <tr>
                <td colspan="2" class="head">Информация:</td>
                <td width="10%"></td>
                <td class="head">В ролях:</td>
            </tr>
            <tr>
                <td class="cell">Год:</td>
                <td class="cell" align="center">${film.year}</td>

                <td></td>
                <td class="cell">${film.actors.split(", ")[0]}</td>
            </tr>
            <tr>
                <td class="cell">Жанр:</td>
                <td class="cell" align="center">${film.genre}</td>

                <td></td>
                <td class="cell">${film.actors.split(", ")[1]}</td>
            </tr>
            <tr>
                <td class="cell">Страна:</td>
                <td class="cell" align="center">Великобритания</td>
                <td></td>
                <td class="cell">${film.actors.split(", ")[2]}</td>
            </tr>
            <tr>
                <td class="cell">Последнее обращение:</td>
                <td class="cell" align="center">${film.date}</td>
                <td></td>
                <td class="cell">${film.actors.split(", ")[3]}</td>
            </tr>
            <tr>
                <td class="cell">Приоритет:</td>
                <td class="cell" align="center">10</td>
                <td></td>
                <td class="cell">${film.actors.split(", ")[4]}</td>
            </tr>
        </table>
        <div class="description">
            <p>Описание</p>
            ${film.description}
        </div>
        <div class="action">
            <div>ed
            </div>
            <div>del
            </div>
        </div>
    </div>
</div>
</body>
</html>
