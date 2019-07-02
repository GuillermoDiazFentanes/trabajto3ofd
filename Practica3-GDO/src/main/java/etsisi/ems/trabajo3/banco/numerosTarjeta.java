package main.java.etsisi.ems.trabajo3.banco;

import java.time.LocalDate;

/**
 * Created by bn0101 on 10/05/2019.
 */
public class numerosTarjeta {
    private String numero;
    private LocalDate fechacaducidad;
    int ccv;
    public numerosTarjeta(String numero, LocalDate fechacaducidad, int ccv){
        this.numero = numero;
        this.fechacaducidad = fechacaducidad;
        this.ccv = ccv;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFechacaducidad() {
        return fechacaducidad;
    }

    public void setFechacaducidad(LocalDate fechacaducidad) {
        this.fechacaducidad = fechacaducidad;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }
}
