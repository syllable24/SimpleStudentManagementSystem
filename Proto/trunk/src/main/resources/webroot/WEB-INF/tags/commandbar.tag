<%-- 
   <commandbar>
   display for Command like Save,Create,Delete,... using Images
--%>
<%@ attribute name="color" %>
<%@ attribute name="bgcolor" %>
<%@ attribute name="actions" %>
<div class="commandbar" onmousedown="alert(event.srcElement.name)" >
  <img src="/static/create.png" name="NEW" title="Neu" accesskey="N" />
  <img src="/static/save.png" name="SAVE" title="Speichern" accesskey="S" />
  <img src="/static/delete.png" name="DELETE" title="Löschen" accesskey="D" />
  <img src="/static/previous.png" title="Zurück" accesskey="BACK" />
  <img src="/static/next.png" title="Vorwärts"  />
  <img src="/static/find.png" title="Suchen" />
  <img src="/static/logout.png" title="Abmelden" />
  <br/>
</div>
