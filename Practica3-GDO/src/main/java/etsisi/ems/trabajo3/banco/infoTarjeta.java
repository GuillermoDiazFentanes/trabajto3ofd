package main.java.etsisi.ems.trabajo3.banco;

import java.util.Vector;

/**
 * Created by bn0101 on 10/05/2019.
 */
public class infoTarjeta {
    private String titular;
    private double credito;
    private int tipo;
    public infoTarjeta(String titular, double credito){
        this.titular = titular;
        this.credito = credito;
    }
    public infoTarjeta(String titular, int tipo){
        this.titular = titular;
        this.tipo = tipo;
        setCredito(calcularCredito(tipo));
    }

	private double calcularCredito(int tipo) {
		double credito;
		switch (tipo) {
			case 1: //oro
				credito = 1000;	break;
			case 2: //platino
				credito =  800;	break;
			case 3: //clasica
				credito =  600;	break;
			default:
				credito =  600;	break;
		}
		return credito;
	}

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
