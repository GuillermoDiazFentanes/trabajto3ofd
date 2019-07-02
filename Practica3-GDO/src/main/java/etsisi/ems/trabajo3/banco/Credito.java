package main.java.etsisi.ems.trabajo3.banco;

import java.util.Date;
import java.util.Vector;
import java.time.LocalDate;
import java.time.ZoneId;

public class Credito{
   
    protected Vector<Movimiento> mMovimientos;
	public Cuenta mCuentaAsociada;
    protected infoTarjeta iT;
    protected datosBancarios dT;
    protected numerosTarjeta nT;
 

    public Credito(numerosTarjeta nT, infoTarjeta iT, datosBancarios dT) {
    	super();
        this.nT = nT;
        this.iT = iT;
        this.dT = dT;
        mMovimientos = new Vector<Movimiento>();
        
    }

    public double getSaldo() {
		return new Saldo().getSaldo(mMovimientos);
    }
	public void setCuenta(Cuenta c) {
		mCuentaAsociada = c;
	}

	public void retirar(double x) throws Exception {	
		double comisiontarifa;
		switch (dT.getMarcainternacional()) {
		case 1: //mastercard
			comisiontarifa = 0.05;
			break;	
		case 2: //maestro
			comisiontarifa = 0.05;
			break;	
		case 3: //visa clásica
			comisiontarifa = 0.03;
			break;
		case 4: //visa electrón
			comisiontarifa = 0.02;
			break;
		default:
			comisiontarifa = 0.05;
			break;		
		}
		
		// Añadimos una comisión de un 5% o 3% o 2%, mínimo de 3 euros.
		double comision = (x * comisiontarifa < 3.0 ? 3 : x * comisiontarifa); 		
		if (x > getCreditoDisponible())
			throw new Exception("Crédito insuficiente");
		Movimiento m = new Movimiento();
		m.setConcepto("Retirada en cuenta asociada (cajero automático)");
		m.setImporte(x + comision);
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		m.setFecha(fecha);
		mMovimientos.addElement(m);
	}

	//traspaso tarjeta a cuenta
		public void ingresar(double x) throws Exception {
			double comision = (x * 0.05 < 3.0 ? 3 : x * 0.05); // Añadimos una comisión de un 5%, mínimo de 3 euros.		
			if (x > getCreditoDisponible())
				throw new Exception("Crédito insuficiente");
			Movimiento m = new Movimiento();
			m.setConcepto("Traspaso desde tarjeta a cuenta");
			m.setImporte(x);
			Date date = new Date();
			LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			m.setFecha(fecha);
			mMovimientos.addElement(m);
			
			mCuentaAsociada.ingresar("Traspaso desde tarjeta a cuenta", x);
			mCuentaAsociada.retirar("Comision Traspaso desde tarjeta a cuenta", comision);
		}

	public void pagoEnEstablecimiento(String datos, double x) throws Exception {
		Movimiento m = new Movimiento();
		m.setConcepto("Compra a crédito en: " + datos);
		m.setImporte(x);
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		m.setFecha(fecha);
		mMovimientos.addElement(m);
	}

	public double getCreditoDisponible() {
		return iT.getCredito() - getSaldo();
	}

	public void liquidar(int mes, int anyo) throws Exception {
		
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {
			Movimiento m = (Movimiento) mMovimientos.elementAt(i);
			if (m.getFecha().getMonthValue() == mes && m.getFecha().getYear() == anyo && !m.isLiquidado())
				r += m.getImporte();
				m.setLiquidado(true);
		}
		
		if (r != 0) {
			Movimiento liq = new Movimiento();
			liq.setConcepto("Liquidación de operaciones tarj. crédito, " + (mes) + " de " + (anyo));
			liq.setImporte(-r);
			Date date = new Date();
			LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			liq.setFecha(fecha);
			mCuentaAsociada.addMovimiento(liq);			
		}
	}
	
	//liquidación parcial sobre el total de los gastos realizados con esa tarjeta durante el mes/año  de liquidación que consiste en lo siguiente: 
	//los gastos totales, incluida una comisión de 12%, se dividen en 3 cuotas a pagar en los 3 meses siguientes 
	public void liquidarPlazos (int mes, int anyo) throws Exception {
		//TODO
	}
}
