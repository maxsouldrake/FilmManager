<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  author: SoulDrake
  created: 27.10.2021  14:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>FILMS</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Films</h2>
${requestScope.films}
<table>
  <thead>
  <tr>
    <th>id</th>
    <th>title</th>
    <th>year</th>
    <th>genre</th>
    <th>country</th>
    <th>action</th>
  </tr>
  </thead>
  <c:forEach var="film" items="${requestScope.films}">
    <tr>
      <td>${film.id}</td>
      <td>${film.title}</td>
      <td>${film.year}</td>
      <td>${film.genre}</td>
      <td>${film.country}</td>
      <td>
        <a href="films?action=update&id=${film.id}">Update</a>
        <a href="films?action=delete&id=${film.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>

<h2>Add</h2>
<a href="films?action=create">Add new film</a>
<br><br>
</body>
</html>
