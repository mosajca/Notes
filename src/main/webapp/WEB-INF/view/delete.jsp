<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Usuwanie notatki</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>
<h2>Usuwanie notatki</h2>
<p>Czy na pewno chcesz usunąć notatkę?</p>
<form:form method="post">
    <button type="submit">Usuń</button>
</form:form>
</body>
</html>
