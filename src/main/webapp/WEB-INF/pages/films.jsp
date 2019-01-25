<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Film Manager</title>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
</head>
<body>

<table class="style">
    <c:if test="${filmsCount > 0}">
        <tr>
            <th class="left-side">№</th>
            <th style="width: 100%">title</th>
            <th>year</th>
            <th>genre</th>
            <th class="right-side">date</th>
        </tr>
        <c:forEach var="film" items="${filmsList}" varStatus="i">
            <tr style="cursor: pointer" onclick="location.href = '/info/${film.id}'">
                <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                <td class="title">${film.title}</td>
                <td>${film.year}</td>
                <td>${film.genre}</td>
                <td class="right-side">${film.date}</td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${filmsCount == 0}">
        <tr>
            <td colspan="5" style="font-size: 150%" class="left-side right-side">
                <c:if test="${allFilmssCount == 0}">
                    the list is empty but you can add a new film
                </c:if>
                <c:if test="${allFilmsCount != 0}">
                    no data found for such parameters
                </c:if>
            </td>
        </tr>
    </c:if>
    <tr>
        <td colspan="5" class="left-side link right-side">
            <a style="margin-right: 70px; font-size: 100%" href="<c:url value="/add"/>">
                <span class="icon icon-add"></span>Add new film
            </a>
            <c:if test="${pagesCount > 1}">
                <c:set value="disabled" var="disabled"/>
                <c:set value="" var="active"/>
                <c:url value="/" var="url">
                    <c:param name="page" value="1"/>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-first"></span>&nbsp
                </a>
                <c:url value="/" var="url">
                    <c:param name="page" value="${page - 1}"/>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-prev"></span>&nbsp
                </a>

                <c:if test="${pagesCount <= 5}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${pagesCount}"/>
                </c:if>
                <c:if test="${pagesCount > 5}">
                    <c:choose>
                        <c:when test="${page < 3}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:when>
                        <c:when test="${page > pagesCount - 2}">
                            <c:set var="begin" value="${pagesCount - 4}"/>
                            <c:set var="end" value="${pagesCount}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${page - 2}"/>
                            <c:set var="end" value="${page + 2}"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                    <c:url value="/" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <c:set value="current-page" var="current"/>
                    <c:set value="" var="perspective"/>
                    <a class="${page == i.index ? current : perspective}" href="${url}">${i.index}</a>
                </c:forEach>

                <c:url value="/" var="url">
                    <c:param name="page" value="${page + 1}"/>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-next"></span>&nbsp
                </a>
                <c:url value="/" var="url">
                    <c:param name="page" value="${pagesCount}"/>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-last"></span>&nbsp
                </a>
            </c:if>
            <span style="margin-left: 70px; font-size: 120%">Total number Of films: ${filmsCount}</span>
        </td>
    </tr>
</table>
<div>
    <form action="<c:url value="/add"/>" method="post">
        <input type="text" name="webSearchQuery" placeholder="title" maxlength="128" title="search by title">
        <input type="submit" value="" title="click to search"><span class="icon icon-find"></span>
    </form>
</div>

</body>
</html>


