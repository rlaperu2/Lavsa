package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.AccesoDB;
import domain.Cliente;
import domain.DetPedido;
import domain.Pedido;
import util.Eureka;

public class PedidoModel {

    public void AnularPedido(String pedidox , String codusuario) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "update TB_pedido set n_fl_anulado=1 , d_dt_anulacion=getdate() , c_c_usuario_anulo=? where c_c_pedido=?      ";

            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, codusuario);
            pr.setString(2, pedidox);

            pr.executeUpdate();
            pr.close();

            cn.commit();

            //               return  pedidox;     
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

    }

    public Pedido PedidoTraerPorCodigo(String pedidox) {
		// System.out.println("en pedidomodel " );
        //List<Pedido> lista = new ArrayList<Pedido>();
        Pedido bean = null;
        Connection cn = null;

        try {

            cn = AccesoDB.getConnection();
            String sql = "select tb_pedido.c_c_cliente, tb_cliente.c_c_ruc , d_dt_entrega = convert(varchar(11), tb_pedido.d_dt_entrega,103),c_c_pedido,c_n_pedido, d_dt_doc=convert(varchar(11),tb_pedido.d_dt_doc) ,C_T_CLIENTE= RTRIM(tb_cliente.c_t_cliente)  "
                    + " , tb_moneda.c_t_moneda_abre , tb_pedido.c_c_moneda ,   "
                    + "   n_i_total = case when tb_moneda.c_c_moneda='01' then tb_pedido.n_i_total_factura_nac else tb_pedido.n_i_total_factura_ext end  , "
                    + " c_c_facturado = case when n_i_total_arti <> n_i_por_facturar then 'Si' else 'No' end , "
                    + " c_t_anulado = case when n_fl_anulado=1 then 'ANULADO' else '' end  , tb_pedido.c_c_tipo_pago , tb_pedido.c_c_lugar , tb_pedido.c_t_observacion , enviado =  case when tb_pedido.n_fl_no_ver=1 then 'No'  else 'Si'  end  "
                    + "	  from tb_pedido "
                    + "	       inner join TB_moneda "
                    + "				on tb_moneda.c_c_moneda = TB_pedido.c_c_moneda  "
                    + "	       inner join TB_cliente "
                    + "				on tb_cliente.c_c_cliente = TB_pedido.c_c_cliente  "
                    + "	  where  "
                    + "	       tb_pedido.c_c_pedido = ? "
                    + "    ";

            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, pedidox);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                bean = new Pedido();
                bean.setC_c_pedido(rs.getString("c_c_pedido"));
                bean.setC_n_pedido(rs.getString("c_n_pedido"));
                bean.setD_dt_doc(rs.getString("d_dt_doc"));
                bean.setD_dt_entrega(rs.getString("d_dt_entrega"));
                bean.setC_t_cliente(rs.getString("c_t_cliente"));
                bean.setC_c_cliente(rs.getString("c_c_cliente"));
                bean.setC_c_ruc(rs.getString("c_c_ruc"));
                bean.setC_t_moneda_abre(rs.getString("c_t_moneda_abre"));
                bean.setN_i_total(rs.getFloat("n_i_total"));
                bean.setC_c_moneda(rs.getString("c_c_moneda"));
                bean.setC_c_facturado(rs.getString("c_c_facturado"));
                bean.setC_t_anulado(rs.getString("c_t_anulado"));
                bean.setC_c_tipo_pago(rs.getString("c_c_tipo_pago"));
                bean.setC_c_lugar(rs.getString("c_c_lugar"));
                bean.setC_t_observacion(rs.getString("c_t_observacion"));
                bean.setEnviado(rs.getString("enviado"));
                    //System.out.println("en pedido model b" );
                //lista.add(bean);
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

        return bean;
    }

    public DetPedido DetPedidoTraerPorLlave(String pedidox, String articulox, Long secuenciax) {

        DetPedido bean = null;

        List<DetPedido> lista = new ArrayList<DetPedido>();
        Connection cn = null;

        try {
            cn = AccesoDB.getConnection();
            String sql = " select tb_pedido_det.c_c_pedido , tb_pedido_det.n_i_secuencia , tb_pedido_det.c_c_articulo,c_t_articulo, n_i_cantidad , "
                    + "   n_i_precio = case when tb_pedido.c_c_moneda='01' then tb_pedido_det.n_i_precio_nac else tb_pedido_det.n_i_precio_ext  end  , "
                    + "   n_i_total = case when tb_pedido.c_c_moneda='01' then tb_pedido_det.n_i_total_item_nac else tb_pedido_det.n_i_total_item_ext end  , "
                    + "  tb_pedido_det.n_i_porc_dscto_1 , tb_pedido_det.n_i_porc_dscto_2 , tb_pedido_det.n_i_porc_dscto_3 , tb_pedido_det.n_i_porc_dscto_4 "
                    + "	  from tb_pedido_det "
                    + "	       inner join tb_pedido on tb_pedido.c_c_pedido = tb_pedido_det.c_c_pedido "
                    + "	       inner join TB_articulo "
                    + "				on tb_articulo.c_c_articulo = TB_pedido_det.c_c_articulo  "
                    + "	  where  "
                    + "	         tb_pedido_det.c_c_pedido = ? and tb_pedido_det.c_c_articulo = ? and tb_pedido_det.n_i_secuencia=? "
                    + "    ";

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, pedidox);
            pstm.setString(2, articulox);
            pstm.setLong(3, secuenciax);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {

                bean = new DetPedido();
                bean.setC_c_pedido(rs.getString("c_c_pedido"));
                bean.setN_i_secuencia(rs.getLong("n_i_secuencia"));
                bean.setC_c_articulo(rs.getString("c_c_articulo"));
                bean.setC_t_articulo(rs.getString("c_t_articulo"));
                bean.setN_i_cantidad(rs.getFloat("n_i_cantidad"));
                bean.setN_i_precio(rs.getFloat("n_i_precio"));
                bean.setN_i_total(rs.getFloat("n_i_total"));

                bean.setN_i_porc_dscto_1(rs.getFloat("n_i_porc_dscto_1"));
                bean.setN_i_porc_dscto_2(rs.getFloat("n_i_porc_dscto_2"));
                bean.setN_i_porc_dscto_3(rs.getFloat("n_i_porc_dscto_3"));
                bean.setN_i_porc_dscto_4(rs.getFloat("n_i_porc_dscto_4"));

                //lista.add(bean);
            }

            rs.close();
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return bean;

    }

    public List<DetPedido> detPedidoTraerPorCodigo(String pedidox) {

        List<DetPedido> lista = new ArrayList<DetPedido>();
        Connection cn = null;

        try {
            cn = AccesoDB.getConnection();
            String sql = "select tb_pedido_det.c_c_pedido , tb_pedido_det.n_i_secuencia , tb_pedido_det.c_c_articulo,c_t_articulo, n_i_cantidad , "
                    + "  n_i_precio = case when tb_pedido.c_c_moneda='01' then tb_pedido_det.n_i_pventa_nac_cigv else tb_pedido_det.n_i_pventa_ext_cigv  end  , "
                    + "   n_i_total = case when tb_pedido.c_c_moneda='01' then tb_pedido_det.n_i_total_item_nac else tb_pedido_det.n_i_total_item_ext end ,  "
                    + "   precio_bruto = case  when tb_pedido.c_c_moneda='01' then tb_pedido_det.n_i_precio_nac else tb_pedido_det.n_i_precio_ext end , "
                    + "   tb_pedido_det.n_i_porc_dscto_1 , tb_pedido_det.n_i_porc_dscto_2 , tb_pedido_det.n_i_porc_dscto_3 , tb_pedido_det.n_i_porc_dscto_4  "
                    + "	  from tb_pedido_det "
                    + "	       inner join tb_pedido on tb_pedido.c_c_pedido = tb_pedido_det.c_c_pedido "
                    + "	       inner join TB_articulo "
                    + "				on tb_articulo.c_c_articulo = TB_pedido_det.c_c_articulo  "
                    + "	  where  "
                    + "	         tb_pedido_det.c_c_pedido = ?   "
                    + "  order by tb_pedido_det.n_i_secuencia  ";

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, pedidox);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                DetPedido bean = new DetPedido();
                bean.setC_c_pedido(rs.getString("c_c_pedido"));
                bean.setN_i_secuencia(rs.getLong("n_i_secuencia"));
                bean.setC_c_articulo(rs.getString("c_c_articulo"));
                bean.setC_t_articulo(rs.getString("c_t_articulo"));
                bean.setN_i_cantidad(rs.getFloat("n_i_cantidad"));
                bean.setN_i_precio(rs.getFloat("n_i_precio"));
                bean.setN_i_total(rs.getFloat("n_i_total"));
                bean.setN_i_precio(rs.getFloat("precio_bruto"));
                bean.setN_i_porc_dscto_1(rs.getFloat("n_i_porc_dscto_1"));
                bean.setN_i_porc_dscto_2(rs.getFloat("n_i_porc_dscto_2"));
                bean.setN_i_porc_dscto_3(rs.getFloat("n_i_porc_dscto_3"));
                bean.setN_i_porc_dscto_4(rs.getFloat("n_i_porc_dscto_4"));
                lista.add(bean);
            }

            rs.close();
            pstm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;

    }

    public List<Pedido> PedidoTraerPorAnioMes(String aniox, String mesx , String codusuario) {

        List<Pedido> lista = new ArrayList<Pedido>();
        Connection cn = null;

        try {
            cn = AccesoDB.getConnection();
            String sql = "select c_c_pedido,c_n_pedido, d_dt_doc=convert(varchar(11),tb_pedido.d_dt_doc) , tb_cliente.c_t_cliente  "
                    + " , tb_moneda.c_t_moneda_abre ,   "
                    + "   n_i_total = case when tb_moneda.c_c_moneda='01' then tb_pedido.n_i_total_factura_nac else tb_pedido.n_i_total_factura_ext end   "
                    + "	  from tb_pedido "
                    + "	       inner join TB_moneda "
                    + "				on tb_moneda.c_c_moneda = TB_pedido.c_c_moneda  "
                    + "	       inner join TB_cliente "
                    + "				on tb_cliente.c_c_cliente = TB_pedido.c_c_cliente  "
                    + "	  where  "
                    + "	  tb_pedido.c_c_usuario_creador = ? and      year(tb_pedido.d_dt_doc)like ? and month(tb_pedido.d_dt_doc) like ? "
                    + " order by tb_cliente.c_t_cliente , tb_pedido.d_dt_doc	     ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, codusuario);
            pstm.setString(2, aniox + '%');
            pstm.setString(3, mesx + '%');

                        //System.out.println(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Pedido bean = new Pedido();
                bean.setC_c_pedido(rs.getString("c_c_pedido"));
                bean.setC_n_pedido(rs.getString("c_n_pedido"));
                bean.setD_dt_doc(rs.getString("d_dt_doc"));
                bean.setC_t_cliente(rs.getString("c_t_cliente"));
                bean.setC_t_moneda_abre(rs.getString("c_t_moneda_abre"));
                bean.setN_i_total(rs.getFloat("n_i_total"));
                lista.add(bean);
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;
    }

    public List<Pedido> PedidoTraerPorFechas(String iniciox, String finalx , String codusuario) {

        List<Pedido> lista = new ArrayList<Pedido>();
        Connection cn = null;

        try {
            cn = AccesoDB.getConnection();
            String sql = "select c_c_pedido,c_n_pedido, d_dt_doc=convert(varchar(11),tb_pedido.d_dt_doc) , tb_cliente.c_t_cliente  "
                    + " , tb_moneda.c_t_moneda_abre ,   "
                    + "   n_i_total = case when tb_moneda.c_c_moneda='01' then tb_pedido.n_i_total_factura_nac else tb_pedido.n_i_total_factura_ext end , "
                    + " c_c_facturado = case when n_i_total_arti <> n_i_por_facturar then 'Si' else 'No' end , "
                    + " c_t_anulado = case when n_fl_anulado=1 then 'ANULADO' else '' end  ,  c_t_fue_enviado = case when tb_pedido.n_fl_no_ver=1 then 'No'  else 'Si'  end  "
                    + "	  from tb_pedido "
                    + "	       inner join TB_moneda "
                    + "				on tb_moneda.c_c_moneda = TB_pedido.c_c_moneda  "
                    + "	       inner join TB_cliente "
                    + "				on tb_cliente.c_c_cliente = TB_pedido.c_c_cliente  "
                    + "	  where  "
                    + "	  tb_pedido.c_c_usuario_creador = ? and       convert(date,tb_pedido.d_dt_doc,103) >= convert(date,?,103) and convert(date,tb_pedido.d_dt_doc,103)  <=  convert(date,?,103) "
                    + " order by tb_cliente.c_t_cliente , tb_pedido.d_dt_doc	     ";
            PreparedStatement pstm = cn.prepareStatement(sql);
           // pstm.setString(1, Eureka.CODIGO_USUARIO);
           pstm.setString(1, codusuario); 
            pstm.setString(2, iniciox);
            pstm.setString(3, finalx);
       //     System.out.println("eurekausuario:"+Eureka.CODIGO_USUARIO);
//            System.out.println(iniciox);
//            System.out.println(finalx);
// ,  c_t_fue_enviado = case when tb_pedido.n_fl_no_ver=1 then 'No'  else 'Si'  end 
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Pedido bean = new Pedido();
                bean.setC_c_pedido(rs.getString("c_c_pedido"));
                bean.setC_n_pedido(rs.getString("c_n_pedido"));
                bean.setD_dt_doc(rs.getString("d_dt_doc"));
                bean.setC_t_cliente(rs.getString("c_t_cliente"));
                bean.setC_t_moneda_abre(rs.getString("c_t_moneda_abre"));
                bean.setN_i_total(rs.getFloat("n_i_total"));
                bean.setC_c_facturado(rs.getString("c_c_facturado"));
                bean.setC_t_anulado(rs.getString("c_t_anulado"));
                bean.setEnviado(rs.getString("c_t_fue_enviado"));
                lista.add(bean);
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;
    }

    public static String generarcodigo() {
        String num = null;
        int num1;
        try {

            String sql = "select max(chr_cliecodigo) from cliente";
            Connection cn = AccesoDB.getConnection();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                num = rs.getString(1);
                num1 = Integer.parseInt(num) + 1;
                num = "0000" + String.valueOf(num1);
                num = num.substring(num.length() - 5, num.length());

            }

        } catch (SQLException ex) {
            System.out.println("error en el modelo cliente generara codigo");
        }

        return num;
    }

    public String insertar(Pedido bean,String codusuario) {

        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
//                System.out.println("aa1"  );                
            // Calcular c_n_pedido
            String sql = "select xtemp = isnull(max(convert(numeric(7),substring(c_n_pedido,4,7))) ,0) "
                    + "from tb_pedido ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Al calcular el numero de pedido.");
            }
            Long xtempn = rs.getLong("xtemp");
            String xc_n_pedido;
            xc_n_pedido = String.valueOf(xtempn + 1);
            if (xc_n_pedido.length() == 6) {
                xc_n_pedido = "0" + xc_n_pedido;
            } else if (xc_n_pedido.length() == 5) {
                xc_n_pedido = "00" + xc_n_pedido;
            } else if (xc_n_pedido.length() == 4) {
                xc_n_pedido = "000" + xc_n_pedido;
            } else if (xc_n_pedido.length() == 3) {
                xc_n_pedido = "0000" + xc_n_pedido;
            } else if (xc_n_pedido.length() == 2) {
                xc_n_pedido = "00000" + xc_n_pedido;
            } else if (xc_n_pedido.length() == 1) {
                xc_n_pedido = "000000" + xc_n_pedido;
            }
            xc_n_pedido = "001" + xc_n_pedido;
            rs.close();
            pstm.close();

            // Obtener el vendedor del usuario
            System.out.println("paso0");
            sql = "select vendx = c_c_vendedor_asignado "
                    + " from tb_usuario where c_c_usuario= ? ";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, codusuario );
            rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Al obtener el vendedor.");
            }
            String vendxx = rs.getString("vendx");
            rs.close();
            pstm.close();

            // Calcular c_c_pedido
            sql = "select xtemp = c_c_valor "
                    + "from tb_parametros where c_c_parametros='029' ";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Al calcular el numero interno del pedido.");
            }
            String xtempx = rs.getString("xtemp");
            String xc_c_pedido = String.valueOf(Long.valueOf(xtempx) + 1);
            rs.close();
            pstm.close();
            // Grabar el correlativo de c_c_pedido en parametros
            sql = "update tb_parametros "
                    + "set c_c_valor = ?   "
                    + "where c_c_parametros = '029'";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, xc_c_pedido);
            pstm.executeUpdate();
            pstm.close();
            // Insertar
            sql = "insert TB_pedido(c_c_cliente,c_c_lugar,d_dt_entrega,d_dt_doc,c_c_pedido,c_n_pedido,"
                    + "c_c_tipo_doc_sal,c_c_almacen,c_c_vendedor,c_c_moneda,c_c_usuario_creador,"
                    + "c_c_tipo_pago,n_i_tcambio_venta,c_c_tipo_salida ,c_t_observacion ,n_fl_aprobado , n_fl_no_ver )"
                    + " values (?,?,?,getdate(),?,?,"
                    + " '13','01',?,?,?,"
                    + "?,1,'01' ,? ,1 ,1 )";
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, bean.getC_c_cliente());
            pr.setString(2, bean.getC_c_lugar());
            pr.setString(3, bean.getD_dt_entrega());
            pr.setString(4, xc_c_pedido);
            pr.setString(5, xc_n_pedido);

            pr.setString(6, vendxx);
            pr.setString(7, bean.getC_c_moneda());
            pr.setString(8, codusuario);
            pr.setString(9, bean.getC_c_tipo_pago());
            pr.setString(10, bean.getC_t_observacion());
            pr.executeUpdate();
            pr.close();
            cn.commit();
            return xc_c_pedido;
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

    }

    public void insertardet(DetPedido bean) {

        Connection cn = null;
        try {

            System.out.println("ingreso det insertar TTTTTTT");

            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);

            // Grabar el correlativo de c_c_pedido en parametros
            Long ln_secuencia = null;
            String sql = null;
            Float lfl_pventa_nac_cigv = null;
            Float lfl_pventa_ext_cigv = null;
            Float lfl_pventa_ext = null;
            Float lfl_pventa_nac = null;
            Float lfl_tot_item_nac = null;
            Float lfl_tot_item_ext = null;
            Float lfl_tot_nac = null;
            Float lfl_tot_ext = null;
            Float lde_porc_igv = null;
            Float lde_tcambio = null;

            // Calcular secuencia
            sql = "select xtemp = max(n_i_secuencia) "
                    + "from tb_pedido_det where c_c_pedido=? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getC_c_pedido());
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Al calcular la secuencia del detalle de pedido.");
            }
            String xtempx = rs.getString("xtemp");

            if (xtempx == null) {
                xtempx = "0";
            }

            ln_secuencia = Long.valueOf(xtempx) + 1;
            rs.close();
            pstm.close();

            // Calcular valores detalle de pedido
            lde_porc_igv = Float.valueOf("1.18");
            lde_tcambio = Float.valueOf("1");
            if (bean.getC_c_moneda() == "01") {
                lfl_pventa_nac_cigv = bean.getN_i_precio();
                lfl_pventa_nac = bean.getN_i_precio() / lde_porc_igv;
                lfl_pventa_ext_cigv = lfl_pventa_nac_cigv / lde_tcambio;
                lfl_pventa_ext = lfl_pventa_ext_cigv / lde_tcambio;
            } else {
                lfl_pventa_ext_cigv = bean.getN_i_precio();
                lfl_pventa_ext = bean.getN_i_precio() / lde_porc_igv;
                lfl_pventa_nac_cigv = lfl_pventa_ext_cigv * lde_tcambio;
                lfl_pventa_nac = lfl_pventa_ext * lde_tcambio;
            }
            lfl_tot_item_nac = lfl_pventa_nac_cigv * bean.getN_i_cantidad();
            lfl_tot_item_ext = lfl_pventa_ext_cigv * bean.getN_i_cantidad();
            lfl_tot_nac = lfl_pventa_nac * bean.getN_i_cantidad();
            lfl_tot_ext = lfl_pventa_ext * bean.getN_i_cantidad();

            // Insertar
            sql = "insert TB_pedido_det(c_c_pedido,c_c_articulo,n_i_secuencia,n_i_cantidad,n_i_pventa_nac_cigv,n_i_pventa_ext_cigv,"
                    + "n_i_pventa_nac,n_i_pventa_ext,n_i_total_item_nac,n_i_total_item_ext,n_i_total_nac,n_i_total_ext  ,  "
                    + " n_i_porc_dscto_1 , n_i_porc_dscto_2 , n_i_porc_dscto_3 , n_i_porc_dscto_4 ) "
                    + " values (?,?,?,?,?,?,"
                    + " ?,?,?,?,?,?,"
                    + " ?,?,?,? )";
            PreparedStatement pr = cn.prepareStatement(sql);

