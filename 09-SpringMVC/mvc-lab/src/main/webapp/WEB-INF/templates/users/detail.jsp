<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>


<div id="content">
    <h1>User detail</h1>
    
<c:url var="saveUrl" value="/users" />
<form:form modelAttribute="user" method="POST" action="${saveUrl}">
 <table>
  <tr>
   <td><form:label path="id">Id:</form:label></td>
   <td><form:input path="id" disabled="true"/></td>
  </tr>
  
  <tr>
   <td><form:label path="username">User name</form:label></td>
   <td><form:input path="username"/></td>
  </tr>
  
 </table> 
 <input type="submit" value="Save" />
 <c:if test="${_method == 'PUT'}">
   <input type="button" value="Delete" onclick="sendDeleteRequest('${saveUrl}/${user.id}', '${saveUrl}')" />
 </c:if>
 
</form:form>

</div>