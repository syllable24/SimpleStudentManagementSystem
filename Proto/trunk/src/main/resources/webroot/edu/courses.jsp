<html>
<h1>Courses mit Parameter</h1>
<img src="/static/save.png" />
<img src="/static/create.png" />
<img src="/static/delete.png" />
<img src="/static/find.png" />
<img src="/static/logout.png" />
<table border="1">
    
  <tr><th>Expression</th><th>Result</th></tr>      
  <tr>
    <td>\${param["course"]}</td>
    <td>${param["course"]}&nbsp;</td>
  </tr><tr>
    <td>\${header["host"]}</td>
    <td>${header["host"]}</td>
  </tr><tr>
    <td>\${header["user-agent"]}</td>
    <td>${header["user-agent"]}</td>
  </tr><tr>
    <td>\${1+1}</td>
    <td>${1+1}</td>
  </tr><tr>
    <td>\${param["course"] & "TEST"}</td>
    <td>${param["course"] && "TEST"}&nbsp;</td>
  </tr>
</table>
</html>
