<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Notes</title>
    <link rel="stylesheet" href="/main.css">
    <link rel="icon" href="/favicon.ico">
</head>
<body>
<header>
    <a href="/notes">Notatki</a>
    <a href="/notes/add">Dodaj</a>
    <a href="/logout">Wyloguj</a>
</header>
<main>
    <%--@elvariable id="notes" type="java.util.List<notes.model.note.Note>"--%>
    <c:forEach var="note" items="${notes}">
        <div class="note">
            <h3><c:out value="${note.title}"/></h3>
            <p><c:out value="${note.content}"/></p>
            <p>Utworzono: <c:out value="${note.creationTimestamp}"/></p>
            <p>Zmodyfikowano: <c:out value="${note.updateTimestamp}"/></p>
            <p>
                <a href="/notes/update/${note.id}">aktualizuj</a>
                <a href="/notes/delete/${note.id}">usuń</a>
            </p>
        </div>
    </c:forEach>
</main>
<footer>
    <c:set value="${empty param.page ? 0 : param.page}" var="currentPage"/>
    <c:if test="${currentPage > 0}">
        <a href="/notes?page=${currentPage - 1}">nowsze</a>
    </c:if>
    <%--@elvariable id="totalPages" type="java.lang.Integer"--%>
    <c:if test="${currentPage + 1 < totalPages}">
        <a href="/notes?page=${currentPage + 1}">starsze</a>
    </c:if>
</footer>
</body>
</html>
