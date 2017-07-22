<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <link rel="stylesheet" href="menu/menu.css" type="text/css" />
     <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        
    <title>Importaciones Lavsa S.A.</title>
</head> 
<body>
<div class="contenedor" id="_LISTADO">
    
<!--   <input type="text" name="desdex" size="12" value="${requestScope.desdex}"  />
 
    <input type="text" name="hastax" size="12" value="${requestScope.hastax}"  />                        -->
                                                
    
<h1>Resultado</h1>
<table    border="1">
   <tr>
   <th width="20%">
       
        Pedido/Fecha 
      
   </th>
   <th width="40%">Cliente</th>

   <th width="15%">Importe</th>
   <th width="15%">Accion</th>
   </tr>
   <c:forEach  items="${requestScope.lista}" var="r">
    	    <tr>
                
                <td>
                 <table> 
                  <tr>
                    <td>${r.c_n_pedido}</td>
                  </tr>
                  <tr>
                    <td>${r.d_dt_doc}</td>
                  </tr>
                 </table> 
                </td>
                
                
                <td>
                    <table>
                        <tr><td>${r.c_t_cliente}</td></tr> 
                        <tr><td>Enviado:${r.enviado} | Facturado:${r.c_c_facturado} | ${r.c_t_anulado}</td></tr>   
                    </table>  
                        
                
                </td>
                <td>
                  <table>
                    <tr><td>${r.c_t_moneda_abre}</td></tr> 
                    <tr><td>${r.n_i_total}</td></tr>   
                  </table>  
                </td>
                <td style="text-align: center;">
                    <a href="javascript:modificarPedido('${r.c_c_pedido}')"><img alt="" src="img/edit.png"/></a>
                    <c:if test="${r.c_c_facturado=='No'&&r.c_t_anulado==''}">
                        <a href="javascript:anularPedido('${r.c_c_pedido}')"><img alt="" src="img/delete-file.png"/></a>
                    </c:if>          
                </td>    
            </tr>
   </c:forEach>
</table>
</div>
 
    
</body>
</html>