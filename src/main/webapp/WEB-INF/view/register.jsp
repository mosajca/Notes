<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Zarejestruj</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>

<%--@elvariable id="form" type="notes.model.user.UserForm"--%>
<form:form method="post" modelAttribute="form">
    <p>Nazwa: <form:input path="username"/></p>
    <p>Hasło: <form:password path="password"/></p>
    <p>Email: <form:input path="email" type="email"/></p>
    <button type="submit">zarejestruj</button>
</form:form>

</body>
</html>
