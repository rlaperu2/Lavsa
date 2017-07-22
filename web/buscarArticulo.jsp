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
 
</head> 
<body>
 
 
	<div id="_HEAD2" >
		<form id="form1" name="form1">
                        <br>
                        Buscar articulox :
                        <br>
                        <input type="hidden" name="pedido" value="${pedidox}"/>
			<input type="text" name="nombre"/> 
			<a href="javascript: buscarxArticulo();">
			    <img alt="" src="img/search-24.png" />
			</a> 
                        <a border="2" width="50" href="javascript:modificarPedido('${pedidox}')"><img alt="" src="img/salir.png"/></a>
		</form>
	</div>
	<div id="_LISTADO" > 
        </div>
</div>
</body>
 
<script type="text/javascript">
    
    
      
        function buscarxArticulo() {
             
            var ls_buscar = document.form1.nombre.value ;
            if (ls_buscar.length < 3 )
            {
                alert("Debe de ingresar por lo menos 3 caracteres");
                
            }    
            else
            {
		 var data = $("#form1").serialize();
                 global_JQuery_html("#", "_LISTADO", global_generarHTMLImagenLoad());
		 $("#_LISTADO").load("ArticuloTraerPorNombre", data);
             }     
	}

 

//samuel nuevo agregado    




</script>
</html>