package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Eureka;
import db.AccesoDB;
import domain.Cliente;
import domain.DetPedido;
import domain.Pedido;
import domain.TipoPago;
import domain.LugarEntrega;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.ClienteModel;
import model.PedidoModel;
import model.TipoPagoModel;
import model.LugarEntregaModel;
import model.ArticuloModel;
import com.google.gson.Gson;


@WebServlet({"/PedidoTraerPorFechas", "/PedidoTraerPorAnioMes", "/EdiDetPed", "/DetPedidoNuevo", "/PedidoNuevo", "/CrearPedido", "/PedidoGrabar", "/DetPedidoGrabar", "/ModificarPedido", "/BuscarArticulo", "/EliDetPed", "/AnularPedido","/ModificarCabPedido","/EnviarPed" ,"/buscar","/buscar_cliente_lugares","/CrearCliente"})
public class PedidoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String alias = null;
        try {
            alias = request.getServletPath();
            
    
            
            switch (alias) {

                case "/PedidoTraerPorFechas":
                    traerPorFechas(request, response);
                    break;
                case "/PedidoTraerPorAnioMes":
                    traerPorAnioMes(request, response);
                    break;
                case "/PedidoNuevo":
                    nuevo(request, response);
                    break;
                case "/DetPedidoNuevo":
                    nuevodet(request, response);
                    break;
                case "/CrearPedido":
                    crear_cab_pedido(request, response);
                    break;
                case "/PedidoGrabar":
                    grabar(request, response);
                    break;
                case "/DetPedidoGrabar":
                    detgrabar(request, response);
                    break;
                case "/CrearCliente":
                    crearcliente(request, response);
                    break;                    
                case "/ModificarPedido":
                    modificarPedido(request, response);
                    break;
                case "/ModificarCabPedido":
                    modificar_cab_pedido(request, response);
                    break;
                case "/AnularPedido":
                    anularPedido(request, response);
                    break;
                case "/EdiDetPed":
                    editardetped(request, response);
                    break;
                case "/BuscarArticulo":
                    buscararticulo(request, response);
                    break;
                case "/EliDetPed":
                    aliminardetped(request, response);
                    break;
                case "/EnviarPed":
                    enviarPedido(request, response);
                    break;    
                case "/buscar":
                    buscar(request, response);
                    break;                    
                case "/buscar_cliente_lugares":
                    buscar_cliente_lugares(request, response);
                    break;                        
            }

        } catch (Exception ex) {
            System.out.println("Error en el elegir en  PedidoController.java " + alias);
        }

    }

    public void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String find = request.getParameter("nombre").toUpperCase().trim();
            String codigousuariox = String.valueOf(request.getSession().getAttribute("codusuario")); 
            String[][] names = new   ClienteModel().buscarPorNombreNew(find,codigousuariox); 
            String json = new Gson().toJson(names);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
           
    }
    
    protected void anularPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String c_c_pedido = request.getParameter("c_c_pedido");
        PedidoModel model = new PedidoModel();
        String codigousuariox = String.valueOf(request.getSession().getAttribute("codusuario"));
        model.AnularPedido(c_c_pedido,codigousuariox);
            // traerPorFechas(request, response);
        //request.getRequestDispatcher("mantPedidos.jsp").forward(request,response);
        modificarPedido(request, response);

    }

    protected void enviarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String c_c_pedido = request.getParameter("c_c_pedido");
        PedidoModel model = new PedidoModel();
        model.quitar_no_ver(c_c_pedido);
        // traerPorFechas(request, response);
        //request.getRequestDispatcher("mantPedidos.jsp").forward(request,response);
        modificarPedido(request, response);
    }    
    
    
    protected void aliminardetped(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parametro
        String c_c_pedido = request.getParameter("c_c_pedido");
        String c_c_articulo = request.getParameter("c_c_articulo");
        Long n_i_secuencia = Long.valueOf(request.getParameter("n_i_secuencia"));
        PedidoModel model = new PedidoModel();
        model.EliminardetPedido(c_c_pedido, c_c_articulo, n_i_secuencia);

//                request.setAttribute("accion", Eureka.ACCION_EDITAR);
//                request.setAttribute("bean", bean);
//                
        modificarPedido(request, response);
    }

    protected void buscararticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Parametro
        String c_c_pedido = request.getParameter("c_c_pedido");
        //System.out.println(" en pedidocontroller / buscararticulo :  "+ c_c_pedido);
        request.setAttribute("pedidox", c_c_pedido);
        request.getRequestDispatcher("buscarArticulo.jsp").forward(request, response);
    }

    protected void nuevodet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            agregar_det_pedido(request,response,request.getParameter("c_c_pedido"),request.getParameter("c_c_articulo"));
