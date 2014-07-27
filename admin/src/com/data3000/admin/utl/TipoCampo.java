package com.data3000.admin.utl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum TipoCampo {

	CADENA(1, "tipo.cadena"),  FECHA(3, "tipo.fecha"), NUMERO(2, "tipo.numero");

	private int id;
	private String nombre;

	private TipoCampo(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	private String toString(Object valor) {
		
		if(valor == null){
			return null;
		}
		
		switch (this) {
			case CADENA:
				return valor.toString();
			case NUMERO:
				DecimalFormat df = new DecimalFormat(ConstantesAdmin.FORMATO_NUMERO);
				Number numero = (Number) valor;
				return df.format(numero);				
			case FECHA:
				SimpleDateFormat sdf = new SimpleDateFormat(ConstantesAdmin.FORMATO_FECHA);
				Date fecha = (Date) valor;
				return sdf.format(fecha);
			default:
				return null;

		}
		
		
	}

	public int getId() {
		return id;
	}

	

	public String getNombre() {
		return nombre;
	}

	
}
