<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Welcome!</title>
</head>
<body>
  
  <c:forEach items = "${it.map}" var="req" >
			    	<c:out value="${it.user}"/> added you as a friend
			    		
				        </c:forEach>	
 </form>
</body>
</html>
