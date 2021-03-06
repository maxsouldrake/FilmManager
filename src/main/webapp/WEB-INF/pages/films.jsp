<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Film Manager</title>
    <link href="<c:url value="/res/style2.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
</head>
<body>
<div class="main-back">
    <div class="main-addrand">
        <div class="main-add">
            <form action="<c:url value="/add"/>">
                <input type="text" name="webSearchQuery" placeholder="title" maxlength="128" title="search by title">
                <input type="submit" value="НАЙТИ" title="click to search">
            </form>
        </div>
        <div class="main-random">
            <a href="<c:url value="/film"/>" title="получить случайный фильм">
                <span class="icon icon-edit">случайный</span>
            </a>
        </div>
    </div>
    <div class="main-search">
        <div class="main-search-field">
            <div>
                <input type="search" name="genreSearch" placeholder="genre" value="${genreSearch}"
                       maxlength="16" title="search by genre" form="search-form">
                <input type="search" name="countrySearch" placeholder="country" value="${countrySearch}"
                       maxlength="16" title="search by country" form="search-form">
            </div>
            <div>
                <input type="search" name="titleSearch" placeholder="title" value="${titleSearch}"
                       maxlength="128" title="search by title" form="search-form">
                <input type="search" name="yearSearch" placeholder="year" value="${yearSearch}"
                       maxlength="4" title="search by year" form="search-form">
            </div>
        </div>
        <div class="main-search-button">
            <form action="<c:url value="/"/>" id="search-form">
                <input type="submit" value="" title="click to search"><span class="icon icon-find"></span>
            </form>
        </div>
    </div>
    <div class="main-list">
        <table>
            <c:if test="${filmsCount > 0}">
                <tr>
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
                    <th class="col-ny left-side" onclick="location.href = '${url}'; ${sortDirection = (sortDirection == 'asc' ? 'desc' : 'asc')}">№</th>
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
                        <c:param name="sortBy" value="title"/>
                        <c:param name="sortDirection" value="${sortDirection}"/>
                    </c:url>
                    <th class="col-t" onclick="location.href = '${url}'">title</th>
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
                        <c:param name="sortBy" value="year"/>
                        <c:param name="sortDirection" value="${sortDirection}"/>
                    </c:url>
                    <th class="col-ny" onclick="location.href = '${url}'">year</th>
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
                        <c:param name="sortBy" value="genre"/>
                        <c:param name="sortDirection" value="${sortDirection}"/>
                    </c:url>
                    <th class="col-gc" onclick="location.href = '${url}'">genre</th>
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
                        <c:param name="sortBy" value="country"/>
                        <c:param name="sortDirection" value="${sortDirection}"/>
                    </c:url>
                    <th class="col-gc right-side" onclick="location.href = '${url}'">country</th>
                </tr>
                <c:forEach var="film" items="${filmList}" varStatus="i">
                    <tr style="cursor: pointer" onclick="location.href = '/info/${film.id}'">
                        <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                        <td class="title">${film.title}</td>
                        <td>${film.year}</td>
                        <td>${film.genre}</td>
                        <td class="right-side">${film.country}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${filmsCount == 0}">
                <tr>
                    <td colspan="5" style="font-size: 150%" class="left-side right-side">
                        <c:if test="${allFilmsCount == 0}">
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
                    <div class="main-footer">
                        <div class="class6">
                            Всего фильмов: ${allFilmsCount}
                        </div>
                        <div class="class5">
                            <c:if test="${pagesCount > 1}">
                                <c:set value="disabled" var="disabled"/>
                                <c:set value="" var="active"/>
                                <c:url value="/" var="url">
                                    <c:param name="page" value="1"/>
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
                                    <c:if test="${!sortBy.equals('id')}">
                                        <c:param name="sortBy" value="${sortBy}"/>
                                        <c:param name="sortDirection" value="${sortDirection}"/>
                                    </c:if>
                                </c:url>
                                <a class="${page == 1 ? disabled : active}" href="${url}">
                                    &nbsp<span class="icon icon-first"></span>&nbsp
                                </a>
                                <c:url value="/" var="url">
                                    <c:param name="page" value="${page - 1}"/>
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
                                    <c:if test="${!sortBy.equals('id')}">
                                        <c:param name="sortBy" value="${sortBy}"/>
                                        <c:param name="sortDirection" value="${sortDirection}"/>
                                    </c:if>
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
                                        <c:if test="${!sortBy.equals('id')}">
                                            <c:param name="sortBy" value="${sortBy}"/>
                                            <c:param name="sortDirection" value="${sortDirection}"/>
                                        </c:if>
                                    </c:url>
                                    <c:set value="current-page" var="current"/>
                                    <c:set value="" var="perspective"/>
                                    <a class="${page == i.index ? current : perspective}" href="${url}">${i.index}</a>
                                </c:forEach>

                                <c:url value="/" var="url">
                                    <c:param name="page" value="${page + 1}"/>
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
                                    <c:if test="${!sortBy.equals('id')}">
                                        <c:param name="sortBy" value="${sortBy}"/>
                                        <c:param name="sortDirection" value="${sortDirection}"/>
                                    </c:if>
                                </c:url>
                                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                                    &nbsp<span class="icon icon-next"></span>&nbsp
                                </a>
                                <c:url value="/" var="url">
                                    <c:param name="page" value="${pagesCount}"/>
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
                                    <c:if test="${!sortBy.equals('id')}">
                                        <c:param name="sortBy" value="${sortBy}"/>
                                        <c:param name="sortDirection" value="${sortDirection}"/>
                                    </c:if>
                                </c:url>
                                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                                    &nbsp<span class="icon icon-last"></span>&nbsp
                                </a>
                            </c:if>
                        </div>
                        <div class="class6">
                            Найдено фильмов: ${filmsCount}
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>


