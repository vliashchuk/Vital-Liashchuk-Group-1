<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Greeting Id</th>
                <th>Text</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${greetings}" var="greeting">
                <tr>
                    <td><c:out value="${greeting.id}" /></td>
                    <td><c:out value="${greeting.text}" /></td>
                    <td><a href="GreetingControllerServlet?action=edit&greetingId=<c:out value="${greeting.id}"/>">Update</a></td>
                    <td><a href="GreetingControllerServlet?action=delete&greetingId=<c:out value="${greeting.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="GreetingControllerServlet?action=insert">Add Greeting</a></p>
</body>
</html>