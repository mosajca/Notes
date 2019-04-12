<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Rejestracja</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>
<h2>Rejestracja</h2>
<%--@elvariable id="info" type="java.lang.String"--%>
<c:if test="${not empty info}"><p><c:out value="${info}"/></p></c:if>
<%--@elvariable id="form" type="notes.model.user.UserForm"--%>
<form:form method="post" modelAttribute="form">
    <p>Nazwa: <form:input path="username"/></p>
    <p><form:errors path="username"/></p>
    <p>Hasło: <form:password path="password"/></p>
    <p><form:errors path="password"/></p>
    <p>Email: <form:input path="email" type="email"/></p>
    <p><form:errors path="email"/></p>
    <button type="submit">zarejestruj</button>
</form:form>
</body>
</html>
