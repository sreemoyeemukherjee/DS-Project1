<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html>
<head>
    <title>Project1Task3</title>
</head>
<body>
<h1>Distributed Systems Class Clicker</h1>
<form action="${pageContext.request.contextPath}/getResults" method="GET">
    The results from the survey are as follows
    <br><br>
    A: <%= request.getAttribute("countA")%><br><br>
    B: <%= request.getAttribute("countB")%><br><br>
    C: <%= request.getAttribute("countC")%><br><br>
    D: <%= request.getAttribute("countD")%><br><br>
    <br>
</form>
</body>
</html>