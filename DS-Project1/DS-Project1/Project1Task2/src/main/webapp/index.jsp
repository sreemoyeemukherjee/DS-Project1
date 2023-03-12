<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Women's World Cup</title>
</head>
<body>
<h1>Women's World Cup 2023</h1>
<h5>Created by Sreemoyee Mukherjee</h5>
<h2>Participating Countries</h2>
<p>Choose a country: </p>
<form action="${pageContext.request.contextPath}/womenWorldCup<%request.getAttribute("countries");%>" method="GET">
<select name="countries">
    <option>Argentina</option>
    <option>Australia</option>
    <option>Brazil</option>
    <option>Canada</option>
    <option>China</option>
    <option>Colombia</option>
    <option>Costa Rica</option>
    <option>Denmark</option>
    <option>England</option>
    <option>France</option>
    <option>Germany</option>
    <option>Ireland</option>
    <option>Italy</option>
    <option>Jamaica</option>
    <option>Japan</option>
    <option>Morocco</option>
    <option>Netherlands</option>
    <option>New Zealand</option>
    <option>Nigeria</option>
    <option>Norway</option>
    <option>Philippines</option>
    <option>South Africa</option>
    <option>South Korea</option>
    <option>Spain</option>
    <option>Sweden</option>
    <option>Switzerland</option>
    <option>United States</option>
    <option>Vietnam</option>
    <option>Zambia</option>
</select>
    <br><br>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>