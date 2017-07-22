package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticuloModel;
import domain.Articulo;
import domain.DetPedido;
import util.Eureka;

/**
 * Servlet implementation class ArticuloController
 */
@WebServlet({"/ArticuloTraerPorNombre", "/ArticuloTraerPorCodigo","/ArtTraerparaListado"})
public class ArticuloController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoger el alias
//		System.out.println("richard_00");
        String alias = request.getServletPath();
            switch (alias) {
                case "/ArtTraerparaListado":
                    traerPorNombreparaListado  (request, response);  
                    break;
                case "/ArticuloTraerPorNombre":
                    traerPorNombre(request, response);
                     break;
                    
                case "/ArticuloTraerPorCodigo":
                    traerPorCodigo(request, response);
                    break;
                            }
        
//        if (alias.equals("/ArticuloTraerPorNombre")) {
//            traerPorNombre(request, response);
//        } else if (alias.equals("/ArticuloTraerPorCodigo")) {
//            traerPorCodigo(request, response);
//        }

    }

    private void traerPorCodigo(HttpServletRequest request,
            HttpServletResponse response) {
 
    }
    
    
    // Para listado de productos
    private void traerPorNombreparaListado(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        //System.out.println("richard_01");
        try {
			// Obtener parametro
            //System.out.println("richard_01");
            String nombre = request.getParameter("nombre");
            String nombre2 = request.getParameter("nombre");

            String pedidox = request.getParameter("pedido");
            System.out.println("entro a articulo LIStado");
			// Proceso
            //System.out.println("richard_02");
            ArticuloModel model = new ArticuloModel();
            List<Articulo> lista = model.traerPornombre(nombre, nombre2);
            if (lista.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron datos");
                destino = "infoPage.jsp";
            } else {
                request.setAttribute("lista", lista);
                request.setAttribute("pedido", pedidox);
                //	System.out.println("richard_03");
                // Si el codigo es el mismo que he buscado debe seleccionarlo automaticamente
                destino="";
                nombre = nombre.toUpperCase() ;
  
                    destino = "ListadoArticulosMaestro.jsp";
                      System.out.println("entro a articulo LIStadoB");
                    request.getRequestDispatcher(destino).forward(request, response);
  

            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
            request.getRequestDispatcher(destino).forward(request, response);
        }
        

    }
    
    
    
    /// para pedido
    private void traerPorNombre(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        //System.out.println("richard_01");
        try {
			// Obtener parametro
            //System.out.println("richard_01");
            String nombre = request.getParameter("nombre");
            String nombre2 = request.getParameter("nombre");

            String pedidox = request.getParameter("pedido");
            System.out.println("entro a articulo");
			// Proceso
            //System.out.println("richard_02");
            ArticuloModel model = new ArticuloModel();
            List<Articulo> lista = model.traerPornombre(nombre, nombre2);
            if (lista.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron datos");
                destino = "infoPage.jsp";
            } else {
                request.setAttribute("lista", lista);
                request.setAttribute("pedido", pedidox);
                //	System.out.println("richard_03");
                // Si el codigo es el mismo que he buscado debe seleccionarlo automaticamente
                destino="";
                nombre = nombre.toUpperCase() ;
                if (lista.get(0).getC_c_articulo().toUpperCase().equals( nombre ) )
                {
                      //var data="c_c_pedido=" + pedidox + "&c_c_articulo="+articulox ;
                      //$("#_WORK").load("DetPedidoNuevo",data);
                      //destino = "ListadoArticulos.jsp";
                    agregar_det_pedido(request,response,pedidox,nombre);
                    
                    
                    
                     
                }   
                else
                {    
                    destino = "ListadoArticulos.jsp";
                    request.getRequestDispatcher(destino).forward(request, response);
                }    

            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
            request.getRequestDispatcher(destino).forward(request, response);
        }
        

    }
    
    
    
        private void agregar_det_pedido(HttpServletRequest request, HttpServletResponse response , String pedidox , String articulox)
            throws ServletException, IOException {
    
        request.setAttribute("accion", Eureka.ACCION_NUEVO);
        DetPedido bean = new DetPedido();
        request.setAttribute("bean", bean);
        bean.setC_c_pedido(pedidox);
        bean.setC_c_articulo(articulox);
 //       String ls_articulo;
        ArticuloModel model = new ArticuloModel();
//        ls_articulo = model.traer_nombre(articulox);
 //       bean.setC_t_articulo(ls_articulo);
        
        DetPedido detpx = model.traer_nombre_y_precios(articulox,pedidox);
        bean.setC_t_articulo(detpx.getC_t_articulo());
        bean.setN_i_precio(detpx.getN_i_precio());
        
        Float lde_valorx = Float.valueOf("0.00");
        bean.setN_i_porc_dscto_1(lde_valorx);
        bean.setN_i_porc_dscto_2(lde_valorx);
        bean.setN_i_porc_dscto_3(lde_valorx);
        bean.setN_i_porc_dscto_4(lde_valorx);
        request.getRequestDispatcher("editarDetPedido.jsp").forward(request, response);
    
    }

    
    
    
}