//        request.setAttribute("accion", Eureka.ACCION_NUEVO);
//        DetPedido bean = new DetPedido();
//        request.setAttribute("bean", bean);
//        bean.setC_c_pedido(request.getParameter("c_c_pedido"));
//        bean.setC_c_articulo(request.getParameter("c_c_articulo"));
//        String ls_articulo;
//        ArticuloModel model = new ArticuloModel();
//        ls_articulo = model.traer_nombre(request.getParameter("c_c_articulo"));
//        bean.setC_t_articulo(ls_articulo);
//        Float lde_valorx = Float.valueOf("0.00");
//        bean.setN_i_porc_dscto_1(lde_valorx);
//        bean.setN_i_porc_dscto_2(lde_valorx);
//        bean.setN_i_porc_dscto_3(lde_valorx);
//        bean.setN_i_porc_dscto_4(lde_valorx);
//        request.getRequestDispatcher("editarDetPedido.jsp").forward(request, response);
    }

    public void agregar_det_pedido(HttpServletRequest request, HttpServletResponse response , String pedidox , String articulox)
            throws ServletException, IOException {
    
        request.setAttribute("accion", Eureka.ACCION_NUEVO);
        DetPedido bean = new DetPedido();
        request.setAttribute("bean", bean);
        bean.setC_c_pedido(pedidox);
        bean.setC_c_articulo(articulox);
        //String ls_articulo;
         ArticuloModel model = new ArticuloModel();
//        ls_articulo = model.traer_nombre(articulox);
        //bean.setC_t_articulo(ls_articulo);
        
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
    
    
    protected void editardetped(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  //System.out.println(" en pedidocontroller / editardetped :  " );   
        // Parametro
        String c_c_pedido = request.getParameter("c_c_pedido");
        String c_c_articulo = request.getParameter("c_c_articulo");
        Long n_i_secuencia = Long.valueOf(request.getParameter("n_i_secuencia"));
        PedidoModel model = new PedidoModel();
        DetPedido bean = model.DetPedidoTraerPorLlave(c_c_pedido, c_c_articulo, n_i_secuencia);

        if (bean == null) {
            throw new RuntimeException("ERROR, registro no existe.");
        }

        request.setAttribute("accion", Eureka.ACCION_EDITAR);
        request.setAttribute("bean", bean);

        request.getRequestDispatcher("editarDetPedido.jsp").forward(request, response);
    }

    private void detgrabar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        String destino = null;
        try {

            String accion = request.getParameter("accion");
            DetPedido bean = new DetPedido();

            bean.setC_c_pedido(request.getParameter("c_c_pedido"));
            bean.setC_c_articulo(request.getParameter("c_c_articulo"));
            bean.setN_i_cantidad(Float.valueOf(request.getParameter("n_i_cantidad")));
            bean.setN_i_precio(Float.valueOf(request.getParameter("n_i_precio")));
            bean.setN_i_porc_dscto_1(Float.valueOf(request.getParameter("n_i_dscto_1")));
            bean.setN_i_porc_dscto_2(Float.valueOf(request.getParameter("n_i_dscto_2")));
            bean.setN_i_porc_dscto_3(Float.valueOf(request.getParameter("n_i_dscto_3")));
            bean.setN_i_porc_dscto_4(Float.valueOf(request.getParameter("n_i_dscto_4")));

            PedidoModel model = new PedidoModel();
            if (accion.equals(Eureka.ACCION_EDITAR)) {
                bean.setN_i_secuencia(Long.valueOf(request.getParameter("n_i_secuencia")));
                //model.actualizardet(bean);
                model.insertar_actualizar_det(bean, "U");
                modificarPedido(request, response);
                //request.setAttribute("mensaje","Registro actualizado.");
            } else if (accion.equals(Eureka.ACCION_NUEVO)) {
                model.insertar_actualizar_det(bean, "I");
                    //model.insertardet(bean);
                //request.setAttribute("mensaje","Registro creado en la BD.");
                modificarPedido(request, response);
            } else {
                throw new Exception("ERROR, proceso no es correcto.");
            }
            destino = "infoPage.jsp";
        } catch (Exception e) {
            destino = "errorPage.jsp";
            request.setAttribute("mensaje", e.getMessage());
        }
        request.getRequestDispatcher(destino).forward(request, response);
    }

      private void crearcliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
          
        String destino;
        try {
            String accion = request.getParameter("accion");
            Cliente bean = new Cliente();
            bean.setC_c_ruc(request.getParameter("xc_c_ruc"));
            bean.setC_t_cliente(request.getParameter("xc_t_cliente"));
 
            ClienteModel model = new ClienteModel();

            String codcliente = model.seleccionclientePorRUC(request.getParameter("xc_c_ruc")).getC_c_cliente();
            if (codcliente==null){
                codcliente="";
            }
            if (  codcliente.length()>0 ){
                request.setAttribute("mensajeSistema", "El RUC ya esta registrado");
            }
            else
            {
                model.insertar(bean,String.valueOf(request.getSession().getAttribute("codusuario")));
                request.setAttribute("c_c_cliente",  model.seleccionclientePorRUC(request.getParameter("xc_c_ruc")).getC_c_cliente()   );
                request.setAttribute("mensajeSistema", "Se creo al cliente correctamente");
            }
            crear_cab_pedido(request,response);
            
            //destino = "editarPedido.jsp";
        } catch (Exception e) {
            request.setAttribute("mensajeSistema", e.getMessage());
            crear_cab_pedido(request,response);
             
//            destino = "errorPage.jsp";
//            request.setAttribute("mensaje", e.getMessage());
        }
        //request.getRequestDispatcher(destino).forward(request, response);
    }

    
    
    private void grabar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        String destino;
        try {
            String accion = request.getParameter("accion");
            Pedido bean = new Pedido();
            bean.setC_c_cliente(request.getParameter("xc_c_cliente"));
            bean.setD_dt_entrega(request.getParameter("xd_dt_entrega"));
            bean.setC_c_pedido(request.getParameter("xc_c_pedido"));
            bean.setC_n_pedido(request.getParameter("xc_n_pedido"));
            bean.setC_c_vendedor(request.getParameter("xc_c_vendedor"));
            bean.setC_c_usuario_creador(request.getParameter("xc_c_usuario_creador"));
            
//             System.out.println("eeeee1");
//             System.out.println(request.getParameter("xc_c_cliente")); 
            
            bean.setC_c_tipo_pago(request.getParameter("xc_c_tipo_pago"));
            bean.setC_c_moneda(request.getParameter("xc_c_moneda"));
            bean.setC_c_lugar(request.getParameter("xc_c_lugar"));
            bean.setC_t_observacion(request.getParameter("xc_t_observacion"));

            PedidoModel model = new PedidoModel();
            if (accion.equals(Eureka.ACCION_EDITAR)) {
                // Grabar el pedido
                model.actualizar(bean);
                //request.setAttribute("mensaje", "Registro actualizado.");
            } else if (accion.equals(Eureka.ACCION_NUEVO)) {
                String codigousuariox = String.valueOf(request.getSession().getAttribute("codusuario"));
                // Grabar el pedido
                bean.setC_c_pedido(model.insertar(bean , codigousuariox ));

    
                //request.setAttribute("mensaje","Registro creado en la BD.");
            }
            //else {
            //    throw new Exception("ERROR, proceso no es correcto.");
            //}
            
            // Proceso
            //PedidoModel model = new PedidoModel();
            bean = model.PedidoTraerPorCodigo(bean.getC_c_pedido());

            if (bean == null) {
                throw new RuntimeException("ERROR, codigo no existe.");
            }
            request.setAttribute("accion", Eureka.ACCION_EDITAR);
            request.setAttribute("bean", bean);
            // Detalle de pedido
            List<DetPedido> lista = model.detPedidoTraerPorCodigo(bean.getC_c_pedido());
            if (lista.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron datos");
            } else {
                request.setAttribute("lista", lista);
            }
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("editarPedidocompleto.jsp").forward(request, response);
            
            
            
            
            destino = "infoPage.jsp";
        } catch (Exception e) {
            destino = "errorPage.jsp";
            request.setAttribute("mensaje", e.getMessage());
        }
        request.getRequestDispatcher(destino).forward(request, response);
    }

    private void traerPorFechas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        try {

            // Obtener parametro
 

            String codigousuariox = String.valueOf(request.getSession().getAttribute("codusuario"));
            String desdex = request.getParameter("desdex");
            String hastax = request.getParameter("hastax");
            // Proceso
            PedidoModel model = new PedidoModel();
            List<Pedido> lista = model.PedidoTraerPorFechas(desdex, hastax , codigousuariox  );
            if (lista.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron datos");
                destino = "infoPage.jsp";
            } else {
                request.setAttribute("desdex", desdex);
                request.setAttribute("hastax", hastax);
                request.setAttribute("lista", lista);
                destino = "ListadoPedidos.jsp";

            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
        }
        request.getRequestDispatcher(destino).forward(request, response);

    }

    private void traerPorAnioMes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        try {

            // Obtener parametro
            String codigousuariox = String.valueOf(request.getSession().getAttribute("codusuario"));
            String aniox = request.getParameter("aniox");
            String mesx = request.getParameter("mesx");
            // Proceso
            PedidoModel model = new PedidoModel();
            List<Pedido> lista = model.PedidoTraerPorAnioMes(aniox, mesx , codigousuariox);
            if (lista.isEmpty()) {
                request.setAttribute("mensaje", "No se encontraron datos");
                destino = "infoPage.jsp";
            } else {
                request.setAttribute("lista", lista);
                destino = "ListadoPedidos.jsp";

            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            destino = "errorPage.jsp";
        }
        request.getRequestDispatcher(destino).forward(request, response);

    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("accion", "A");
        request.getRequestDispatcher("escogerCliente.jsp").forward(request, response);
    }

    
    
    protected void crear_cab_pedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Pedido bean = new Pedido();

