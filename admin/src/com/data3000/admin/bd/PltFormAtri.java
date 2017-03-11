package com.data3000.admin.bd;
// Generated 11/03/2017 08:16:33 AM by Hibernate Tools 3.5.0.Final

import java.util.Date;

/**
 * PltFormAtri generated by hbm2java
 */
public class PltFormAtri implements java.io.Serializable {

	private long formAtriIdn;
	private PltFormulario pltFormulario;
	private String formAtriNombre;
	private String formAtriValor;
	private String audiUsuario;
	private Date audiFechModi;
	private boolean audiSiAnul;
	private String audiMotiAnul;
	private String audiChecksum;

	public PltFormAtri() {
	}

	public PltFormAtri(long formAtriIdn, PltFormulario pltFormulario, String formAtriNombre, String formAtriValor,
			String audiUsuario, Date audiFechModi, boolean audiSiAnul) {
		this.formAtriIdn = formAtriIdn;
		this.pltFormulario = pltFormulario;
		this.formAtriNombre = formAtriNombre;
		this.formAtriValor = formAtriValor;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
	}

	public PltFormAtri(long formAtriIdn, PltFormulario pltFormulario, String formAtriNombre, String formAtriValor,
			String audiUsuario, Date audiFechModi, boolean audiSiAnul, String audiMotiAnul, String audiChecksum) {
		this.formAtriIdn = formAtriIdn;
		this.pltFormulario = pltFormulario;
		this.formAtriNombre = formAtriNombre;
		this.formAtriValor = formAtriValor;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
		this.audiMotiAnul = audiMotiAnul;
		this.audiChecksum = audiChecksum;
	}

	public long getFormAtriIdn() {
		return this.formAtriIdn;
	}

	public void setFormAtriIdn(long formAtriIdn) {
		this.formAtriIdn = formAtriIdn;
	}

	public PltFormulario getPltFormulario() {
		return this.pltFormulario;
	}

	public void setPltFormulario(PltFormulario pltFormulario) {
		this.pltFormulario = pltFormulario;
	}

	public String getFormAtriNombre() {
		return this.formAtriNombre;
	}

	public void setFormAtriNombre(String formAtriNombre) {
		this.formAtriNombre = formAtriNombre;
	}

	public String getFormAtriValor() {
		return this.formAtriValor;
	}

	public void setFormAtriValor(String formAtriValor) {
		this.formAtriValor = formAtriValor;
	}

	public String getAudiUsuario() {
		return this.audiUsuario;
	}

	public void setAudiUsuario(String audiUsuario) {
		this.audiUsuario = audiUsuario;
	}

	public Date getAudiFechModi() {
		return this.audiFechModi;
	}

	public void setAudiFechModi(Date audiFechModi) {
		this.audiFechModi = audiFechModi;
	}

	public boolean isAudiSiAnul() {
		return this.audiSiAnul;
	}

	public void setAudiSiAnul(boolean audiSiAnul) {
		this.audiSiAnul = audiSiAnul;
	}

	public String getAudiMotiAnul() {
		return this.audiMotiAnul;
	}

	public void setAudiMotiAnul(String audiMotiAnul) {
		this.audiMotiAnul = audiMotiAnul;
	}

	public String getAudiChecksum() {
		return this.audiChecksum;
	}

	public void setAudiChecksum(String audiChecksum) {
		this.audiChecksum = audiChecksum;
	}

}
