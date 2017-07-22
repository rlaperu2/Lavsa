package domain;

public class Pedido {

    private String c_c_pedido;
    private String c_n_pedido;
    private String d_dt_doc;
    private String c_t_cliente;
    private String c_t_moneda_abre;
    private float n_i_total;
    private String c_c_cliente;
    private String c_c_ruc;
    private String d_dt_entrega;
    private String c_c_vendedor;
    private String c_c_usuario_creador;
    private String c_c_moneda;
    private String c_c_lugar;
    private String c_c_tipo_pago;
    private String c_t_observacion;
    private String c_c_facturado;
    private String c_t_anulado;
    private String enviado;
    
    public Pedido(){

    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getC_t_anulado() {
        return c_t_anulado;
    }

    public void setC_t_anulado(String c_t_anulado) {
        this.c_t_anulado = c_t_anulado;
    }

    public String getC_c_facturado() {
        return c_c_facturado;
    }

    public void setC_c_facturado(String c_c_facturado) {
        this.c_c_facturado = c_c_facturado;
    }

    
    
    public String getC_t_observacion() {
        return c_t_observacion;
    }

    public void setC_t_observacion(String c_t_observacion) {
        this.c_t_observacion = c_t_observacion;
    }

    
    public String getC_c_tipo_pago() {
        return c_c_tipo_pago;
    }

    public void setC_c_tipo_pago(String c_c_tipo_pago) {
        this.c_c_tipo_pago = c_c_tipo_pago;
    }
    
    
    public String getC_c_moneda() {
        return c_c_moneda;
    }

    public void setC_c_moneda(String c_c_moneda) {
        this.c_c_moneda = c_c_moneda;
    }

    public String getC_c_lugar() {
        return c_c_lugar;
    }

    public void setC_c_lugar(String c_c_lugar) {
        this.c_c_lugar = c_c_lugar;
    }
    

    
    
    public void setC_c_usuario_creador(String c_c_usuario_creador) {
        this.c_c_usuario_creador = c_c_usuario_creador;
    }        
    
    public void setC_c_vendedor(String c_c_vendedor) {
        this.c_c_vendedor = c_c_vendedor;
    }
    
    
    public void setD_dt_entrega(String d_dt_entrega) {
        this.d_dt_entrega = d_dt_entrega;
    }

    public void setC_c_pedido(String c_c_pedido) {
        this.c_c_pedido = c_c_pedido;
    }

    public void setC_n_pedido(String c_n_pedido) {
        this.c_n_pedido = c_n_pedido;
    }

    public void setD_dt_doc(String d_dt_doc) {
        this.d_dt_doc = d_dt_doc;
    }

    public void setC_t_cliente(String c_t_cliente) {
        this.c_t_cliente = c_t_cliente;
    }

    public void setC_t_moneda_abre(String c_t_moneda_abre) {
        this.c_t_moneda_abre = c_t_moneda_abre;
    }

    public void setN_i_total(float n_i_total) {
        this.n_i_total = n_i_total;
    }

    public void setC_c_cliente(String c_c_cliente) {
        this.c_c_cliente = c_c_cliente;
    }    
    
    public void setC_c_ruc(String c_c_ruc) {
        this.c_c_ruc = c_c_ruc;
    }    
    
    //
    
    public String getC_c_usuario_creador() {
        return c_c_usuario_creador;
    }
    
    public String getC_c_vendedor() {
        return c_c_vendedor;
    }
    
    public String getD_dt_entrega() {
        return d_dt_entrega;
    }

    
    public String getC_c_pedido() {
        return c_c_pedido;
    }

    public String getC_n_pedido() {
        return c_n_pedido;
    }

    public String getD_dt_doc() {
        return d_dt_doc;
    }

    public String getC_t_cliente() {
        return c_t_cliente;
    }

    public String getC_t_moneda_abre() {
        return c_t_moneda_abre;
    }

    public float getN_i_total() {
        return n_i_total;
    }
	
    public String getC_c_cliente() {
        return c_c_cliente;
    }
    
    public String getC_c_ruc() {
        return c_c_ruc;
    }    
	
}
