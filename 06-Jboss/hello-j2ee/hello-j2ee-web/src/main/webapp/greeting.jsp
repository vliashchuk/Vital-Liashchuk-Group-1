<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Add new greeting</title>
</head>
<body>

    <form method="POST" action='GreetingControllerServlet' name="frmAddGreeting">
		Id : <input type="text" readonly="readonly" name="greetingId"
            value="<c:out value="${greeting.id}" />" /> <br /> 
        Text : <input
            type="text" name="greetingText"
            value="<c:out value="${greeting.text}" />" />  <br /> <input
            type="submit" value="Submit" />
    </form>
</body>
</html>