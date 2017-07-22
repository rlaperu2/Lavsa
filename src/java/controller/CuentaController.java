/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Empleado;
import model.CuentaModel;

/**
 *
 * @author salegria
 */
@WebServlet({"/CuentaDeposito"})
public class CuentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		String alias = request.getServletPath();
		if (alias.equals("/CuentaDeposito")) {
			cuentaDeposito(request, response);
		} 
	}


	private void cuentaDeposito(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
	  
		Map<String,Object> data = new HashMap<>();
		try {
	    // Data
			String cuenta = request.getParameter("cuenta");
			Double importe = Double.parseDouble(request.getParameter("importe"));
			// Empleado
			Empleado usuario;
			usuario = (Empleado) request.getSession().getAttribute("usuario");
			// Proceso
			CuentaModel model = new CuentaModel();
			model.registrarDeposito(cuenta, importe, usuario.getCodigo());
			// Reporte
			data.put("codigo", 1);
		  data.put("mensaje", "Proceso ok.");
    } catch (Exception e) {
    	data.put("codigo", -1);
  	  data.put("mensaje", e.getMessage());
    }		
//	  Gson gson = new Gson();
//	  String dataJson = gson.toJson(data);
//	  //response.setHeader("Content-Type", "text/plain");
//	  response.setContentType("application/json");
//	  response.getWriter().println(dataJson);
//	  response.getWriter().flush();
	  
  }
	
}