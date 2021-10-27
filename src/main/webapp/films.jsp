<%--
  Created by IntelliJ IDEA.
  User: SoulDrake
  Date: 27.10.2021
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Film Manager</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Films</h2>
<table>
  <thead>
  <tr>
    <th>id</th>
    <th>title</th>
    <th>year</th>
    <th>genre</th>
    <th>country</th>
    <th>date</th>
    <th>priority</th>
    <th>actors</th>
    <th>description</th>
    <th>poster</th>
    <th>action</th>
  </tr>
  </thead>
  <tr>
    ${requestScope.films}
  </tr>
</table>

<h2>Add</h2>

</body>
</html>
