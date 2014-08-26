package com.data3000.admin.bd;

// Generated 12/01/2014 11:11:48 AM by Hibernate Tools 3.4.0.CR1



import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.data3000.admin.vo.Formulario;

/**
 * PltFormulario generated by hbm2java
 */
public class PltFormulario extends Formulario implements java.io.Serializable {

	private long formIdn;
	private String formNombre;
	private String formTipo;
	private String formUrl;
	private String formModulo;
	private String audiUsuario;
	private Date audiFechModi;
	private boolean audiSiAnul;
	private String audiMotiAnul;
	private String audiChecksum;
	private String formIcono;
	private Set<PltFormAtri> pltFormAtris = new HashSet<PltFormAtri>(0);
	private Set<PltRelaForm> pltRelaFormsForFormPadre = new HashSet<PltRelaForm>(
			0);
	private Set<PltRelaForm> pltRelaFormsForFormHijo = new HashSet<PltRelaForm>(
			0);
	private Set<PltMenu> pltMenus = new HashSet<PltMenu>(0);
	private Set<PltPermiso> pltPermisos = new HashSet<PltPermiso>(0);

	public PltFormulario() {
	}

	public PltFormulario(long formIdn, String formNombre, String formTipo,
			String formUrl, String formModulo, String audiUsuario,
			Date audiFechModi, boolean audiSiAnul) {
		this.formIdn = formIdn;
		this.formNombre = formNombre;
		this.formTipo = formTipo;
		this.formUrl = formUrl;
		this.formModulo = formModulo;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
	}

	public PltFormulario(long formIdn, String formNombre, String formTipo,
			String formUrl, String formModulo, String audiUsuario,
			Date audiFechModi, boolean audiSiAnul, String audiMotiAnul,
			String audiChecksum,String formIcono, 
			Set<PltFormAtri> pltFormAtris,
			Set<PltRelaForm> pltRelaFormsForFormPadre,
			Set<PltRelaForm> pltRelaFormsForFormHijo, Set<PltMenu> pltMenus,
			Set<PltPermiso> pltPermisos) {
		this.formIdn = formIdn;
		this.formNombre = formNombre;
		this.formTipo = formTipo;
		this.formUrl = formUrl;
		this.formModulo = formModulo;
		this.audiUsuario = audiUsuario;
		this.audiFechModi = audiFechModi;
		this.audiSiAnul = audiSiAnul;
		this.audiMotiAnul = audiMotiAnul;
		this.audiChecksum = audiChecksum;
		this.pltRelaFormsForFormPadre = pltRelaFormsForFormPadre;
		this.pltRelaFormsForFormHijo = pltRelaFormsForFormHijo;
		this.pltMenus = pltMenus;
		this.pltPermisos = pltPermisos;
		this.formIcono = formIcono;
		this.pltFormAtris = pltFormAtris;
	}

	public long getFormIdn() {
		return this.formIdn;
	}

	public void setFormIdn(long formIdn) {
		this.formIdn = formIdn;
	}

	public String getFormNombre() {
		return this.formNombre;
	}

	public void setFormNombre(String formNombre) {
		this.formNombre = formNombre;
	}

	public String getFormTipo() {
		return this.formTipo;
	}

	public void setFormTipo(String formTipo) {
		this.formTipo = formTipo;
	}

	public String getFormUrl() {
		return this.formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	public String getFormModulo() {
		return this.formModulo;
	}

	public void setFormModulo(String formModulo) {
		this.formModulo = formModulo;
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

	public Set<PltRelaForm> getPltRelaFormsForFormPadre() {
		return this.pltRelaFormsForFormPadre;
	}

	public void setPltRelaFormsForFormPadre(
			Set<PltRelaForm> pltRelaFormsForFormPadre) {
		this.pltRelaFormsForFormPadre = pltRelaFormsForFormPadre;
	}

	public Set<PltRelaForm> getPltRelaFormsForFormHijo() {
		return this.pltRelaFormsForFormHijo;
	}

	public void setPltRelaFormsForFormHijo(
			Set<PltRelaForm> pltRelaFormsForFormHijo) {
		this.pltRelaFormsForFormHijo = pltRelaFormsForFormHijo;
	}

	public Set<PltMenu> getPltMenus() {
		return this.pltMenus;
	}

	public void setPltMenus(Set<PltMenu> pltMenus) {
		this.pltMenus = pltMenus;
	}

	public Set<PltPermiso> getPltPermisos() {
		return this.pltPermisos;
	}

	public void setPltPermisos(Set<PltPermiso> pltPermisos) {
		this.pltPermisos = pltPermisos;
	}
	
	

	public String getFormIcono() {
		return formIcono;
	}

	public void setFormIcono(String formIcono) {
		this.formIcono = formIcono;
	}
	
	

	public Set<PltFormAtri> getPltFormAtris() {
		return pltFormAtris;
	}

	public void setPltFormAtris(Set<PltFormAtri> pltFormAtris) {
		this.pltFormAtris = pltFormAtris;
	}

	@Override
	public String getNombre() {
		return getFormNombre();
	}

	@Override
	public String getTooltip() {
		return getFormNombre();
	}

	@Override
	public String getUrl() {
		return getFormUrl();
	}

	@Override
	public String getTipo() {
		return getFormTipo();
	}

	@Override
	public Long getId() {		
		return getFormIdn();
	}

	@Override
	public String getUrlIcono() {
		return getFormIcono();
	}

	

	


}
