<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>


<div id="content">
    <h1>Seller detail</h1>
    
<c:url var="saveUrl" value="/sellers" />
<form:form modelAttribute="seller" method="POST" action="${saveUrl}">

 <table>
  <tr>
   <td><form:label path="id">Id:</form:label></td>
   <td><form:input path="id" readonly="true"/></td>
  </tr>
  
  <tr>
   <td><form:label path="name">Seller name</form:label></td>
   <td><form:input path="name"/></td>
  </tr>
  
 </table> 
 <input type="submit" value="Save" />
 
</form:form>

</div>