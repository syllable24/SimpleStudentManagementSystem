<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
  <head>
  </head>
  <body>
    <h1>Scheduler with JSTL Example</h1>
    <hr>
    <p>A list of months
    <hr>
    <% String[] months = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
       pageContext.setAttribute("monthNames", months);
    %>
    <c:forEach var="i" begin="1" end="12" step="1">
        
      <c:out value="${i}" />.<c:out value="${monthNames[i-1]}" />
      <br />
    </c:forEach>
  </body>
</html>
