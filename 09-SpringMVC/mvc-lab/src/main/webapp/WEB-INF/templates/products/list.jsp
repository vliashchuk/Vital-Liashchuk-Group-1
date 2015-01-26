<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>

<c:url var="productListUrl" value="/products" />
<div id="content">
    <h1>Product list</h1>
    <table border="1">
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Action</th>
         
        <c:forEach var="product" items="${products.products}" varStatus="status">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            
            <td>
                <a href="${productListUrl}/${product.id}">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                
                <a href="javascript:sendDeleteRequest('${productListUrl}/${product.id}', '${productListUrl}')">Delete</a>
            </td>
                     
        </tr>
        </c:forEach>             
    </table>
</div>
