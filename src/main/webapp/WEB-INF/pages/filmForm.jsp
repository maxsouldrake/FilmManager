<%--
  author: SoulDrake
  created: 27.10.2021  14:56
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Film Form</title>
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<section>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <hr>
    <h2>${film.id == null ? 'Create film' : 'Edit film'}</h2>
    <form method="post" action="films">
        <input type="hidden" name="id" value="${film.id}">
        <dl>
            <dt>Title:</dt>
            <dd><label>
                <input type="text" value="${film.title}" name="title" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Year:</dt>
            <dd><label>
                <input type="number" value="${film.year}" size=4 name="year" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Genre:</dt>
            <dd><label>
                <input type="text" value="${film.genre}" name="genre" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Country:</dt>
            <dd><label>
                <input type="text" value="${film.country}" name="country" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Date:</dt>
            <dd><label>
                <input type="date" value="${film.date}" name="date" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Priority:</dt>
            <dd><label>
                <input type="number" value="${film.priority}" name="priority" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Actors:</dt>
            <dd><label>
                <input type="text" value="${film.actors}" name="actors" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><label>
                <input type="text" value="${film.description}" name="description" required>
            </label></dd>
        </dl>
        <dl>

            <dt>Poster:</dt>
            <dd><label>
                <input type="text" value="${film.posterUrl}" name="posterUrl" required>
            </label></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
