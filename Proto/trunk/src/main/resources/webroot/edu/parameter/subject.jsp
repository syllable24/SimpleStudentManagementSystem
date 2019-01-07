<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
  <head>
  </head>
  <body>
    <h1>Subject Example</h1>
    <hr>
    <% String[] subjects = new String[]{"SWE","DWH","INF","MED","NAW","MAT","ENG","GWR","DOK","PQM"};
       pageContext.setAttribute("subjectNames", subjects);
    %>
    
    <p>A Subject example
    <hr>
    <table style="margin:0; align-self: center; border: 2px solid #808080; border-collapse">
    <c:forEach var="i" begin="1" end="10" step="1">
    <tr>
        <td><c:out value="${i}" /></td><td><c:out value="${subjectNames[i-1]}" /></td>
</tr>
    </c:forEach>
  </table>
  </body>
</html>
