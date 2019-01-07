<html>
<%@ page session="true"%>
<body>
<jsp:useBean id='counter' scope='request' class='at.co.newe.Counter' type="at.co.newe.Counter" />

<h1>Department</h1>

Counter accessed <jsp:getProperty name="counter" property="count"/> times.<br/>
Counter last accessed by <jsp:getProperty name="counter" property="last"/><br/>
<jsp:setProperty name="counter" property="last" value="<%= request.getRequestURI()%>"/>

<a href="teacher.jsp">Goto teacher.jsp</a>

</body>
</html>
