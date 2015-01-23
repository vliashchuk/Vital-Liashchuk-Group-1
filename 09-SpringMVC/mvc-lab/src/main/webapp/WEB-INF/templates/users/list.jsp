<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>

<c:url var="userListUrl" value="/users" />
<div id="content">
    <h1>User list</h1>
    <table border="1">
    	<th>status.index</th>
        <th>Id</th>
        <th>Name</th>
        <th>Action</th>
         
        <c:forEach var="user" items="${users.users}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>
                <a href="${userDetailsUrl}">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                
                <a href="javascript:sendDeleteRequest('${userListUrl}/${user.id}', '${userListUrl}')">Delete</a>
            </td>
                     
        </tr>
        </c:forEach>             
    </table>
</div>
