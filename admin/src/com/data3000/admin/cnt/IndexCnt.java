package com.data3000.admin.cnt;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.East;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.data3000.admin.ngc.PlataformaNgc;
import com.data3000.admin.utl.ConstantesAdmin;
import com.data3000.admin.vo.EstructuraMenu;
import com.data3000.admin.vo.Formulario;
import com.data3000.admin.vo.Usuario;


public class IndexCnt extends GenericForwardComposer<Window>  {
	
	private Logger logger;
	
	private Window winIndex;
	
	
	private Tabs tabs;
	private Tabpanels tabpanels;
	
	private PlataformaNgc plataformaNgc;
	
	private Usuario usuario;
	private Map<String,Formulario> mapaFormularios;
	
	@Override
	public void doAfterCompose(Window winIndex) throws Exception{
		
		super.doAfterCompose(winIndex);
		logger = Logger.getLogger(this.getClass());
		
		
	}
	
	public void onCreate$winIndex(Event evt) throws Exception{
		ingresar();
	}
	
	private void ingresar() throws Exception{
		usuario = (Usuario) session.getAttribute(ConstantesAdmin.SESION_USUARIO);
		if(usuario == null){
			lanzarVentanaAcceso();
		} else {
			mapaFormularios = (Map<String,Formulario>) session.getAttribute(ConstantesAdmin.SESION_MAPA_FORM);			
			lanzarEscritorio();
		}
	}
	
