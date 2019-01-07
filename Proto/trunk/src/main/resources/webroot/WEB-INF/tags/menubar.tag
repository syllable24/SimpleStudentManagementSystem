<%-- 
   <menubar>
   display the menuetree
--%>
<%@ attribute name="menue" %>
<DIV style="width:100%; height: 5em; margin-bottom: -0.5em; position:relative">
<ul id="menu" onclick='menuSelect(event);' >
 <li class='color1' id='A' ><a>Bewegungen</a>
   <ul class="sub">
     <li id="A1">Requests<img src="/static/select.png"   >
		  <ul class="sub1">
			<li id="A1A">Schnellerf.</li>
			<li id="A1B">Freigabe</li>
			<li id="A1C">Dokumente</li>
		  </ul>     </li>
     <li id="A2">Projects<img src="/static/select.png"   >
		  <ul class="sub1">
			<li id="A2A">Teambesprechung</li>
			<li id="A2B">Strukturplan</li>
			<li id="A2C">Milestones</li>
			<li id="A2D">Abrechnung</li>
		  </ul>     </li>
     <li id="A3">Aufträge<img src="/static/select.png"   >
		  <ul class="sub1">
			<li id="A31">Ereignis</li>
			<li id="A32">Rollen</li>
			<li id="A33">Regeln</li>
			<li id="A34">Tätigkeiten</li>
			<li id="A35">Artefakte</li>
		  </ul>     </li>
     <li id="A4">Tasks<img src="/static/select.png"   >
		  <ul class="sub1">
			<li id="A4A">Tasklist</li>
			<li id="A4B">Planwoche</li>
			<li id="A4C">Telefon</li>
			<li id="A4D">Eskalation</li>
		  </ul>     </li>
     <li id="A5">Works     </li>
   </ul>
</li>
 <li class='color2' id='B' ><a>Auswertungen</a>
   <ul class="sub">
     <li id="B1">Taskday     </li>
     <li id="B2">Leistungserfassung     </li>
     <li id="">TaskReport     </li>
     <li id="B4">Projekte     </li>
     <li id="">Requests     </li>
     <li id="B2">Leistungserfassung     </li>
     <li id="AN1">Erfassung     </li>
     <li id="B8">Lizenzen     </li>
   </ul>
</li>
 <li class='color3' id='C' ><a>Parameter</a>
   <ul class="sub">
     <li id="C1">Arbeitsbereiche     </li>
     <li id="C2">States     </li>
     <li id="C3">Leistungsarten     </li>
     <li id="C4">Priorities     </li>
     <li id="C5">Hersteller     </li>
     <li id="UPLOAD">Upload Dateien     </li>
   </ul>
</li>
 <li class='color4' id='D' ><a>Stammdaten</a>
   <ul class="sub">
     <li id="D5B">Specifications     </li>
     <li id="">Configurations     </li>
     <li id="D3">Locations     </li>
     <li id="D4">Resources     </li>
     <li id="D5">Products<img src="/static/select.png"   >
		  <ul class="sub1">
			<li id="D5A">ResourceAttribute</li>
			<li id="D5B">Specifications</li>
			<li id="D5C">Resourcetypes</li>
		  </ul>     </li>
     <li id="">Etiketten     </li>
     <li id="">Schulungen     </li>
     <li id="CO">Connections     </li>
     <li id="D9">Lieferanten<img src="/static/select.png"   >
		  <ul class="sub1">
			<li id="D91">Lieferantenpreise</li>
		  </ul>     </li>
     <li id="D10">Organisationen<img src="/static/select.png"   >
		  <ul class="sub1">
			<li id="D10A">Mitarbeiterkont.</li>
			<li id="D10B">Personen</li>
			<li id="D10C">Adressen</li>
		  </ul>     </li>
     <li id="D6">Lookups     </li>
   </ul>
</li>
 <li class='color5' id='E' ><a>Benutzer</a>
   <ul class="sub">
     <li id="E1">AD-User     </li>
     <li id="E2">Zugriffschlüssel     </li>
     <li id="E3">Gruppenverwaltung     </li>
     <li id="E4">User     </li>
     <li id="E5">Menues     </li>
     <li id="E6">Benutzerverwaltung     </li>
   </ul>
</li>
  </ul>
</DIV>