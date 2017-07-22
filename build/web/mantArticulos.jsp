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
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <title>Importaciones Lavsa S.A.</title>
</head> 
<body>
<div class="_PAGE">
 <div class="_HEAD"><jsp:include page="util/head.jsp"/></div>
  <div class="_MENU"><jsp:include page="menu/menu.jsp"/></div>
  <div class="_WORK">
   	<div id="_HEAD2" >
		<form id="form1" name="form1"  >
			<h1>ARTICULOS</h1>
                        <br>
			<label>Buscar Articulos:</label>  
			<br>
                        <input type="text" name="nombre" /> 
<!--                        <br>
                        <label>Buscar por nombre de articulo:</label><br /> 
			<input type="text" name="nombre2" /> -->
<!--			<a href="javascript: enviarFormulario();">
                            <img border="2" alt="" src="img/search-24.png" />
			</a> -->
                        <br/>
                        <input type="button" value="Buscar" onclick="enviarFormularioxyx()" /> 
                        <br/> 
			 
		</form>
	</div>
	 
 	<div  id="_LISTADO"></div>
 	  
  </div>
  <div class="_FOOTER">
     
  </div>
</div>
</body>


<script type="text/javascript">
    
    
    
	function enviarFormularioxyx() {
            var ls_buscar = document.form1.nombre.value ;
            if (ls_buscar.length < 3   )
            {
                alert("Debe de ingresar por lo menos 3 caracteres");
                
            }    
            else
            {
            
		 var data = $("#form1").serialize();
                 global_JQuery_html("#", "_LISTADO", global_generarHTMLImagenLoad());
		 $("#_LISTADO").load("ArtTraerparaListado", data);
             }     
	}
 
 
       //samuel nuevo agregado    

        function global_JQuery_html(pmtTipoIdentificador, pmtDescripcionIdentificador, html)
        {
            $("#"+pmtDescripcionIdentificador).html("");
            $("#"+pmtDescripcionIdentificador).empty().html(html);
        }

        function global_generarHTMLImagenLoad()
        {
            //alert("ingreso");
            var cadena;
            cadena = '<img src="'+devolverContextPath()+'img/cargando.gif "/>';
            // alert(cadena+"ingreso 1")
            return cadena;
        }

        function devolverContextPath()
        {
            return $("#mycontextpath").text();
        }
 
</script>

</html>