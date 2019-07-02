package main.java.etsisi.ems.trabajo3.banco;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

public class Cuenta {
	
	protected Vector<Movimiento> mMovimientos;
	protected String mNumero;
	protected String mTitular;
	public Cuenta(String numero, String titular) {
		super();
		mNumero = numero;
		mTitular = titular;
		mMovimientos = new Vector<Movimiento>();
	}
	
	public double getSaldo() {
		return new Saldo().getSaldo(mMovimientos);
	}

	public void ingresar(double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		m.setConcepto("Ingreso en efectivo");
		m.setImporte(x);
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		m.setFecha(fecha);
		this.mMovimientos.addElement(m);
	}

	public void retirar(double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new Exception("Saldo insuficiente");
		Movimiento m = new Movimiento();
		m.setConcepto("Retirada de efectivo");
		m.setImporte(-x);
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		m.setFecha(fecha);
		this.mMovimientos.addElement(m);

	}


	public void ingresar(String concepto, double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		movaux(concepto, x);
	}

	public void retirar(String concepto, double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new Exception("Saldo insuficiente");
		movaux(concepto, -x);
	}

	public void movaux(String concepto, double x) throws Exception{
		Movimiento m = new Movimiento();
		m.setConcepto(concepto);
		m.setImporte(x);
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		m.setFecha(fecha);
		this.mMovimientos.addElement(m);
	}

	public void addMovimiento(Movimiento m) {
		mMovimientos.addElement(m);
	}
}
