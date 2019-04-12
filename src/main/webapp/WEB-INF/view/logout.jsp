<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Wylogowywanie</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>
<h2>Wylogowywanie</h2>
<p>Czy na pewno chcesz się wylogować?</p>
<form:form method="post">
    <button type="submit">wyloguj</button>
</form:form>
</body>
</html>
