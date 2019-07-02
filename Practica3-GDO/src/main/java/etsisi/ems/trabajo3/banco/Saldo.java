package main.java.etsisi.ems.trabajo3.banco;

import java.util.Vector;

public class Saldo {


	public Saldo() {
	}

	public double getSaldo(Vector<Movimiento> mMovimientos) {
				
		       double r = 0.0;
		        for (int i = 0; i < mMovimientos.size(); i++) {
		            Movimiento m = (Movimiento) mMovimientos.elementAt(i);
		            r += m.getImporte();
		        }
		        return r;  
	}

}