//        System.out.println("lllegoxxxxx"); 
//        System.out.println(request.getParameter("xc_c_ruc"));
//         System.out.println(request.getParameter("xc_t_cliente"));
//         System.out.println(String.valueOf(request.getAttribute("c_c_cliente")));
//            System.out.println("lllegoxxxxx2");
            
            
         request.setAttribute("bean", bean);
//        bean.setC_c_cliente(request.getParameter("c_c_cliente"));
//        bean.setC_t_cliente(request.getParameter("c_t_cliente"));
//        bean.setC_c_ruc(request.getParameter("c_c_ruc"));
         
        // lo siguiente es para que luego de crear el cliente , tome los datos ya ingresados en la creacion del cliente
         
        String codCliente=String.valueOf(request.getAttribute("c_c_cliente")) ;
        if (codCliente==null) {
            codCliente="";
        }
        request.setAttribute("mensajeSistema", request.getAttribute("mensajeSistema"));
        bean.setC_c_cliente(codCliente);
        bean.setC_t_cliente(request.getParameter("xc_t_cliente"));
        bean.setC_c_ruc(request.getParameter("xc_c_ruc")); 
         
         
         

        java.util.Date fecha = new Date();
        DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = fechaHora.format(fecha);
        bean.setD_dt_entrega(convertido);

        request.setAttribute("accion", Eureka.ACCION_NUEVO);

        TipoPagoModel model = new TipoPagoModel();
        List<TipoPago> lista = model.traertodo();
        request.setAttribute("lista", lista);
