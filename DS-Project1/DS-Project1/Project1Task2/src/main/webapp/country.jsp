<%--
  Created by IntelliJ IDEA.
  User: Sreemoyee
  Date: 04-02-2023
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title><%= request.getParameter("countries")%></title>
</head>
<body>
<h1>Country: <%= request.getParameter("countries")%></h1>
<h3>Nickname: <%= request.getAttribute("nickName")%></h3>
<b><%= request.getAttribute("nickNameURL")%></b>
<h3>Capital City: <%= request.getAttribute("capital")%></h3>
<b><%= request.getAttribute("capitalURL")%></b>
<h3>Top Scorer in 2019: <%= request.getAttribute("topScorer")%></h3>
<b><%= request.getAttribute("topScorerURL")%></b>
<h3>Flag: </h3>
<img src="<%= request.getAttribute("flagURL")%>" width="150" height="100"><br><br>
<b><%= request.getAttribute("flagSiteURL")%></b>
<h3>Flag Emoji: </h3>
<img src="<%= request.getAttribute("EmojiURL")%>" width="300" height="200"><br><br>
<b><%= request.getAttribute("flagEmojiURL")%></b>
<br><br>
<form action="${pageContext.request.contextPath}" method="GET">
<input type = "submit" value = "Continue" />
</form>
</body>
</html>
