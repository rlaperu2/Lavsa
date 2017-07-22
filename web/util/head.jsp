<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<table style="width: 100%;">
  <tr>
    <td><img alt="" src="img/logo.gif"/></td>
    <td style="text-align: left;">
      Usuariox:<br/>
      ${sessionScope.usuario.c_t_correo}
      <br/>
      <a href="LogonSalir">Salir</a>
    </td>
  </tr> 
</table>