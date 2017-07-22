 
package domain;
 
import java.io.Serializable;
 

public class Comisiones  implements   Serializable, Cloneable {
     private CtaxCobrar documento;
     private Float comision;

    public CtaxCobrar getDocumento() {
        return documento;
    }

    public void setDocumento(CtaxCobrar documento) {
        this.documento = documento;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

     
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Comisiones objetoADevolver=(Comisiones)super.clone();
        if(objetoADevolver.getDocumento()!=null){objetoADevolver.setDocumento((CtaxCobrar)objetoADevolver.getDocumento().clone());} 
        return objetoADevolver; //To change body of generated methods, choose Tools | Templates. 
    }

   
     
}
