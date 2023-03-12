    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Project1Task1</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/computeHashes" method="GET">
    Enter text: <input type = "text" name = "text">
    <br>
    <input type="radio" id="MD5" name="hash_method" value="MD5" checked="true">
    <label for="MD5">MD5</label><br>
    <input type="radio" id="SHA-256" name="hash_method" value="SHA-256">
    <label for="SHA-256">SHA-256</label><br>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>