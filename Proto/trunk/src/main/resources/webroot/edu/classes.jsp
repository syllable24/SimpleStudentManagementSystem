<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<html>
  <head>
  <link rel="stylesheet" type="text/css" href="/static/main.css" title="Standard Style" />

  </head>
  <body>
    <h1>Classes Verwaltung</h1>
    <hr>
    <p>Classes mit MENUEBAR AND COMMANDBAR JSP fragment file in WEB-INF/tags</p>
        <acme:menubar menue="EDU" />
        <hr>
        <acme:commandbar actions="FIND" />
        <div>
        <form onchange="alert(event.srcElement.tagName)" >
            <input type="TEXT" value="3">
            <input type="PASSWORD" value="ABCD">
            <select data-bind="order" name="order"><option>A</option><option>B</option></select>
            <textarea>Hilfe</textarea>
        </form>
        </div>
  </body>
</html>
