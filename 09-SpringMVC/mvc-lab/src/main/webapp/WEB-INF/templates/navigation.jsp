<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="navigation">
    <ul>
        <li>
            <a href="<c:url value="/users"/>" title="User list">User list</a>
        </li>
        <li>
            <a href="<c:url value="/users/new"/>"  title="New user">New user</a>
        </li>
    </ul>
</div>