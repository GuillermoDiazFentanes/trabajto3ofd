package main.java.etsisi.ems.trabajo3.banco;

/**
 * Created by bn0101 on 10/05/2019.
 */
public class datosBancarios { 
    private int marcainternacional;
    private String nombreentidad;
    public datosBancarios(int marcainternacional, String nombreentidad){
        this.marcainternacional = marcainternacional;
        this.nombreentidad = nombreentidad;
    }

    public int getMarcainternacional() {
        return marcainternacional;
    }

    public void setMarcainternacional(int marcainternacional) {
        this.marcainternacional = marcainternacional;
    }

    public String getNombreentidad() {
        return nombreentidad;
    }

    public void setNombreentidad(String nombreentidad) {
        this.nombreentidad = nombreentidad;
    }
}
