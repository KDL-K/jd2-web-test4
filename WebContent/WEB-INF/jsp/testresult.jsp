<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

   <fmt:setLocale value="${sessionScope.local}"/>
   <fmt:setBundle basename="resources/local" var="loc"/>
   
   <fmt:message bundle="${loc}" key="local.lang_button_ru" var="button_ru"/>
   <fmt:message bundle="${loc}" key="local.lang_button_en" var="button_en"/>
   
   <fmt:message bundle="${loc}" key="local.button_main_page" var="b_main"/> 
   
</head>

<body>

   <form action="Controller" method="post">
      <input type="hidden" name="command" value="change_language"/>
      <input type="hidden" name="lang" value="ru"/>
      <input type="submit" value="${button_ru}"/>
   </form> 
   <form action="Controller" method="post">
      <input type="hidden" name="command" value="change_language"/>
      <input type="hidden" name="lang" value="en"/>
      <input type="submit" value="${button_en}"/>
   </form><br/>
   
   <form action="Controller" method="post">
      <input type="hidden" name="command" value="go_to_main_page"/>
      <input type="submit" value="${b_main}"/>
  </form><br/>
   
 <c:if test="${not empty result}">
    <c:out value="Your test result: ${result}%"></c:out>
 </c:if><br/>
 <c:if test="${empty result}">
    <c:out value="Sorry. Your time is over! Your test result: 0%"></c:out>
 </c:if><br/>

   

</body>
</html>