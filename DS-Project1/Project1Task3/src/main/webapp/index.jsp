<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html>
<head>
    <title>Project1Task3</title>
</head>
<body>
<h1>Distributed Systems Class Clicker</h1>
<form action="${pageContext.request.contextPath}/submit" method="GET">
    <% if (request.getAttribute("message") != null) { %>
    <%= request.getAttribute("message")%><br>
    <% } %>
    Submit your answer to the current question:
    <br>
    <input type="radio" id="optionA" name="options" value="A">
    <label for="optionA">A</label><br>
    <input type="radio" id="optionB" name="options" value="B">
    <label for="optionB">B</label><br>
    <input type="radio" id="optionC" name="options" value="C">
    <label for="optionC">C</label><br>
    <input type="radio" id="optionD" name="options" value="D">
    <label for="optionD">D</label><br>
    <br>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>