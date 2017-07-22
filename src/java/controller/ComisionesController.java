/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

 
import domain.Comisiones;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ComisionesModel;
 

/**
 *
 * @author rlopez
 */
@WebServlet({"/Comisiones"})
public class ComisionesController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String alias = request.getServletPath();
         
        if (alias.equals(
                "/Comisiones")) {
            ComisionesDetalle(request, response);
        }
    }

    private void ComisionesDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
        String destino;
        try {
            String codusuario = String.valueOf(request.getSession().getAttribute("codusuario")) ;
            ComisionesModel model = new ComisionesModel();
            List<Comisiones> listas = model.traerComisiones(  
                   codusuario 
                     ,  
                              request.getParameter("xc_c_moneda") , 
                              request.getParameter("xn_n_anio") , 
                              request.getParameter("xn_n_mes") );
            if (listas.isEmpty()) {
                request.setAttribute("mensaje", "No se encontrdos datos.");
                destino = "infoPage.jsp";
            } else {
                request.setAttribute("listas", listas);
                destino = "comisiones_detalle.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
        }
        request.getRequestDispatcher(destino).forward(request, response);
    }

}
