<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Verificar la sesión del usuario -->
<c:if test="${sessionScope.usuario == null}">
    <jsp:forward page="index.jsp"/>
</c:if>    

<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
  
<%
   Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("dd/MM/yyyy");
   String currentDate = ft.format(dNow);
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <link rel="stylesheet" href="menu/menu.css" type="text/css" />
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        
 
        <title>Importaciones Lavsa S.A.</title>
    </head> 
 
    
 
    <body>
        <div class="contenedor">
            <div class="_HEAD"><jsp:include page="util/head.jsp"/></div>
            <div class="_MENU"><jsp:include page="menu/menu.jsp"/></div>
            <div id="_WORK" class="_WORK">
                <form id="form1" name="form1">
                        <h1>Cuentas por Cobrar</h1>
                        <table>
                            <tr>
                                <td>
                                    <label>Razon Social:</label><input type="text" name="xc_t_cliente" size="18" value="" id="xc_t_cliente"/>
                                    <br>
                                    <label>Codigo:</label><input name="xc_c_cliente" id="xc_c_cliente" size="5" value="">
                                </td> 
                                <td>
                                    <a href="javascript: enviarFormulario();">
                                        <img width="50" alt="" src="img/search-24.png" />
                                    </a>  
 
                                </td>    
                            </tr>        
                        </table>     
                          
                    </form>

                <div id="_LISTADO"></div>

            </div>
            <div class="_FOOTER">
                
            </div>
        </div>
    </body>
 

    
    <script type="text/javascript" src="jquery/gfc_librerias.js"></script>
    <script type="text/javascript">
        function enviarFormulario() {
             
                    var data = $("#form1").serialize();
                    global_JQuery_html("#", "_LISTADO", global_generarHTMLImagenLoad());
                    $("#_LISTADO").load("CtaxCobrar", data);
                         
        } 
    
    </script>

    
<!--   Buscar Clientes -->
  <script>
  $(function() {
    
    var availableTags = new Array(); 
     $("#xc_t_cliente").bind("keyup",function(event){
        var data = {nombre:$("#xc_t_cliente").val()};
         $.getJSON("buscar",data,function(res,est,jqXHR){
            availableTags.length = 0;
            $.each(res, function(i , item){
                if  (item[i,1] != null){
                    availableTags[i]={value: item[i,1]  ,  label: item[i,0]} ;
               }
             });
        });
    });
    
    $("#xc_t_cliente" ).autocomplete({
                source: availableTags,
                minLength : 3 ,
                select:function(event, ui){
                   event.preventDefault();
                   $(this).val(ui.item.label);
                   $("#xc_c_cliente").val(ui.item.value);

                }
    });
  });
  </script>
   

</html>