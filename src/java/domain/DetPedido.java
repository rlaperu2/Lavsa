package domain;

public class DetPedido {

    private String c_c_pedido;
    private String c_c_articulo;
    private String c_t_articulo;
    private Float n_i_cantidad;
    private Float n_i_precio;
    private Float n_i_total;
    private Long  n_i_secuencia;
    private String c_c_moneda;
    private Float n_i_porc_dscto_1;
    private Float n_i_porc_dscto_2;
    private Float n_i_porc_dscto_3;
    private Float n_i_porc_dscto_4;
    private Float n_i_precio_nac;
    private Float n_i_precio_ext;
    
    public DetPedido(){

    }

    public Float getN_i_precio_nac() {
        return n_i_precio_nac;
    }

    public void setN_i_precio_nac(Float n_i_precio_nac) {
        this.n_i_precio_nac = n_i_precio_nac;
    }

    public Float getN_i_precio_ext() {
        return n_i_precio_ext;
    }

    public void setN_i_precio_ext(Float n_i_precio_ext) {
        this.n_i_precio_ext = n_i_precio_ext;
    }

    
    
    public Float getN_i_porc_dscto_1() {
        return n_i_porc_dscto_1;
    }

    public void setN_i_porc_dscto_1(Float n_i_porc_dscto_1) {
        this.n_i_porc_dscto_1 = n_i_porc_dscto_1;
    }

    public Float getN_i_porc_dscto_2() {
        return n_i_porc_dscto_2;
    }

    public void setN_i_porc_dscto_2(Float n_i_porc_dscto_2) {
        this.n_i_porc_dscto_2 = n_i_porc_dscto_2;
    }

    public Float getN_i_porc_dscto_3() {
        return n_i_porc_dscto_3;
    }

    public void setN_i_porc_dscto_3(Float n_i_porc_dscto_3) {
        this.n_i_porc_dscto_3 = n_i_porc_dscto_3;
    }

    public Float getN_i_porc_dscto_4() {
        return n_i_porc_dscto_4;
    }

    public void setN_i_porc_dscto_4(Float n_i_porc_dscto_4) {
        this.n_i_porc_dscto_4 = n_i_porc_dscto_4;
    }

    public void setC_c_moneda(String c_c_moneda) {
        this.c_c_moneda = c_c_moneda;
    }

    public String getC_c_moneda() {
        return c_c_moneda;
    }

    public void setN_i_secuencia(Long n_i_secuencia) {
        this.n_i_secuencia = n_i_secuencia;
    }    
    
    public void setC_c_pedido(String c_c_pedido) {
        this.c_c_pedido = c_c_pedido;
    }

    public void setC_c_articulo(String c_c_articulo) {
        this.c_c_articulo = c_c_articulo;
    }

    public void setC_t_articulo(String c_t_articulo) {
        this.c_t_articulo = c_t_articulo;
    }

    public void setN_i_cantidad(Float n_i_cantidad) {
        this.n_i_cantidad = n_i_cantidad;
    }

    public void setN_i_precio(Float n_i_precio) {
        this.n_i_precio = n_i_precio;
    }

    public void setN_i_total(Float n_i_total) {
        this.n_i_total = n_i_total;
    }

    public Long getN_i_secuencia() {
        return n_i_secuencia;
    }
    
    
    public String getC_c_pedido() {
        return c_c_pedido;
    }

    public String getC_c_articulo() {
        return c_c_articulo;
    }

    public String getC_t_articulo() {
        return c_t_articulo;
    }

    public Float getN_i_cantidad() {
        return n_i_cantidad;
    }

    public Float getN_i_precio() {
        return n_i_precio;
    }

    public Float getN_i_total() {
        return n_i_total;
    }
    
    
    
	
}