//               
        LugarEntregaModel model2 = new LugarEntregaModel();
        List<LugarEntrega> lista2 = model2.traerporCliente(codCliente);
        request.setAttribute("lista2", lista2);

        request.getRequestDispatcher("editarPedido.jsp").forward(request, response);
    }

    protected void modificar_cab_pedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PedidoModel modelp = new PedidoModel();
        Pedido bean = modelp.PedidoTraerPorCodigo(request.getParameter("c_c_pedido")) ;
        request.setAttribute("bean", bean);
        request.setAttribute("accion", Eureka.ACCION_EDITAR);
        // si vengo con el cliente lleno quiere decir que estoy cambiando de cliente y vengo de una busqueda
      if (request.getParameter("c_c_cliente") != null){
            bean.setC_c_cliente(request.getParameter("c_c_cliente"));
            bean.setC_t_cliente(request.getParameter("c_t_cliente"));
            bean.setC_c_ruc(request.getParameter("c_c_ruc"));
        }
       
        TipoPagoModel model = new TipoPagoModel();
        List<TipoPago> lista = model.traertodo();
        request.setAttribute("lista", lista);
        cliente_lugares(bean.getC_c_cliente() , request );
//        LugarEntregaModel model2 = new LugarEntregaModel();
//        List<LugarEntrega> lista2 = model2.traerporCliente(bean.getC_c_cliente());
//        request.setAttribute("lista2", lista2);
        request.getRequestDispatcher("editarPedido.jsp").forward(request, response);
    }
    
    protected void buscar_cliente_lugares(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        System.out.println("llego");
        System.out.println(request.getParameter("codcliente"));
         
        LugarEntregaModel model2 = new LugarEntregaModel();
        String[][] names = model2.traerporCliente_arrary(request.getParameter("codcliente"));
        
            response.setContentType("text/html;charset=UTF-8");
            //String find = request.getParameter("nombre").toUpperCase().trim();
            //String[][] names2 = new   ClienteModel().buscarPorNombreNew(find); 
            String json = new Gson().toJson(names);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        
        
        //request.setAttribute("lista2", lista2);
        
//        cliente_lugares(request.getParameter("codcliente") , request );
//        request.getRequestDispatcher("editarPedido.jsp").forward(request, response);
    }
    
    protected void cliente_lugares(String as_cliente , HttpServletRequest request ){
        LugarEntregaModel model2 = new LugarEntregaModel();
        List<LugarEntrega> lista2 = model2.traerporCliente(as_cliente);
        request.setAttribute("lista2", lista2);
    
    }
    
        
    
    
    protected void modificarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parametro
        String codigo = request.getParameter("c_c_pedido");

        // Proceso
        PedidoModel model = new PedidoModel();
        Pedido bean = model.PedidoTraerPorCodigo(codigo);
        if (bean == null) {
            throw new RuntimeException("ERROR, codigo no existe.");
        }
        request.setAttribute("accion", Eureka.ACCION_EDITAR);
        request.setAttribute("bean", bean);
        // Detalle de pedido
        List<DetPedido> lista = model.detPedidoTraerPorCodigo(codigo);
        if (lista.isEmpty()) {
            request.setAttribute("mensaje", "No se encontraron datos");
        } else {
            request.setAttribute("lista", lista);
        }
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("editarPedidocompleto.jsp").forward(request, response);
    }

}
