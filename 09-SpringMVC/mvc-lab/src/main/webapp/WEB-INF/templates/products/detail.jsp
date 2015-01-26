<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>


<div id="content">
    <h1>Product detail</h1>
    
<c:url var="saveUrl" value="/products" />
<form:form modelAttribute="product" method="PUT" action="${saveUrl}">

 <table>
  <tr>
   <td><form:label path="id">Id:</form:label></td>
   <td><form:input path="id" readonly="true"/></td>
  </tr>
  
  <tr>
   <td><form:label path="name">Product name</form:label></td>
   <td><form:input path="name"/></td>
  </tr>
  
  <tr>
   <td><form:label path="description">Product description</form:label></td>
   <td><form:input path="description"/></td>
  </tr>
  
 </table> 
 <input type="submit" value="Save" />
 <input type="button" value="Delete" onclick="sendDeleteRequest('${saveUrl}/${product.id}', '${saveUrl}')" />
 
</form:form>

</div>