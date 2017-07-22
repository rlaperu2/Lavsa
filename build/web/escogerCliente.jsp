 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Verificar la sesión del usuario -->
<c:if test="${sessionScope.usuario == null}">
    <jsp:forward page="index.jsp"/>
</c:if>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">

    <body>
        <!--<div class="_PAGE">-->
<!--            <div class="_HEAD"><jsp:include page="util/head.jsp"/></div>
            <div class="_MENU"><jsp:include page="menu/menu.jsp"/></div>-->
<!--            <div id="_WORK" class="_WORK">-->
                <form name="formEditar" id="formEditar">
                    <input type="hidden"  name="xc_c_pedido" value="${pedidox}" />                    
                    <input type="hidden" name="accion" value="${accion}" />
                    <br>
                        Buscar cliente : 
                    <br>
                        <input type="text" name="tipo">
                                <a href="javascript: enviarForm();">
                                    <img  width="50" alt="" src="img/search-24.png" />
                                </a>
                                <a href="mantPedidos.jsp">
                                    <img  width="50" alt="" src="img/salir.png" />
                                </a>
                    <br>
                </form>
                <div  id="_LISTADO"></div>
            <!--</div>-->    
        <!--</div>--> 
    </body>
    
    <script type="text/javascript" src="jquery/gfc_librerias.js"></script>
    <script type="text/javascript">
        function enviarForm() {
             
            var ls_buscar = document.formEditar.tipo.value ;
            if (ls_buscar.length < 3 )
            {
                alert("Debe de ingresar por lo menos 3 caracteres");
                
            }    
            else
            {
              
                 var data = $("#formEditar").serialize();
                
                  global_JQuery_html("#", "_LISTADO", global_generarHTMLImagenLoad());
                  //global_JQuery_html("#", "_LIST2", global_generarHTMLImagenLoad());
               
                 $("#_LISTADO").load("Clientenombre",data);
             }     
                 
        }
        function editarCliente(codigo) {
          
		var data = "codigo=" + codigo;
		$("#_WORK").load("PedidoEditar",data);
	}
        function agregarCliente() {
          
		//var data = "codigo=" + codigo;
		$("#_WORK").load("AgregarCliente" );
	}        
        function goPedido(codigox,descripx,rucx,pedidox) {
          
		var data = "c_c_cliente=" + codigox+"&c_t_cliente="+descripx+"&c_c_ruc="+rucx+"&c_c_pedido="+pedidox;
                if (pedidox.length >0 ){
                    $("#_WORK").load("ModificarCabPedido",data);
                }    
                else
                {
                    $("#_WORK").load("CrearPedido",data);
                }    
		    
	}
        
        
  
        
        
    </script>
     
      <script src="//code.jquery.com/jquery-1.10.2.js"></script>   
    <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>   
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
    });

   $(function () {
       //Declaramos que todos los IDs que se llamen datepicker(id="datepicker") se conviertan en el Objeto Datepicker
       // y le indicamos que se aparezca una lista con los meses y otra con los años.
        $("#datepicker").datepicker({
          
           changeMonth: true,
          changeYear: true,
            numberOfMonths: 1
       });
   });
    </script>
   
</html>