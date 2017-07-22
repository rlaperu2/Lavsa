 
package domain;

import java.io.Serializable;

 
public class CtaxCobrar implements   Serializable, Cloneable {
    private String c_t_tipo_doc_abrev;
    private String c_t_documento;
    private String d_dt_documento;
    private String d_dt_vencimiento;
    private String c_t_moneda_abre;
    private Float n_i_total;
    private Float n_i_saldo;
    private Cliente cliente;
    private String n_intbanco;
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getN_intbanco() {
        return n_intbanco;
    }

    public void setN_intbanco(String n_intbanco) {
        this.n_intbanco = n_intbanco;
    }

 

 

    public String getC_t_moneda_abre() {
        return c_t_moneda_abre;
    }

    public void setC_t_moneda_abre(String c_t_moneda_abre) {
        this.c_t_moneda_abre = c_t_moneda_abre;
    }

    public String getC_t_tipo_doc_abrev() {
        return c_t_tipo_doc_abrev;
    }

    public void setC_t_tipo_doc_abrev(String c_t_tipo_doc_abrev) {
        this.c_t_tipo_doc_abrev = c_t_tipo_doc_abrev;
    }

    public String getC_t_documento() {
        return c_t_documento;
    }

    public void setC_t_documento(String c_t_documento) {
        this.c_t_documento = c_t_documento;
    }

    public String getD_dt_documento() {
        return d_dt_documento;
    }

    public void setD_dt_documento(String d_dt_documento) {
        this.d_dt_documento = d_dt_documento;
    }

    public String getD_dt_vencimiento() {
        return d_dt_vencimiento;
    }

    public void setD_dt_vencimiento(String d_dt_vencimiento) {
        this.d_dt_vencimiento = d_dt_vencimiento;
    }

    public Float getN_i_total() {
        return n_i_total;
    }

    public void setN_i_total(Float n_i_total) {
        this.n_i_total = n_i_total;
    }

    public Float getN_i_saldo() {
        return n_i_saldo;
    }

    public void setN_i_saldo(Float n_i_saldo) {
        this.n_i_saldo = n_i_saldo;
    }
    
    
    
}
