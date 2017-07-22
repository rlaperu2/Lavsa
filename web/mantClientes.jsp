<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Verificar la sesión del usuario -->
<c:if test="${sessionScope.usuario == null}">
	<jsp:forward page="index.jsp" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estilos.css">
<link rel="stylesheet" href="menu/menu.css" type="text/css" />
<title>Importaciones Lavsa S.A.</title>
</head>
<body>
	<div class="_PAGE">
		<div class="_HEAD"><jsp:include page="util/head.jsp" /></div>
		<div class="_MENU"><jsp:include page="menu/menu.jsp" /></div>
		<div class="_WORK">
			<div id="_HEAD2" style="background-color: #FAFAFA;">
				<form id="form1" name="form1">
					<h1>CLIENTES</h1>
                                        
                            <table>
                            <tr>
                                <td>
					<label>Buscar cliente:</label><br /> 
					<input type="text" name="nombre" /> 
                                </td> 
                                <td>
					<a href="javascript: enviarFormulario();">
					  <img width="50" alt="" src="img/search-24.png" />
					</a> 
					<a href="javascript: nuevoCliente();">
					   <img width="50" alt="" src="img/Add.png" />
					</a>
                                </td>
                            </tr>   
                            </table>            
                                        
				</form>
			</div>
			<div id="_WORK2"></div>
		</div>
		 
	</div>
</body>

<script type="text/javascript">
	function enviarFormulario() {
		var   data = $("#form1").serialize();
		$("#_WORK2").load("ClienteTraerPorNombre", data);
                
	}
	function nuevoCliente() {
		$("#_WORK2").load("ClienteNuevo");
	}	
	function editarCliente(codigo) {
		var data = "codigo=" + codigo;
		$("#_WORK2").load("ClienteEditar",data);
	}
        function eliminarcli(codigo){
            var  data="codigo"+codigo;
            $("#_WORK2").load("eliminarcliente")
            
        }
	
</script>
</html>