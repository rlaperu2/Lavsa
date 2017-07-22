<%-- 
    Document   : editarCliente
    Created on : 12/01/2015, 02:24:36 PM
    Author     : salegria
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EUREKA - ${accion} CLIENTE</title>
</head>
<body>
  <form name="formEditar" id="formEditar">
  
    <input type="hidden" name="accion" value="${accion}" />
    <input type="hidden" name="codigo" value="${bean.codigo}" />
  
    <h1>${accion} CLIENTE</h1>
    <table>

        <tr>
              <td>Código</td>
              <td>${bean.c_c_cliente}</td>
        </tr>
        <tr>
              <td>Razon Social</td>
              <td><input type="text" name="c_t_cliente" value="${bean.c_t_cliente}"/></td>
        </tr>
        <tr>
              <td>R.U.C.:</td>
              <td><input type="text" name="c_c_ruc" value="${bean.c_c_ruc}"/></td>
        </tr>

    </table>
    
    <input id="btnClienteGrabar" class="botonAceptar" 
           type="button" value="Grabar"/>
  </form>
</body>
<script type="text/javascript">

  $("#btnClienteGrabar").click(function(){
	  var data = $("#formEditar").serialize();
	  $("#_WORK2").load("ClienteGrabar", data);
  })

</script>
</html>