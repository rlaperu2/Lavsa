<%-- 
    Document   : samueltipo
    Created on : 02/03/2015, 05:03:18 PM
    Author     : salegria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="form2" name="form2">
            <input type="hidden" name="accion" value="${accion}" />

            <table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <fieldset >
                            <legend>Pedido</legend>

                            Codigo:
                            <input size="5" disabled type="text" name="c_c_cliente" value="${bean.c_c_cliente}"  />
                            RUC: 
                            <input size="10" disabled type="text" name="c_c_ruc" value="${bean.c_c_ruc}"  />


                            <br>
                            Cliente:
                            <input size="35" disabled type="text" name="c_t_cliente" value="${bean.c_t_cliente}"  />


                            <br>
                            <label>Fecha de Pedido:</label>
                            <input size="8" disabled type="text" name="d_dt_doc" value="${bean.d_dt_doc}"  />
                            <br>
                            <label>Fecha de Entrega:</label>
                            <input size="8" disabled type="text" name="d_dt_entrega" value="${bean.d_dt_entrega}"  />
                            <br>
                            <label>Total:</label>
                            <input size="4" disabled type="text" name="n_i_total" value="${bean.n_i_total}"  /> 
                            <input size="4" disabled type="text" name="c_t_moneda_abre" value="${bean.c_t_moneda_abre}"  /> 
                            <label>Facturado</label> 
                            <input size="2" disabled type="text" name="c_c_facturado" value="${bean.c_c_facturado}"  /> 
                            <label  class="label_resaltado" id="label_resaltado">${bean.c_t_anulado}</label>

                                                        <label>Enviado:</label> 
                            <input size="3" disabled type="text" name="enviado" value="${bean.enviado}"  /> 
                            
                        </fieldset>
                    </td>
                    <td>
                        <table>
                            
                        <c:if test="${bean.c_c_facturado=='No'&&bean.c_t_anulado==''}">      
                            <tr>
                                <td>
                                    <a href="javascript:modificarCabPedido('${bean.c_c_pedido}')"><img width="40" alt="" src="img/edit.png"/></a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="javascript:enviar('${bean.c_c_pedido}')"><img width="40" alt="" src="img/seleccionar.png"/></a>
                                </td>
                            </tr>        
                        </c:if>        
                            <tr>
                                <td>
                                    <a href="mantPedidos.jsp"><img width="50" alt="" src="img/salir.png"/></a>
                                </td>
                            </tr>    
                            
                                
                            
                            
                        </table>
                    </td>   
                </tr>
            </table>
            <!--<fieldset>-->        
            <!--<legend>Detalle de Pedido</legend>-->
            <table   width="100%" align="left"  border="1">
                <tr>
                    <th hidden>Secuencia</th>
                    <!--<th width="20px">Codigo</th>-->
                    <th width="52%">Producto</th>
                    <th width="12%">Cant.</th>
                    <th width="12%">Precio</th>
                    <th width="12%">%Dsctos</th>
                    <th width="12%">
                        <c:if test="${bean.c_c_facturado=='No'&&bean.c_t_anulado==''}">  
                            <a href="javascript: buscarArticulo(${bean.c_c_pedido});">
                                <img alt="" src="img/Add.png" />
                            </a> 
                        </c:if>      
                        <!--                                  <a href= "mantArticulos.jsp&c_c_pedido=1">
                                                            <img alt="" src="img/Add.png" />
                                                        </a>     -->

                    </th>
                </tr>
                <c:forEach  items="${requestScope.lista}" var="r">
                    <tr>
                        <td hidden>${r.n_i_secuencia}</td> 
                        <!--<td>${r.c_c_articulo}</td>-->
                        <td>${r.c_t_articulo}</td>
                        <td>${r.n_i_cantidad}</td>
                        <td>${r.n_i_precio}</td>
                        <td>
                            <table>
                                <tr>
                                    <td>${r.n_i_porc_dscto_1}</td>
                                </tr>
                                <tr>
                                    <td>${r.n_i_porc_dscto_2}</td>
                                </tr>
                                <tr>
                                    <td>${r.n_i_porc_dscto_3}</td>
                                </tr>
                                <tr>
                                    <td>${r.n_i_porc_dscto_4}</td>
                                </tr>
                            </table>    
                        </td>    
                        <!--                             <td>
                                                       <table>
                                                        <tr><td>Cantidad:</td></tr>  
                                                        <tr><td>${r.n_i_cantidad}</td></tr>
                                                        <tr><td>Precio:</td></tr>  
                                                        <tr><td>${r.n_i_precio}</td></tr>  
                                                        <tr><td>Total:</td></tr>  
                                                        <tr><td>${r.n_i_total}</td></tr>   
                                                      </table>   
                                                     </td>   -->
                        <td style="text-align: center;}">
                            <c:if test="${bean.c_c_facturado=='No'&&bean.c_t_anulado==''}">
                                <a href="javascript:modificarDetPedido('${r.c_c_pedido}', '${r.c_c_articulo}' ,${r.n_i_secuencia})"><img alt="" src="img/edit.png"/></a>
                                <a href="javascript:eliminarDetPedido('${r.c_c_pedido}', '${r.c_c_articulo}' ,${r.n_i_secuencia})"><img alt="" src="img/delete-file.png"/></a>
                                </c:if> 
                        </td>    
                    </tr>
                </c:forEach>



            </table>
            <br>
            <br>

            <!--<input onclick="window.open('mantPedidos.jsp','_self')" type="button" value="Salir" />--> 


            <!--</fieldset>-->
        </form>
        <div id="_esperar" > 
        </div>    
    </body>

    
    <script type="text/javascript">


        function buscarxArticulo() {
            var data = $("#form1").serialize();
            $("#_LISTADO").load("ArticuloTraerPorNombre", data);
        }

        function enviar(pedidox  ) {
            if (confirm('¿Estas seguro de Enviar a Facturar?'))
            {
                var data = "c_c_pedido=" + pedidox ;
                global_JQuery_html("#", "_esperar", global_generarHTMLImagenLoad());
                $("#_WORK").load("EnviarPed", data);
            }
        }        

        function buscarArticulo(pedidox) {
            global_JQuery_html("#", "_esperar", global_generarHTMLImagenLoad());
            var data = "c_c_pedido=" + pedidox;
            $("#_WORK").load("BuscarArticulo", data);
        }

        function modificarDetPedido(pedidox, articulox, secuenciax) {
            global_JQuery_html("#", "_esperar", global_generarHTMLImagenLoad());
            var data = "c_c_pedido=" + pedidox + "&c_c_articulo=" + articulox + "&n_i_secuencia=" + secuenciax;
            $("#_WORK").load("EdiDetPed", data);
        }

        function eliminarDetPedido(pedidox, articulox, secuenciax) {
            if (confirm('¿Estas seguro de ELIMINAR EL PRODUCTO ?'))
            {
                var data = "c_c_pedido=" + pedidox + "&c_c_articulo=" + articulox + "&n_i_secuencia=" + secuenciax;
                global_JQuery_html("#", "_esperar", global_generarHTMLImagenLoad());
                $("#_WORK").load("EliDetPed", data);
            }
        }
        function modificarCabPedido(xc_c_pedido) {
//            alert('ingresox');
//            alert(xc_c_pedido);
            var data = "c_c_pedido=" + xc_c_pedido ;
            $("#_WORK").load("ModificarCabPedido",data);
            
//                global_JQuery_html("#", "_WORK", global_generarHTMLImagenLoad());
//		var data = "c_c_pedido=" + xc_c_pedido ;
//		$("#_WORK").load("ModificarPedido",data);
	}
    </script>
</html>
