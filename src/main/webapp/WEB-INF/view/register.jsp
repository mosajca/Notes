<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Notes</title>
    <link rel="icon" href="/favicon.ico">
</head>
<body>

<%--@elvariable id="form" type="notes.model.user.UserForm"--%>
<form:form action="/register" method="post" modelAttribute="form">
    <p>Nazwa: <form:input path="name"/></p>
    <p>Hasło: <form:password path="password"/></p>
    <button type="submit">zarejestruj</button>
</form:form>

</body>
</html>
