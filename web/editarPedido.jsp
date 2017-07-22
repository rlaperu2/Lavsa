<%-- 
    Document   : samueltipo
    Created on : 02/03/2015, 05:03:18 PM
    Author     : salegria
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!--<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">-->
<!--  <link rel="stylesheet" href="/resources/demos/style.css">-->
<!--  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->
<script>
    $(function () {

        var availableTags = new Array();
        $("#xc_t_cliente").bind("keyup", function (event) {
            var data = {nombre: $("#xc_t_cliente").val()};
            $.getJSON("buscar", data, function (res, est, jqXHR) {
                availableTags.length = 0;
                $.each(res, function (i, item) {
                    if (item[i, 1] != null) {
                        availableTags[i] = {value: item[i, 1], label: item[i, 0]};
                    }
                });
            });
        });

        $("#xc_t_cliente").autocomplete({
            source: availableTags,
            minLength: 3,
            select: function (event, ui) {
                event.preventDefault();
                $(this).val(ui.item.label);
                $("#xc_c_cliente").val(ui.item.value);
                $.getJSON("buscar_cliente_lugares", "codcliente=" + ui.item.value,
                        function (res2, est, jqXHR)
                        {

                            document.getElementById("xc_c_lugar").options.length = 0;

                            select = document.getElementById('xc_c_lugar');

                            $.each(res2, function (i, item) {
                                if (item[i, 1] != null) {
                                    var opt = document.createElement('option');
                                    opt.value = item[i, 1];
                                    opt.innerHTML = item[i, 0];
                                    select.appendChild(opt);
                                }
                                //if  (item[i,1] != null){
                                //availableTags[i]={value: item[i,1]  ,  label: item[i,0]} ;
                                //document.getElementById("xc_c_lugar").options[i]="a";
                                //}
                            });





                        }

                );
            }
        });
    });
</script>




<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="form2" name="form2">
            
       <br> 
            <label  style="color:red;">${mensajeSistema}</label>
            <br> 
            <label>Pedido:</label>
            <input type="text" disabled name="xc_n_pedido" value="${bean.c_n_pedido}" size="8"/>
             
            <input <c:if test="${accion=='EDITAR'}">hidden="true"</c:if>     type="button" value="Agregar Cliente" onclick="enviarFormularioxyx()" /> 
  
            
            <br> 
            <label>Codigo</label>
            <input type="text" readonly="true"  id="xc_c_cliente"  name="xc_c_cliente" value="${bean.c_c_cliente}" size="5"/>
            <a href="javascript:buscarCliente();"><img  width="20" alt="" src="img/search-24y.png"/></a>


            <label>RUC</label>
            <input type="text"  name="xc_c_ruc" value="${bean.c_c_ruc}" size="11"/>
            <br>

            <label>Razon Social</label>
            <br>
            <!--            disabled-->
            <input type="text"  id="xc_t_cliente" name="xc_t_cliente" value="${bean.c_t_cliente}" size="35"/>

            <br>
            <label>Forma de Pago:</label>
            <br>
            <select name="xc_c_tipo_pago"  style="width: 280px" >
                <c:forEach  items="${requestScope.lista}" var="r">                  
                    <option  
                        value="${r.c_c_tipo_pago}"

                        <c:if test="${r.c_c_tipo_pago==bean.c_c_tipo_pago}">  
                            selected
                        </c:if>      
                        >${r.c_t_tipo_pago}</option>
                </c:forEach>

            </select>

            <br>
            <label>Fecha Entrega:</label>
            <input  name="xd_dt_entrega" type="text" id="datepicker" size="12"  value="${bean.d_dt_entrega}">
            <br>
            <label>Moneda:</label>
            <select name="xc_c_moneda"  style="width: 60px" >
                <option value="01" <c:if test="${bean.c_c_moneda=='01'}">  selected </c:if>  >S/.</option> 
                <option value="02" <c:if test="${bean.c_c_moneda=='02'}">  selected </c:if>  >US$</option> 
                </select>
                </br>

                <label>Lugar de Entrega:</label>
                </br>
                <select id="xc_c_lugar" name="xc_c_lugar"  style="width: 280px" >
                <c:forEach  items="${requestScope.lista2}" var="r2">                  
                    <option value="${r2.c_c_lugar}"

                            <c:if test="${r2.c_c_lugar==bean.c_c_lugar}">  
                                selected
                            </c:if>  


                            >${r2.c_t_lugar}</option>>
                </c:forEach>
            </select>
            </br>

            <label>Observacion:</label>
            </br>
            <input  name="xc_t_observacion" type="text"  value="${bean.c_t_observacion}"   size="35">
            </br>            

            <!--            <input type="button" value="Grabar" id="btngrabar" class="botonaceptar" >-->
            <a href="javascript:grabarelpedido();"><img  width="50" alt="" src="img/grabar.png"/></a>
            <a href="mantPedidos.jsp">
                <img  width="50" alt="" src="img/salir.png" />
            </a>
            <div id="_esperar"></div>
                 
            <input type="hidden"  name="accion" value="${accion}" />
            <input type="hidden"  name="xc_c_pedido" value="${bean.c_c_pedido}" />
           
        </form>
    </body>



    <script type="text/javascript" src="jquery/gfc_librerias.js"></script>
    <script type="text/javascript">


                function enviarFormularioxyx() {

                    var ls_buscar = document.form2.xc_c_ruc.value;
                    if (ls_buscar.length !== 11)
                    {
                        alert("El ruc debe ser de 11 caracteres");
                        return;
                    }
                    ls_buscar = document.form2.xc_t_cliente.value;
                    if (ls_buscar.length === 0)
                    {
                        alert("Debe de ingresar la Razon Social");
                        return;
                    }
                    var data = $("#form2").serialize();
                    $("#_WORK").load("CrearCliente", data);
//            else
//            {
//            
//		 var data = $("#form1").serialize();
//                 global_JQuery_html("#", "_LISTADO", global_generarHTMLImagenLoad());
//		 $("#_LISTADO").load("ArtTraerparaListado", data);
//             }     
                }



                function buscarCliente()
                {
                    var data = $("#form2").serialize();
                    $("#_WORK").load("escogerCliente", data);
                }

                function grabarelpedido()
                {


                    if (gfc_comprobar_fecha(document.form2.xd_dt_entrega.value) == 1) {
                        var data = $("#form2").serialize();
                        global_JQuery_html("#", "_esperar", global_generarHTMLImagenLoad());
                        $("#_WORK").load("PedidoGrabar", data);
                    }

                }

                $("#btngrabar").click(function () {
                    var data = $("#form2").serialize();
                    global_JQuery_html("#", "_esperar", global_generarHTMLImagenLoad());
                    $("#_WORK").load("PedidoGrabar", data);
                });


                //samuel nuevo agregado    

                function global_JQuery_html(pmtTipoIdentificador, pmtDescripcionIdentificador, html)
                {
                    $("#" + pmtDescripcionIdentificador).html("");
                    $("#" + pmtDescripcionIdentificador).empty().html(html);
                }

                function global_generarHTMLImagenLoad()
                {
                    //alert("ingreso");
                    var cadena;
                    cadena = '<img src="' + devolverContextPath() + 'img/cargando.gif "/>';
                    // alert(cadena+"ingreso 1")
                    return cadena;
                }

                function devolverContextPath()
                {
                    return $("#mycontextpath").text();
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
