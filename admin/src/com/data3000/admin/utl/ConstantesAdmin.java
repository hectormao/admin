package com.data3000.admin.utl;

public class ConstantesAdmin {
	
	/**
	 * id con el que se identifica el atributo se sesion del usuario
	 */
	public static final String SESION_USUARIO = "usuario";
	
	public static final String SESION_MAPA_FORM = "mapaFormularios";
	
	
	/**
	 * Estado inactivo
	 */
	public static final String ESTADO_INACTIVO = "I";
	
	/**
	 * Estado activo
	 */
	public static final String ESTADO_ACTIVO = "A";
	
	
	/**
	 * Parametro a ventana de funcionalidad
	 */
	public static final String PARAMETRO_FUNCIONALIDAD = "funcionalidad";
	
	/**
	 * Nombre de la clave que referencia los campos a mostrar en una tabla definidos en un properties
	 */
	public static final String NOMBRE_CLAVE_CAMPOS_MOSTRAR_TABLA = "camposMostrar";
	
	
	/************************************************************
	 * CODIGOS DE ERROR DE LA PLATAFORMA
	 ************************************************************/
	
	/**
	 * No se puede lanzar la ventana de acceso
	 */
	public static final String ERR0001 = "error.0001";
	
	/**
	 * El campo no puede ser vacio
	 */
	public static final String ERR0002 = "error.0002";
	
	/**
	 * No existe el usuario
	 */
	public static final String ERR0003 = "error.0003";
	
	/**
	 * Clave no valida
	 */
	public static final String ERR0004 = "error.0004";


	/**
	 * Usuario inactivo
	 */
	public static final String ERR0005 = "error.0005";
	
	/**
	 * Usuario ya registrado
	 */
	public static final String ERR0006 = "error.0006";
	
	/**
	 * No se ha seleccionado elemento
	 */
	public static final String ERR0007 = "error.0007";
	
	
	/**
	 * Argumentos a los formularios
	 */
	public static final String ARG_USUARIO = "usuario";

	public static final String ARG_FORMULARIO = "formulario";

	public static final String ARG_TABLA_DATOS = "tablaDatos";
	
	public static final String ARG_CLASE = "clase";
	
	public static final String ARG_CAMPOS_TABLA = "camposTabla";
	
	public static final String ARG_SELECCION = "objetoSeleccionado";

	
	/**
	 * Eventos
	 */
	public static final String ACCION = "accion";
	public static final String EVENTO_REFRESCAR = "refrescar";

	/**
	 * Argumentos
	 */
	public static final String OBJETO_PADRE = "padre";
	public static final String NOMBRE_ATRIBUTO_PADRE = "nombrePadre";

	

	/**
	 * Formatos de fecha y numeros
	 */
	public static final String FORMATO_NUMERO = "0.#";
	public static final String FORMATO_FECHA = "yyyy-MM-dd HH:mm:ss.S";
	
	

	/**
	 * Tipos de formulario
	 */
	
	public static final String FORMULARIO_TIPO_INSERTAR = "I";
	public static final String FORMULARIO_TIPO_BORRAR = "B";
	public static final String FORMULARIO_TIPO_EDITAR = "E";
	public static final String FORMULARIO_TIPO_CONSULTAR = "C";
	public static final String FORMULARIO_TIPO_OTRO = "O";
	
	
	
	/**
	 * Tipos de funcionalidades hijas
	 */
	public static final String HIJO_BOTON = "B";
	public static final String HIJO_CELDA = "C";
	public static final String HIJO_DETALLE = "D";
	


	
}
