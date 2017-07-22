<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Verificar la sesión del usuario -->
<c:if test="${sessionScope.usuario == null}">
  <jsp:forward page="index.jsp"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <link rel="stylesheet" href="menu/menu.css" type="text/css" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Importaciones Lavsa</title>
</head>
<body>
<div class="_PAGE">
  <div class="_HEAD"><jsp:include page="util/head.jsp"/></div>
  <div class="_MENU"><jsp:include page="menu/menu.jsp"/></div>
  <div class="_WORK" id="_WORK2"><br/><br/><br/><br/></div>
  <div class="_FOOTER">
   
  </div>
</div>
</body>

<script type="text/javascript">

  function fnReporteClientes1(){
		  var html;
		  html = "<object data='ReporteClientes1' type='application/pdf' "
			  + "width='880px' height='600px'></object>";
		  $("#_WORK").html(html);
  }

  function fnReporteClientes2(){
		  var html;
		  html = "<object data='ReporteClientes2' type='application/pdf' "
			  + "width='880px' height='600px'></object>";
		  $("#_WORK").html(html);
  }

</script>
</html>