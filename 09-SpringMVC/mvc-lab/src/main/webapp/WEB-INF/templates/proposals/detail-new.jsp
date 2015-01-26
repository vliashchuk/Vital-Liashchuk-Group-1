<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>


<div id="content">
    <h1>Seller "${seller.name}" proposals</h1>
    
<c:url var="saveUrl" value="/proposals" />

<c:choose>
<c:when test="${!(empty allProducts)}">

	<form:form modelAttribute="proposal" method="POST" action="${saveUrl}">
	
	  <form:hidden path="sellerId" />
	  
	 <table>
	
	  <tr>
	   <td><form:label path="productId">Product name</form:label></td>
	   <td><form:select path="productId">
		              <form:options items="${allProducts}" itemValue="id" itemLabel="name"/>
		   </form:select>
	  </tr>
	  
	  <tr>
	   <td><form:label path="price">Price</form:label></td>
	   <td><form:input path="price"/></td>
	  </tr>
	  
	 </table> 
	 <input type="submit" value="Add" />
	 
	</form:form>

</c:when>
<c:otherwise>
      <p>There is no products created. Create product first.</p>
</c:otherwise>
</c:choose>

    <h2>Proposal list</h2>
    <table border="1">
        <th>Id</th>
        <th>Product name</th>
        <th>Price</th>
         
        <c:forEach var="proposal" items="${proposals}" varStatus="status">
        <tr>
            <td>${proposal.id}</td>
            <td>${proposal.product.name}</td>
            <td>${proposal.price}</td>
        </tr>
        </c:forEach>             
    </table>

</div>