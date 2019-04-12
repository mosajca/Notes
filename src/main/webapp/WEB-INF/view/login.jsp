<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Logowanie</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>
<h2>Logowanie</h2>
<c:if test="${(param.error != null) && (not empty SPRING_SECURITY_LAST_EXCEPTION)}">
    <p><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
</c:if>
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
