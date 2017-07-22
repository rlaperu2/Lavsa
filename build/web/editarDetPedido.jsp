 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="form2" name="form2">
            <input type="hidden" name="accion" value="${accion}" />
            <!--type="hidden"-->
            <input type="hidden" name="c_c_pedido" value="${bean.c_c_pedido}" />
            <input type="hidden" name="n_i_secuencia" value="${bean.n_i_secuencia}" />
                <fieldset>
                    <legend>Detalle de Pedido</legend>
                    Codigo:
                    <input size="10"  type="text" name="c_c_articulo" value="${bean.c_c_articulo}" readonly="readonly"  />
                
                    <br>
                    Articulo:
                    <input size="40" disabled type="text" name="c_t_articulo" value="${bean.c_t_articulo}"  /> 
                
                    <br>
                    Cantidad:
                    <input size="10" type="text" name="n_i_cantidad" value="${bean.n_i_cantidad}"  />
                  
                    
                    <br>
                    Precio Bruto: 
                    <input size="10" type="text" name="n_i_precio" value="${bean.n_i_precio}"  />
                    <br>
                    
                    
                    %Dscto.1: 
                    <input size="3" type="text" name="n_i_dscto_1" value="${bean.n_i_porc_dscto_1}"  />
                    %Dscto.2:
                    <input size="3" type="text" name="n_i_dscto_2" value="${bean.n_i_porc_dscto_2}"  />
                    <br>
                    %Dscto.3:
                    <input size="3" type="text" name="n_i_dscto_3" value="${bean.n_i_porc_dscto_3}"  />
                    %Dscto.4:
                    <input size="3" type="text" name="n_i_dscto_4" value="${bean.n_i_porc_dscto_4}"  />
                    
                    
                    
                    <!--<br>-->
<!--                    Total: -->
                    <input size="10" disabled type="hidden" name="n_i_total" value="${bean.n_i_total}"  />
                    <!--<br>-->
                    <br>
                 <div id="_botones" > 
                    <a href="javascript:grabardetPedido();"><img  width="50" alt="" src="img/grabar.png"/></a>
                    <a href="javascript:salirdetPedido();"><img  width="50" alt="" src="img/salir.png"/></a>
                </div>     
<!--                    <input type="button" value="Grabar" id="btngrabary" class="botongrabary" >  
                    <input type="button" value="Salir" id="btnsaliry" class="botonsaliry" >  -->
            </fieldset>
         </form>        
        <div id="_esperar" > 
        </div>        
    </body>
    
    <script type="text/javascript" src="jquery/gfc_librerias.js"></script>
    <script type="text/javascript">

       
          function grabardetPedido() {
 
            //validar_numero("cantidad",document.form2.n_i_cantidad.value);
           if  (gfc_validar_numero("cantidad",document.form2.n_i_cantidad.value) && gfc_validar_numero("precio",document.form2.n_i_precio.value)  && gfc_validar_numero("n_i_dscto_1",document.form2.n_i_dscto_1.value)    && gfc_validar_numero("n_i_dscto_2",document.form2.n_i_dscto_2.value)    && gfc_validar_numero("n_i_dscto_3",document.form2.n_i_dscto_3.value)    && gfc_validar_numero("n_i_dscto_4",document.form2.n_i_dscto_4.value)    ) 
           {
                global_JQuery_html("#", "_botones", global_generarHTMLImagenLoad());
                var data = $("#form2").serialize();
                
                $("#_WORK").load("DetPedidoGrabar", data); 
           }
 
            

            
            
	}
        function salirdetPedido() {
            var data = "c_c_pedido=" + ${bean.c_c_pedido} ;
             global_JQuery_html("#", "_botones", global_generarHTMLImagenLoad());
            $("#_WORK").load("ModificarPedido",data);
	}
 

        $("#btnsaliry").click(function(){
         
            var data = "c_c_pedido=" + ${bean.c_c_pedido} ;
            $("#_WORK").load("ModificarPedido",data);
 
            //modificarPedido('${bean.c_c_pedido}');
        });
        
        $("#btngrabary").click(function(){
           
          var data = $("#form2").serialize();
	  $("#_WORK").load("DetPedidoGrabar", data);
        });
        
    </script>    
    
</html>
