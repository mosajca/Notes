<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Aktualizuj</title>
    <link rel="icon" href="/favicon.ico">
</head>
<body>

<%--@elvariable id="form" type="notes.model.note.NoteForm"--%>
<form:form method="post" modelAttribute="form">
    <p>Tytuł: <form:input path="title"/></p>
    <p>Treść: <form:textarea path="content"/></p>
    <button type="submit">aktualizuj</button>
</form:form>

</body>
</html>