	private void lanzarEscritorio() throws Exception {
		
		Borderlayout areaTrabajo = new Borderlayout();
		Image image = new Image();
		image.setSrc("img/iconos/codex.png");
		
		
		
		winIndex.setWidth("100%");
		winIndex.setHeight("100%");
//		winIndex.appendChild(contenedorLogo);
		winIndex.appendChild(areaTrabajo);
		areaTrabajo.setWidth("100%");
		areaTrabajo.setHeight("100%");
		North norte = new North();
		norte.setSize("40px");
		norte.setBorder("none");
		areaTrabajo.appendChild(norte);
		
		Center escritorio = new Center();
		cargarEscritorio(escritorio);
		areaTrabajo.appendChild(escritorio);
		
		Borderlayout layoutNorte = new Borderlayout();
		
		Center center = new Center();
		center.setBorder("none");
        Hbox areaMenu = new Hbox();
        
		
        //Menubar menuBar = new Menubar();
        //menuBar.setSclass("menuBar");
        
        Toolbarbutton tlbApp = new Toolbarbutton();
        tlbApp.setImage("img/iconos/menu.png");
        tlbApp.setClass("menuapp");
        
        
        //itemPrincipal.setSclass("menuapp");
        //menuBar.appendChild(itemPrincipal);
		areaMenu.appendChild(tlbApp);
		
		areaMenu.setHflex("1");
		areaMenu.setVflex("1");
		//menuBar.setVflex("1");
		Menupopup popup = new Menupopup();
		//itemPrincipal.appendChild(popup);
		
		cargarMenu(popup);
		winIndex.appendChild(popup);
		
		
		tlbApp.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				
				popup.open(self,"after_pointer");
				
			}
		});
		
		
		
		areaMenu.appendChild(image);
		
		
		center.appendChild(areaMenu);
		layoutNorte.appendChild(center);
		
		East este = new East();
		este.setSize("100px");
		este.setBorder("none");
		
		
		Toolbar toolbar = new Toolbar();
		toolbar.setClass("toolUsuario");
		
		//Boton usuario conectado
		Toolbarbutton btnUsuarioConectado = new Toolbarbutton();
		btnUsuarioConectado.setImage("img/iconos/usuario.png");
		btnUsuarioConectado.setLabel(usuario.getLogin());
		btnUsuarioConectado.setTooltip(usuario.getLogin());
		toolbar.appendChild(btnUsuarioConectado);
		
		//Boton cerrar sesion
		Toolbarbutton btnCerrarSesion = new Toolbarbutton();
		btnCerrarSesion.setTooltiptext("${labels.app.cerrarSesion}");
		btnCerrarSesion.setImage("img/iconos/cerrarSesion.png");
		//Evento para cerrar la sesion del usuario
		btnCerrarSesion.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				if(logger.isDebugEnabled()) logger.debug(new StringBuilder("Cerrando sesi�n"));
				
				// Eliminar el usuario de los atributos en sesi�n
				session.removeAttribute(ConstantesAdmin.SESION_USUARIO);
				// Invalidar la sesi�n del usuario actual
		        session.invalidate();
		        // Redireccionar la p�gina para volver a la ventana de autenticaci�n
		        Executions.sendRedirect("/index.zul");
				
			}
		});
		toolbar.appendChild(btnCerrarSesion);
		este.appendChild(toolbar);
		layoutNorte.appendChild(este);
		norte.appendChild(layoutNorte);
		
		
	}
	
	private void abrirFormulario(final Component componenteEvento, final Formulario funcionalidad) throws Exception{
		
		
		if(componenteEvento != null && componenteEvento instanceof Menuitem){
			((Menuitem) componenteEvento).setDisabled(true);
		}
		
		Window win;
		
		final Tab tab = new Tab();
		tab.setClosable(true);
		
		String leyenda = Labels.getLabel(funcionalidad.getNombre());
		if(StringUtils.isBlank(leyenda)){
			leyenda = funcionalidad.getNombre();
		}
		tab.setTooltip(funcionalidad.getTooltip());
		tab.setLabel(leyenda);
		tabs.appendChild(tab);
		
		
		Tabpanel panel = new Tabpanel();
		tabpanels.appendChild(panel);
		
		String url = funcionalidad.getUrl();
		
		Map<String,Object> marg = new HashMap<String, Object>();
		marg.put(ConstantesAdmin.ARG_FORMULARIO,funcionalidad);
		marg.put(ConstantesAdmin.ARG_USUARIO,usuario);
		
		try{			
			win = (Window) Executions.createComponents(url, panel, marg);
			
		} catch(Exception ex){
			java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream(url) ;
			java.io.Reader zulReader = new java.io.InputStreamReader(zulInput) ;
			win = (Window) Executions.createComponentsDirectly(zulReader,"zul",panel,marg) ;
		}
		
		win.setBorder("none");
		win.doEmbedded();
		
		win.addEventListener(Events.ON_CLOSE, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {				
				Events.sendEvent(Events.ON_CLOSE,tab,null);
			}
			
		});
		
		
		tab.setSelected(true);
		
		tab.addEventListener(Events.ON_CLOSE, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				if(componenteEvento != null && componenteEvento instanceof Menuitem){
					((Menuitem) componenteEvento).setDisabled(false);
				}				
			}
		});
		
		
		
		
	}

	private void cargarEscritorio(Center escritorio) {
		Tabbox tabbox = new Tabbox();
		tabbox.setOrient("bottom");
		escritorio.appendChild(tabbox);
		tabbox.setWidth("100%");
		tabbox.setHeight("100%");
		
		tabs = new Tabs();
		
		tabbox.appendChild(tabs);
		
		tabpanels = new Tabpanels();
		tabbox.appendChild(tabpanels);
		
	}

	private void cargarMenu(Menupopup menu) throws Exception{
		List<EstructuraMenu> estructuras = plataformaNgc.getMenu(usuario,mapaFormularios); 
		
		for(EstructuraMenu estructura : estructuras ){
			cargarMenu(menu, estructura);
		}
	}
	
	private void cargarMenu(Component padre, EstructuraMenu estructura) throws Exception{
		
		com.data3000.admin.vo.Menu menu = estructura.getMenu();
		if(menu != null){
			
			String leyenda = Labels.getLabel(menu.getNombre());
			if(StringUtils.isBlank(leyenda)){
				leyenda = menu.getNombre();
			}
			
			final Formulario accion = menu.getFormulario();
			
			if(accion != null){
				//menu item
				final Menuitem menuitem = new Menuitem(leyenda);
				menuitem.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						abrirFormulario(menuitem, accion);
						
					}
					
				});
				padre.appendChild(menuitem);
			} else {
				
				Menu menuPadre = new Menu(leyenda);
				Menupopup popup = new Menupopup();
				menuPadre.appendChild(popup);
				for(EstructuraMenu hijo : estructura.getHijos()){
					cargarMenu(popup, hijo);
				}
				padre.appendChild(menuPadre);
				
			}
			
			
			
		}
		
	}

	private void lanzarVentanaAcceso() {
		try{
			java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/data3000/admin/zul/acceso.zul") ;
			java.io.Reader zulReader = new java.io.InputStreamReader(zulInput) ;
			Window win = (Window) Executions.createComponentsDirectly(zulReader,"zul",winIndex,null) ;
			win.addEventListener(Events.ON_CLOSE, new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					ingresar();					
				}
			});
		} catch(Exception ex){
			logger.error(Labels.getLabel(ConstantesAdmin.ERR0001),ex);
		}
	}


	public PlataformaNgc getPlataformaNgc() {
		return plataformaNgc;
	}

	public void setPlataformaNgc(PlataformaNgc plataformaNgc) {
		this.plataformaNgc = plataformaNgc;
	}
	
	

}
