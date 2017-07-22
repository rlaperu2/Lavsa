/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

 
import domain.CtaxCobrar;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CtaxCobrarModel;
 

/**
 *
 * @author rlopez
 */
@WebServlet({"/CtaxCobrar"})
public class CtaxCobrarController extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String alias = request.getServletPath();
        System.out.println("entro");
        System.out.println(alias);

        if (alias.equals(
                "/CtaxCobrar")) {
            CuentasxCobrar(request, response);
        }
    }

    private void CuentasxCobrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        try {
            String codcli = request.getParameter("xc_c_cliente");
            CtaxCobrarModel model = new CtaxCobrarModel();
            List<CtaxCobrar> listas = model.traerCtaxCobrar(codcli);
            if (listas.isEmpty()) {
                request.setAttribute("mensaje", "No se encontrdos datos.");
                destino = "infoPage.jsp";
            } else {
                request.setAttribute("listas", listas);
                destino = "cuentasxcobrar.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
        }
        request.getRequestDispatcher(destino).forward(request, response);
    }

}
