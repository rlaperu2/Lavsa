<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <link rel="stylesheet" href="menu/menu.css" type="text/css" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Importaciones Lavsa S.A.</title>
</head> 
<body>
<div class="_LISTADO">
<h1>Resultado</h1>
<table    border="1">
    <tr>
            <th>Codigo</th>
            <th>Descripción</th>
            <!--<th>Unidad</th>-->
            <th></th>
            <th>Precio</th>
            <!--<th>Stock</th>-->
    </tr>
    <c:set var="familia" value=""></c:set>  
    <c:forEach  items="${requestScope.lista}" var="r">

    <tr> 
 
                <td>
                    <table    border="0">
                        <tr>
                            <td>
                                <table  border="0">
                                    <tr> 
                                        <td>
                                            <a href='javascript: nuevodetPedidox("${requestScope.pedido}","${r.c_c_articulo}"  )'>
                                                <img alt="" src="img/seleccionar.png" />
                                            </a>
                                        </td>
                                    </tr>                         
                                    <tr> 
                                        <td>
                                            <c:if test="${r.n_fl_archivo_grafico == 1}">
                                     
                                            <a href="verimagen.jsp?dato1=${r.c_c_articulo}" target="_blank" >   
                                                <img alt="" src="img/imagen.png" />
                                            </a>
                                            </c:if> 
                                        </td>    
                                    </tr> 
                                </table>    
                            </td>            
                        </tr>
                        <tr> 
                            <td>${r.c_c_articulo}</td>
                        </tr>
                    </table> 
                
                </td>
                <td>${r.c_t_articulo}</td>
                <td>${r.moneda}</td>  
                <td>${r.precio}</td>  
                
    </tr>
   </c:forEach>
</table>
</div>
</body>


<script type="text/javascript">
    
  
    
 	function nuevodetPedidox(pedidox,articulox  ) {
          //  alert(pedidox);
           // alert(articulox);
           // codx='';
            //codx=articulox;
         
            var data="c_c_pedido=" + pedidox + "&c_c_articulo="+articulox ;
            $("#_WORK").load("DetPedidoNuevo",data);
        }    
    
        function vergrafico(codigox) {
             //alert('llego');
             window.open("verimagen.jsp");   // Opens a new window
            //myWindow.document.write("<p>This is 'myWindow'</p>");   // Text in the new window
            //myWindow.opener.document.write("<img alt='' src='/imagenes/"+codigox+".jpg'/>"); 
		 
	}
        function addslashes(string) {
            alert('aaaa');
            return string.replace(/\\/g, '\\\\').
                replace(/\u0008/g, '\\b').
                replace(/\t/g, '\\t').
                replace(/\n/g, '\\n').
                replace(/\f/g, '\\f').
                replace(/\r/g, '\\r').
                replace(/'/g, '\u00F3').
                replace(/"/g, '\u00F3');
        }
</script>

</html>