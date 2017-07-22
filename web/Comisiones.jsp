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
    java.util.Calendar fecha = java.util.Calendar.getInstance();
    Integer mes_actual = fecha.get(java.util.Calendar.MONTH);
    Integer anio_actual = fecha.get(java.util.Calendar.YEAR);
    String ls_1_sel ="";
    String ls_2_sel ="";
    String ls_3_sel ="";
    String ls_4_sel ="";
    String ls_5_sel ="";
    String ls_6_sel ="";
    String ls_7_sel ="";
    String ls_8_sel ="";
    String ls_9_sel ="";
    String ls_10_sel ="";
    String ls_11_sel ="";
    String ls_12_sel ="";
    if (mes_actual.equals(0) ){         ls_1_sel="SELECTED";    }
    if (mes_actual.equals(1) ){         ls_2_sel="SELECTED";    }
    if (mes_actual.equals(2) ){         ls_3_sel="SELECTED";    }
    if (mes_actual.equals(3) ){         ls_4_sel="SELECTED";    }
    if (mes_actual.equals(4) ){         ls_5_sel="SELECTED";    }
    if (mes_actual.equals(5) ){         ls_6_sel="SELECTED";    }
    if (mes_actual.equals(6) ){         ls_7_sel="SELECTED";    }
    if (mes_actual.equals(7) ){         ls_8_sel="SELECTED";    }
    if (mes_actual.equals(8) ){         ls_9_sel="SELECTED";    }
    if (mes_actual.equals(9) ){         ls_10_sel="SELECTED";    }
    if (mes_actual.equals(10) ){         ls_11_sel="SELECTED";    }
    if (mes_actual.equals(11) ){         ls_12_sel="SELECTED";    }
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
                    <h1>Comisiones</h1>
                    <table>
                        <tr>
                            <td>

                                <table>
                                    <tr>
                                        <td>
                                            <label>Año:</label><input name="xn_n_anio" id="xn_n_anio" size="3" value="<%=anio_actual%>">
                                            <label>Mes:</label>
                                            <select name="xn_n_mes"  style="width: 85px" >
                                                <option value="1"  <%=ls_1_sel%> >Enero</option> 
                                                <option value="2"  <%=ls_2_sel%> >Febrero</option> 
                                                <option value="3"  <%=ls_3_sel%> >Marzo</option>
                                                <option value="4"  <%=ls_4_sel%> >Abril</option>
                                                <option value="5"  <%=ls_5_sel%> >Mayo</option>
                                                <option value="6"  <%=ls_6_sel%> >Junio</option> 
                                                <option value="7"  <%=ls_7_sel%> >Julio</option>
                                                <option value="8"  <%=ls_8_sel%> >Agosto</option>
                                                <option value="9"  <%=ls_9_sel%> >Setiembre</option> 
                                                <option value="10" <%=ls_10_sel%> >Octubre</option>
                                                <option value="11" <%=ls_11_sel%> >Noviembre</option>
                                                <option value="12" <%=ls_12_sel%> >Diciembre</option> 
                                            </select>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Moneda:</label> 
                                            <select name="xc_c_moneda"  style="width: 60px" >
                                                <option value="01"   selected    >S/.</option> 
                                                <option value="02"  >US$</option> 
                                            </select>
                                        </td>
                                    </tr>

                                </table>

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
            $("#_LISTADO").load("Comisiones", data);

        }

    </script>





</html>