package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.AccesoDB;
import domain.Cliente;
import model.PedidoModel;
import domain.Pedido;
import model.ClienteModel;
import util.Eureka;

@WebServlet({"/ClienteTraerPorNombre", "/Clientenombre", "/SeleccionarCliente", "/AgregarCliente", "/escogerCliente", "/CuentasxCobrar"})
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String alias = request.getServletPath();



        if (alias.equals("/Clientenombre")) {
            nombre(request, response);
        }
        if (alias.equals("/SeleccionarCliente")) {
            seleccion(request, response);
        }

        if (alias.equals("/AgregarCliente")) {
            System.out.println("lllego1");
            agregarCliente(request, response);
        }

        if (alias.equals("/escogerCliente")) {
            //System.out.println("lllegoxxxxxxxxxxxxxxxxxxxxxxxxxx");
            escogerCliente(request, response);
        }

    }



    private void escogerCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("accion", "M");
        request.setAttribute("pedidox", request.getParameter("xc_c_pedido"));
        request.getRequestDispatcher("escogerCliente.jsp").forward(request, response);
    }

    private void traerpornombre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino;
        try {
            // Obtener parámetro
            String nombre = request.getParameter("nombr");
                        //String nombre ="PERUANO";
            // Proceso
            ClienteModel model = new ClienteModel();
            List<Cliente> listas = model.traerPorNombre(nombre);
            //System.out.println("richard_03");
            if (listas.isEmpty()) {
                request.setAttribute("mensaje", "No se encontrdos datos.");
                destino = "infoPage.jsp";
            } else {
                //System.out.println("richard_04");
                request.setAttribute("listas", listas);
                destino = "listadoClientes.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
        }

        request.getRequestDispatcher(destino).forward(request, response);
    }

    private void nombre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        try {

            String tipo = request.getParameter("tipo");
            String codigousuariox = String.valueOf(request.getSession().getAttribute("codusuario"));
            List<Cliente> listass = ClienteModel.listarcliente(tipo, codigousuariox);
            if (listass.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron clientes");
                destino = "infoPage.jsp";
            } else {
                request.setAttribute("listass", listass);
                request.setAttribute("pedidox", request.getParameter("xc_c_pedido"));
                destino = "seleccionarCliente.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
        }
        request.getRequestDispatcher(destino).forward(request, response);
    }

    private void seleccion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = null;
        String tipo = request.getParameter("tipo");
        Cliente cli = ClienteModel.seleccioncliente(tipo);
        destino = "seleccionarCliente.jsp";
        request.getRequestDispatcher(destino).forward(request, response);
    }

    private void agregarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = null;
//        String tipo=request.getParameter("tipo");
//        Cliente cli=ClienteModel.seleccioncliente(tipo);
        destino = "editarCliente.jsp";
        request.getRequestDispatcher(destino).forward(request, response);
    }

    private void mostrarmensaje(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        try {

            request.setAttribute("mensaje", "Buscando cliente ... ");
            destino = "infoPage.jsp";

        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
        }
        request.getRequestDispatcher(destino).forward(request, response);
    }

}
