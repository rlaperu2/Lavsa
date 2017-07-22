/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import  domain.Empleado;
import  domain.Usuario;
import  model.LogonModel;

@WebServlet({ "/LogonIngreso", "/LogonSalir" })
public class LogonController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void service(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    // Recoger el alias
    String alias = request.getServletPath();
    // Programar
    if(alias.equals("/LogonIngreso")){
      logonIngreso(request,response);
    } else if(alias.equals("/LogonSalir")){
      logonSalir(request,response);
    } 
  }

  
  

	 private void logonIngreso(HttpServletRequest request,
		      HttpServletResponse response) throws ServletException, IOException {
		    String destino;
		    try {
		      // Datos
		      String usuario = request.getParameter("usuario");
		      String clave = request.getParameter("clave");
		      // Proceso
		      /*if(usuario.equals("gustavo") && clave.equals("coronel")){
		        destino = "main.jsp";
		        request.getSession().setAttribute("usuario", usuario);
		      } else {
		        throw new Exception("Datos no son correctos.");
		      }*/
		      LogonModel model = new LogonModel();
		      Usuario bean = model.validar(usuario, clave);
		      destino="main.jsp";
		      request.getSession().setAttribute("usuario", bean);
                      request.getSession().setAttribute("codusuario", bean.getC_c_usuario());
                      
		    } catch (Exception e) {
		      request.setAttribute("error", e.getMessage());
		      destino = "index.jsp";
		    }
		    // Forward
		    request.getRequestDispatcher(destino).forward(request, response);
		  }
  

  private void logonSalir(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    request.getSession().invalidate();
    request.getRequestDispatcher("index.jsp").forward(request, response);
  }

}