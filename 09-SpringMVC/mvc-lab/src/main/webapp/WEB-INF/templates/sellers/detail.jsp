<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="scriptUrl" value="/resources/script/delete-request.js" />
<script src="${scriptUrl}"></script>


<div id="content">
    <h1>Seller detail</h1>
    
<c:url var="saveUrl" value="/sellers" />
<c:url var="proposalListUrl" value="/proposals" />
<form:form modelAttribute="seller" method="PUT" action="${saveUrl}">

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
 <a href="${proposalListUrl}/forSeller/${seller.id}">Edit proposals</a> <br/>
 <input type="submit" value="Save" />
 <input type="button" value="Delete" onclick="sendDeleteRequest('${saveUrl}/${seller.id}', '${saveUrl}')" />
 
</form:form>

</div>