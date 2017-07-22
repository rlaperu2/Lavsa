package domain;

public class Articulo {

	private String c_c_articulo;
	private String c_t_articulo;
	private String c_t_uni_abre;
	private float precio;
	private String moneda;
	private String c_t_linea;
	private String c_t_familia;
	private String n_i_stock;
	private float n_fl_archivo_grafico; 
                 
	public Articulo(){
		
	}

        public void setN_fl_archivo_grafico(float n_fl_archivo_grafico) {
            this.n_fl_archivo_grafico = n_fl_archivo_grafico;
        }

        public float getN_fl_archivo_grafico() {
            return n_fl_archivo_grafico;
        }
	
	public String getC_c_articulo() {
		return c_c_articulo;
	}
	public void setC_c_articulo(String c_c_articulo) {
		this.c_c_articulo = c_c_articulo;
	}
	public String getC_t_articulo() {
		return c_t_articulo;
	}
	public void setC_t_articulo(String c_t_articulo) {
		this.c_t_articulo = c_t_articulo;
	}

	public String getC_t_uni_abre() {
		return c_t_uni_abre;
	}

	public void setC_t_uni_abre(String c_t_uni_abre) {
		this.c_t_uni_abre = c_t_uni_abre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
 

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
 
	public String getC_t_linea() {
		return c_t_linea;
	}

	public void setC_t_linea(String c_t_linea) {
		this.c_t_linea = c_t_linea;
	}

	public String getC_t_familia() {
		return c_t_familia;
	}

	public void setC_t_familia(String c_t_familia) {
		this.c_t_familia = c_t_familia;
	}
	

        
        public String getN_i_stock() {
		return n_i_stock;
	}

	public void setN_i_stock(String n_i_stock) {
		this.n_i_stock = n_i_stock;
	}
}
