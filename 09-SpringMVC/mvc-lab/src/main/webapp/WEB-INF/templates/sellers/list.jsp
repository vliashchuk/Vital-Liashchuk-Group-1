<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>

<c:url var="sellerListUrl" value="/sellers" />
<div id="content">
    <h1>Seller list</h1>
    <table border="1">
        <th>Id</th>
        <th>Name</th>
        <th>Action</th>
         
        <c:forEach var="seller" items="${sellers.sellers}" varStatus="status">
        <tr>
            <td>${seller.id}</td>
            <td>${seller.name}</td>
            <td>
                <a href="${sellerListUrl}/${seller.id}">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                
                <a href="javascript:sendDeleteRequest('${sellerListUrl}/${seller.id}', '${sellerListUrl}')">Delete</a>
            </td>
                     
        </tr>
        </c:forEach>             
    </table>
</div>
