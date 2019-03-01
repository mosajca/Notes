<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Notes</title>
    <link rel="icon" href="/favicon.ico">
</head>
<body>

<%--@elvariable id="notes" type="java.util.List<notes.model.note.Note>"--%>
<c:forEach var="note" items="${notes}">
    <h2><c:out value="${note.title}"/></h2>
    <p><c:out value="${note.content}"/></p>
    <a href="/update/${note.id}">aktualizuj</a>
    <a href="/delete/${note.id}">usuń</a>
</c:forEach>

</body>
</html>
