<%--
  author: SoulDrake
  created: 27.10.2021  14:56
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Film Form</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create film' : 'Edit film'}</h2>
    <form method="post" action="films">
        <input type="hidden" name="id" value="${requestScope.film.id}">
        <dl>
            <dt>Title:</dt>
            <dd><label>
                <input type="text" value="${requestScope.film.title}" name="title" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Year:</dt>
            <dd><label>
                <input type="number" value="${requestScope.film.year}" size=4 name="year" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Genre:</dt>
            <dd><label>
                <input type="text" value="${requestScope.film.genre}" name="genre" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Country:</dt>
            <dd><label>
                <input type="text" value="${requestScope.film.country}" name="country" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Date:</dt>
            <dd><label>
                <input type="date" value="${requestScope.film.date}" name="date" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Priority:</dt>
            <dd><label>
                <input type="number" value="${requestScope.film.priority}" name="priority" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Actors:</dt>
            <dd><label>
                <input type="text" value="${requestScope.film.actors}" name="actors" required>
            </label></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><label>
                <input type="text" value="${requestScope.film.description}" name="description" required>
            </label></dd>
        </dl>
        <dl>

            <dt>Poster:</dt>
            <dd><label>
                <input type="text" value="${requestScope.film.posterUrl}" name="posterUrl" required>
            </label></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
