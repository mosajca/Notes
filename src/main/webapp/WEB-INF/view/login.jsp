<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Zaloguj</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>

<form:form method="post">
    <p>
        <label for="username">Nazwa:</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Hasło:</label>
        <input type="password" id="password" name="password"/>
    </p>
    <button type="submit">zaloguj</button>
</form:form>

</body>
</html>