//                System.out.println("ssssssssssss");
//                System.out.println(bean.getC_c_pedido());
//                System.out.println(bean.getC_c_articulo());
//                System.out.println(bean.getN_i_secuencia());
//                System.out.println("sssssssssssss1");
            pr.setString(1, bean.getC_c_pedido());
            pr.setString(2, bean.getC_c_articulo());
            pr.setLong(3, ln_secuencia);
            pr.setFloat(4, bean.getN_i_cantidad());
            pr.setFloat(5, lfl_pventa_nac_cigv);
            pr.setFloat(6, lfl_pventa_ext_cigv);
            pr.setFloat(7, lfl_pventa_nac);
            pr.setFloat(8, lfl_pventa_ext);
            pr.setFloat(9, lfl_tot_item_nac);
            pr.setFloat(10, lfl_tot_item_ext);
            pr.setFloat(11, lfl_tot_nac);
            pr.setFloat(12, lfl_tot_ext);

            pr.setFloat(13, bean.getN_i_porc_dscto_1());
            pr.setFloat(14, bean.getN_i_porc_dscto_2());
            pr.setFloat(15, bean.getN_i_porc_dscto_3());
            pr.setFloat(16, bean.getN_i_porc_dscto_4());

            pr.executeUpdate();
            pr.close();

            // calcular total
            sql = "update tb_pedido set n_i_total_factura_nac = (select sum(n_i_total_item_nac) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ), "
                    + "   n_i_total_factura_ext = (select sum(n_i_total_item_ext) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ) "
                    + "   where c_c_pedido=? ";
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setString(1, bean.getC_c_pedido());
            pt.executeUpdate();
            pt.close();

            cn.commit();

        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

    }

    public void actualizar(Pedido bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            
              // Actualizar
            String sql = "update TB_pedido set  c_c_cliente=?,c_c_lugar=?,d_dt_entrega=?, "
                    + "  c_c_moneda=?, "
                    + "c_c_tipo_pago=?,  c_t_observacion=?   "
                    + " where c_c_pedido=?  ";
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, bean.getC_c_cliente());
            pr.setString(2, bean.getC_c_lugar());
            pr.setString(3, bean.getD_dt_entrega());
            pr.setString(4, bean.getC_c_moneda());
            pr.setString(5, bean.getC_c_tipo_pago());
            pr.setString(6, bean.getC_t_observacion());
            pr.setString(7, bean.getC_c_pedido());
            pr.executeUpdate();
            pr.close();
            cn.commit();
            
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
    }

    
        public void quitar_no_ver(String pedidox) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            System.out.println("bb1");
              // Actualizar
            String sql = "update TB_pedido set  n_fl_no_ver=0  "
                    + " where c_c_pedido=?  ";
            System.out.println("bb2");
            PreparedStatement pr = cn.prepareStatement(sql);
            System.out.println("bb3");
            pr.setString(1, pedidox);
            System.out.println("bb4");
            pr.executeUpdate();
            pr.close();
            cn.commit();
            
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
    }

    
    
    
    public void actualizardet(DetPedido bean) {

        Connection cn = null;
        try {

            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);

            // Grabar el correlativo de c_c_pedido en parametros
            Long ln_secuencia = null;
            String sql = null;
            Float lfl_pventa_nac_cigv = null;
            Float lfl_pventa_ext_cigv = null;
            Float lfl_pventa_ext = null;
            Float lfl_pventa_nac = null;
            Float lfl_tot_item_nac = null;
            Float lfl_tot_item_ext = null;
            Float lfl_tot_nac = null;
            Float lfl_tot_ext = null;
            Float lde_porc_igv = null;
            Float lde_tcambio = null;
            Float lde_precio_nac = null;
            Float lde_precio_ext = null;

            Float a1 = (100 - bean.getN_i_porc_dscto_1());
            Float b1 = a1 - (a1 * bean.getN_i_porc_dscto_2() / 100);
            Float c1 = b1 - (b1 * bean.getN_i_porc_dscto_3() / 100);
            Float d1 = c1 - (c1 * bean.getN_i_porc_dscto_4() / 100);
            d1 = 100 - d1;

            // Calcular valores
            lde_porc_igv = Float.valueOf("1.18");
            lde_tcambio = Float.valueOf("1");
            if (bean.getC_c_moneda() == "01") {
                lde_precio_nac = bean.getN_i_precio() - (bean.getN_i_precio() * d1 / 100);
                lfl_pventa_nac = lde_precio_nac;
                lfl_pventa_nac_cigv = lde_precio_nac * lde_porc_igv;
                lde_precio_ext = lde_precio_nac / lde_tcambio;
                lfl_pventa_ext_cigv = lfl_pventa_nac_cigv / lde_tcambio;
                lfl_pventa_ext = lfl_pventa_ext_cigv / lde_tcambio;
            } else {
                lde_precio_ext = bean.getN_i_precio() - (bean.getN_i_precio() * d1 / 100);
                lfl_pventa_ext = lde_precio_ext;
                lfl_pventa_ext_cigv = lde_precio_ext * lde_porc_igv;
                lde_precio_nac = lde_precio_ext * lde_tcambio;
                lfl_pventa_nac_cigv = lfl_pventa_ext_cigv * lde_tcambio;
                lfl_pventa_nac = lfl_pventa_ext * lde_tcambio;
            }
            lfl_tot_item_nac = lfl_pventa_nac_cigv * bean.getN_i_cantidad();
            lfl_tot_item_ext = lfl_pventa_ext_cigv * bean.getN_i_cantidad();
            lfl_tot_nac = lfl_pventa_nac * bean.getN_i_cantidad();
            lfl_tot_ext = lfl_pventa_ext * bean.getN_i_cantidad();

            // Insertar
            sql = "update TB_pedido_det set  c_c_articulo = ?,n_i_cantidad =?,n_i_pventa_nac_cigv=?,n_i_pventa_ext_cigv=?,"
                    + " n_i_pventa_nac=?,n_i_pventa_ext=?,n_i_total_item_nac=?,n_i_total_item_ext=?,n_i_total_nac=?,n_i_total_ext=? ,  "
                    + " n_i_porc_dscto_1=? , n_i_porc_dscto_2=? , n_i_porc_dscto_3=? , n_i_porc_dscto_4=? "
                    + " n_i_precio_nac =? , n_i_precio_ext=? "
                    + " where c_c_pedido=? and c_c_articulo=? and n_i_secuencia=?  "
                    + "  ";
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, bean.getC_c_articulo());

            pr.setFloat(2, bean.getN_i_cantidad());
            pr.setFloat(3, lfl_pventa_nac_cigv);
            pr.setFloat(4, lfl_pventa_ext_cigv);
            pr.setFloat(5, lfl_pventa_nac);
            pr.setFloat(6, lfl_pventa_ext);
            pr.setFloat(7, lfl_tot_item_nac);
            pr.setFloat(8, lfl_tot_item_ext);
            pr.setFloat(9, lfl_tot_nac);
            pr.setFloat(10, lfl_tot_ext);

            pr.setFloat(11, bean.getN_i_porc_dscto_1());
            pr.setFloat(12, bean.getN_i_porc_dscto_2());
            pr.setFloat(13, bean.getN_i_porc_dscto_3());
            pr.setFloat(14, bean.getN_i_porc_dscto_4());

            pr.setFloat(15, lde_precio_nac);
            pr.setFloat(16, lde_precio_ext);

            pr.setString(17, bean.getC_c_pedido());
            pr.setString(18, bean.getC_c_articulo());
            pr.setLong(19, bean.getN_i_secuencia());

            pr.executeUpdate();
            pr.close();

            // calcular total
            sql = "update tb_pedido set n_i_total_factura_nac = (select sum(n_i_total_item_nac) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ), "
                    + "   n_i_total_factura_ext = (select sum(n_i_total_item_ext) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ) "
                    + "   where c_c_pedido=? ";
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setString(1, bean.getC_c_pedido());
            pt.executeUpdate();
            pt.close();

            cn.commit();

        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

    }

    public void insertar_actualizar_det(DetPedido bean, String insert_o_update) {

        Connection cn = null;
        try {

            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);

            // Grabar el correlativo de c_c_pedido en parametros
            Long ln_secuencia = null;
            String sql = null;
            Float lfl_pventa_nac_cigv = null;
            Float lfl_pventa_ext_cigv = null;
            Float lfl_pventa_ext = null;
            Float lfl_pventa_nac = null;
            Float lfl_tot_item_nac = null;
            Float lfl_tot_item_ext = null;
            Float lfl_tot_nac = null;
            Float lfl_tot_ext = null;
            Float lde_porc_igv = null;
            Float lde_tcambio = null;
            Float lde_precio_nac = null;
            Float lde_precio_ext = null;

            Float a1 = (100 - bean.getN_i_porc_dscto_1());
            Float b1 = a1 - (a1 * bean.getN_i_porc_dscto_2() / 100);
            Float c1 = b1 - (b1 * bean.getN_i_porc_dscto_3() / 100);
            Float d1 = c1 - (c1 * bean.getN_i_porc_dscto_4() / 100);
            d1 = 100 - d1;

            // Calcular valores
            lde_porc_igv = Float.valueOf("1.18");
            lde_tcambio = Float.valueOf("1");
            Pedido bean2;

            bean2 = PedidoTraerPorCodigo(bean.getC_c_pedido());

            bean.setC_c_moneda(bean2.getC_c_moneda());
            if (bean.getC_c_moneda().equals("01")) {
                lde_precio_nac = bean.getN_i_precio();
                lfl_pventa_nac = bean.getN_i_precio() - (bean.getN_i_precio() * d1 / 100);
                lfl_pventa_nac_cigv = lfl_pventa_nac * lde_porc_igv;
                lde_precio_ext = lfl_pventa_nac / lde_tcambio;
                lfl_pventa_ext_cigv = lfl_pventa_nac_cigv / lde_tcambio;
                lfl_pventa_ext = lfl_pventa_ext_cigv / lde_tcambio;
            } else {
                System.out.println(bean.getN_i_precio());
                lde_precio_ext = bean.getN_i_precio();
                lfl_pventa_ext = bean.getN_i_precio() - (bean.getN_i_precio() * d1 / 100);
                lfl_pventa_ext_cigv = lfl_pventa_ext * lde_porc_igv;
                lde_precio_nac = lfl_pventa_ext * lde_tcambio;
                lfl_pventa_nac_cigv = lfl_pventa_ext_cigv * lde_tcambio;
                lfl_pventa_nac = lfl_pventa_ext * lde_tcambio;
            }
            lfl_tot_item_nac = lfl_pventa_nac_cigv * bean.getN_i_cantidad();
            lfl_tot_item_ext = lfl_pventa_ext_cigv * bean.getN_i_cantidad();
            lfl_tot_nac = lfl_pventa_nac * bean.getN_i_cantidad();
            lfl_tot_ext = lfl_pventa_ext * bean.getN_i_cantidad();
            PreparedStatement pr;

            // Insertar o update 
            if (insert_o_update == "I") {
                sql = "insert TB_pedido_det(c_c_pedido,c_c_articulo,n_i_secuencia,n_i_cantidad,n_i_pventa_nac_cigv,n_i_pventa_ext_cigv,"
                        + "n_i_pventa_nac,n_i_pventa_ext,n_i_total_item_nac,n_i_total_item_ext,n_i_total_nac,n_i_total_ext  ,  "
                        + " n_i_porc_dscto_1 , n_i_porc_dscto_2 , n_i_porc_dscto_3 , n_i_porc_dscto_4 ,n_i_precio_nac  , n_i_precio_ext   ) "
                        + " values (?,?,?,?,?,?,"
                        + " ?,?,?,?,?,?,"
                        + " ?,?,?,?,?,? )";

                pr = cn.prepareStatement(sql);
                pr.setString(1, bean.getC_c_pedido());
                pr.setString(2, bean.getC_c_articulo());

                // Calcular secuencia
                sql = "select xtemp = max(n_i_secuencia) "
                        + "from tb_pedido_det where c_c_pedido=? ";
                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setString(1, bean.getC_c_pedido());
                ResultSet rs = pstm.executeQuery();
                if (!rs.next()) {
                    throw new SQLException("Al calcular la secuencia del detalle de pedido.");
                }
                String xtempx = rs.getString("xtemp");

                if (xtempx == null) {
                    xtempx = "0";
                }

                ln_secuencia = Long.valueOf(xtempx) + 1;
                rs.close();
                pstm.close();

                pr.setLong(3, ln_secuencia);

                pr.setFloat(4, bean.getN_i_cantidad());
                pr.setFloat(5, lfl_pventa_nac_cigv);
                pr.setFloat(6, lfl_pventa_ext_cigv);

                pr.setFloat(7, lfl_pventa_nac);
                pr.setFloat(8, lfl_pventa_ext);
                pr.setFloat(9, lfl_tot_item_nac);
                pr.setFloat(10, lfl_tot_item_ext);
                pr.setFloat(11, lfl_tot_nac);
                pr.setFloat(12, lfl_tot_ext);

                pr.setFloat(13, bean.getN_i_porc_dscto_1());
                pr.setFloat(14, bean.getN_i_porc_dscto_2());
                pr.setFloat(15, bean.getN_i_porc_dscto_3());
                pr.setFloat(16, bean.getN_i_porc_dscto_4());

                pr.setFloat(17, lde_precio_nac);
                pr.setFloat(18, lde_precio_ext);

            } else {
                sql = "update TB_pedido_det set  c_c_articulo = ?,n_i_cantidad =?,n_i_pventa_nac_cigv=?,n_i_pventa_ext_cigv=?,"
                        + " n_i_pventa_nac=?,n_i_pventa_ext=?,n_i_total_item_nac=?,n_i_total_item_ext=?,n_i_total_nac=?,n_i_total_ext=? ,  "
                        + " n_i_porc_dscto_1=? , n_i_porc_dscto_2=? , n_i_porc_dscto_3=? , n_i_porc_dscto_4=? , "
                        + " n_i_precio_nac =? , n_i_precio_ext=? "
                        + " where c_c_pedido=? and c_c_articulo=? and n_i_secuencia=?  "
                        + "  ";
                pr = cn.prepareStatement(sql);
                pr.setString(1, bean.getC_c_articulo());

                pr.setFloat(2, bean.getN_i_cantidad());
                pr.setFloat(3, lfl_pventa_nac_cigv);
                pr.setFloat(4, lfl_pventa_ext_cigv);
                pr.setFloat(5, lfl_pventa_nac);
                pr.setFloat(6, lfl_pventa_ext);
                pr.setFloat(7, lfl_tot_item_nac);
                pr.setFloat(8, lfl_tot_item_ext);
                pr.setFloat(9, lfl_tot_nac);
                pr.setFloat(10, lfl_tot_ext);

                pr.setFloat(11, bean.getN_i_porc_dscto_1());
                pr.setFloat(12, bean.getN_i_porc_dscto_2());
                pr.setFloat(13, bean.getN_i_porc_dscto_3());
                pr.setFloat(14, bean.getN_i_porc_dscto_4());

                pr.setFloat(15, lde_precio_nac);
                pr.setFloat(16, lde_precio_ext);

                pr.setString(17, bean.getC_c_pedido());
                pr.setString(18, bean.getC_c_articulo());
                pr.setLong(19, bean.getN_i_secuencia());

            }

            pr.executeUpdate();
            pr.close();

            // calcular total
            sql = "update tb_pedido set n_i_total_factura_nac = (select sum(n_i_total_item_nac) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ), "
                    + "   n_i_total_factura_ext = (select sum(n_i_total_item_ext) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ) ,  "
                    + "      n_i_total_arti = (select sum(n_i_cantidad) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido )  "
                    + "   where c_c_pedido=? ";
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setString(1, bean.getC_c_pedido());
            pt.executeUpdate();
            pt.close();

            cn.commit();

        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

    }

    // inserta en el pedido la oferta seleccionada
    public String EliminardetPedido(String pedidox, String articulox, Long secuenciax) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);

                // Insertar
            String sql = "delete TB_pedido_det where c_c_pedido=? and c_c_articulo = ? and n_i_secuencia=?      ";

            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, pedidox);
            pr.setString(2, articulox);
            pr.setLong(3, secuenciax);

            pr.executeUpdate();
            pr.close();

            // calcular total
            sql = "update tb_pedido set n_i_total_factura_nac = (select sum(n_i_total_item_nac) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ), "
                    + "   n_i_total_factura_ext = (select sum(n_i_total_item_ext) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido ) , "
                    + "      n_i_total_arti = (select sum(n_i_cantidad) from tb_pedido_det where c_c_pedido=tb_pedido.c_c_pedido )  "
                    + "   where c_c_pedido=? ";
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setString(1, pedidox);
            pt.executeUpdate();
            pt.close();

            cn.commit();

            return pedidox;
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }

    }

}
