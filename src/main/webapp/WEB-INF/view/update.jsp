<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Aktualizacja notatki</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>
<h2>Aktualizacja notatki</h2>
<%--@elvariable id="form" type="notes.model.note.NoteForm"--%>
<form:form method="post" modelAttribute="form">
    <p>Tytuł: <form:input path="title"/></p>
    <p><form:errors path="title"/></p>
    <p>Treść: <form:textarea path="content"/></p>
    <p><form:errors path="content"/></p>
    <button type="submit">aktualizuj</button>
</form:form>
</body>
</html>
