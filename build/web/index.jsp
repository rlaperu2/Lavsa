 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
         <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
        
        <title>Importaciones Lavsa</title>
        <style type="text/css">
            #_LOGON{
                width: 200px;
                margin: 1px;
                padding: 1px;
                background-color: white;
            }
        </style>
    </head>
    <body>
        <form method="post" action="LogonIngreso">
            <div id="_LOGON">
                
                <img alt="" src="img/logo.gif"/>
                <h1>Ingreso al sistema</h1>

                <% if (request.getAttribute("error") != null) {%>

                <div class="_error">

                    <%=request.getAttribute("error")%>

                </div>
                <% }%>

                <fieldset>
                    <legend>Datos</legend>
                    <label class="_etiqueta">Usuario:</label>
                    <input type="text" name="usuario"/>
                    <hr/>
                    <label class="_etiqueta">Clave:</label>
                    <input type="password" name="clave"/>
                </fieldset>

                <br/>
                <input class="button" type="submit" value="Ingresar" /> 
                <br/> 
            </div>
        </form>
    </body>
</html>