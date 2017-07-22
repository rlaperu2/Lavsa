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

        <h1>Resultado</h1>
        <table    border="1">
            <tr>
                <th>T.Doc.</th>
                <th>Documento</th>
                <th>Cliente</th>
                <th></th>
                <th>Total</th>
                <th>Comision</th>
            </tr>
            <c:forEach  items="${requestScope.listas}" var="c">
                <tr>

                    <td>${c.documento.c_t_tipo_doc_abrev}</td>
                    <td>${c.documento.c_t_documento}</td>
                    <td>${c.documento.cliente.c_t_cliente}</td>
                    <td>${c.documento.c_t_moneda_abre}</td>

                    <td>${c.documento.n_i_total} </td>
                      <td>${c.comision}</td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>