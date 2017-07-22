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
                        <h1>Pedidos</h1>
                        <table>
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <td>
                                                <label>Desde</label><input type="text" name="desdex" size="12" value="<%=currentDate%>" id="datepicker"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>Hasta</label><input type="text" name="hastax" size="12" value="<%=currentDate%>" id="datepicker2"/>                        
                                            </td>
                                        </tr>    
                                    </table>    
                                </td> 
                                <td>
                                    <a href="javascript: enviarFormulario();">
                                        <img width="50" alt="" src="img/search-24.png" />
                                    </a>  
<!--                                     escogerCliente.jsp-->
                                    <a href= "javascript: nuevoPedido();">
                                        <img width="50" alt="" src="img/Add.png" />
                                    </a>
                                     
                                </td>    
                            </tr>        
                        </table>     
                            
                            <!--                        <label>Año</label> <input type="text" name="aniox" size="5" value="2015"/> 
                        <select name="mesx"  >
                            <option value="1">Enero</option>>
                            <option value="2">Febrero</option>>
                            <option value="3">Marzo</option>>
                            <option value="4">Abril</option>>
                            <option value="5">Mayo</option>>
                            <option value="6">Junio</option>>
                            <option value="7">Julio</option>>
                            <option value="8">Agosto</option>>
                            <option value="9" selected>Septiembre</option>>
                            <option value="10">Octubre</option>>
                            <option value="11">Noviembre</option>>
                            <option value="12">Diciembre</option>>
                        </select>     -->
  


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
            if  ((gfc_comprobar_fecha(document.form1.desdex.value) == 1) &&  (gfc_comprobar_fecha(document.form1.hastax.value) == 1))
            {
                    var data = $("#form1").serialize();
                    global_JQuery_html("#", "_LISTADO", global_generarHTMLImagenLoad());
                    $("#_LISTADO").load("PedidoTraerPorFechas", data);
            }            
        } 
        
        function busqCliente() {
            $("#_WORK").load("escogerCliente.jsp");
            
             
        }
        
        
        function nuevoPedido() {
             $("#_WORK").load("CrearPedido");
        }
    	function searchArticulos() {
		 var data = $("#form1").serialize();
		 $("#_LISTADO").load("ArticuloTraerPorNombre", data);
	}
    
//        function agregarPedido(codigox,descripx,rucx,pedidox) {
//            
//            alert(pedidox);
//            alert("sss1");
//		var data = "c_c_cliente=" + codigox+"&c_t_cliente="+descripx+"&c_c_ruc="+rucx;
//               
//		$("#_WORK").load("CrearPedido",data);
//	}

 


        function modificarPedido(xc_c_pedido) {
                global_JQuery_html("#", "_WORK", global_generarHTMLImagenLoad());
		var data = "c_c_pedido=" + xc_c_pedido ;
		$("#_WORK").load("ModificarPedido",data);
	}

        function anularPedido(xc_c_pedido) {
            if(confirm('¿Estas seguro de ANULAR PEDIDO. ?'))
            {
                global_JQuery_html("#", "_LISTADO", global_generarHTMLImagenLoad());
		var data = "c_c_pedido=" + xc_c_pedido ;
		$("#_LISTADO").load("AnularPedido",data);
            }    
	}
         
        


    </script>

 
    <script>
    $(function () {
       //Agregamos el "Idioma" Español con las traducciones que deseemos y el formato que queramos que tenga la fecha.
       $.datepicker.regional['es'] = {
           closeText: 'Cerrar',
           prevText: '<Ant',
           nextText: 'Sig>',
           currentText: 'Hoy',
           monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
           monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
           dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'S&aacute;bado'],
           dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mi&eacute;', 'Juv', 'Vie', 'S&aacute;b'],
           dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S&aacute;'],
           weekHeader: 'Sm',
           dateFormat: 'dd/mm/yy',
           firstDay: 1,
           isRTL: false,
           showMonthAfterYear: false,
           yearSuffix: ''
     };
       //Indicamos que por defecto coja el idioma es(Español)
      $.datepicker.setDefaults($.datepicker.regional['es']);
      
      
      
//   //Agregamos el "Idioma" Español con las traducciones que deseemos y el formato que queramos que tenga la fecha.
//       $.datepicker2.regional['es'] = {
//           closeText: 'Cerrar',
//           prevText: '<Ant',
//           nextText: 'Sig>',
//           currentText: 'Hoy',
//           monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
//           monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
//           dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'S&aacute;bado'],
//           dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mi&eacute;', 'Juv', 'Vie', 'S&aacute;b'],
//           dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S&aacute;'],
//           weekHeader: 'Sm',
//           dateFormat: 'dd/mm/yy',
//           firstDay: 1,
//           isRTL: false,
//           showMonthAfterYear: false,
//           yearSuffix: ''
//     };
//       //Indicamos que por defecto coja el idioma es(Español)
//      $.datepicker2.setDefaults($.datepicker2.regional['es']);
      
    });

   $(function () {
       //Declaramos que todos los IDs que se llamen datepicker(id="datepicker") se conviertan en el Objeto Datepicker
       // y le indicamos que se aparezca una lista con los meses y otra con los años.
        $("#datepicker").datepicker({
          
           changeMonth: true,
          changeYear: true,
            numberOfMonths: 1
       });
       
        $("#datepicker2").datepicker({
          
           changeMonth: true,
          changeYear: true,
            numberOfMonths: 1
       });       
       
       
   });
    </script>

</html>