package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import modelo.excepciones.ExcepcionPiezaDesconocida;
/**
 * Clase factoria pieza
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class FactoriaPieza {
	/**
	 * Metodo estatico creaPieza con un string
	 * <br>Crea una pieza por reflexion a partir de un string
	 * @param p :Clase de la nueva pieza
	 * @param c :Color de la nueva pieza
	 * @return pieza :Devuelve la nueva pieza creada
	 * @throws ExcepcionPiezaDesconocida :Se lanza cuando el string no representa una clase del proyecto
	 */
	public static Pieza creaPieza(String p,Color c) throws ExcepcionPiezaDesconocida{
		if(c!=Color.NULO){
			Class<?> aux = null;
			try {
				aux = Class.forName("modelo."+p);
				if(aux.getSuperclass()==Pieza.class){
					Constructor<?> constructor = aux.getConstructor(modelo.Color.class);
					Object pieza = constructor.newInstance(c);
					return (Pieza) pieza;
				}throw new ExcepcionPiezaDesconocida(p.charAt(0));
			} catch (ClassNotFoundException e) {
				throw new ExcepcionPiezaDesconocida(p.charAt(0));
			} catch (SecurityException e) {
				throw new ExcepcionPiezaDesconocida(p.charAt(0));
			} catch (NoSuchMethodException e) {
				throw new ExcepcionPiezaDesconocida(p.charAt(0));
			} catch (IllegalArgumentException e) {
				throw new ExcepcionPiezaDesconocida(p.charAt(0));
			} catch (InstantiationException e) {
				throw new ExcepcionPiezaDesconocida(p.charAt(0));
			} catch (IllegalAccessException e) {
				throw new ExcepcionPiezaDesconocida(p.charAt(0));
			} catch (InvocationTargetException e) {
				throw new ExcepcionPiezaDesconocida(p.charAt(0));
			}
		}throw new ExcepcionPiezaDesconocida(p.charAt(0));
	}
	/**
	 * Metodo estatico creaPieza con un char
	 * <br>Crea una pieza a partir del tipo
	 * @param p :Tipo de la nueva pieza
	 * @param c :Color de la nueva pieza
	 * @return pieza :Devuelve la nueva pieza creada
	 * @throws ExcepcionPiezaDesconocida :Se lanza cuando el char no es ningun tipo de pieza definido
	 */
	public static Pieza creaPieza(char p,Color c) throws ExcepcionPiezaDesconocida{
		Pieza pieza = null;
		
		switch(p){
		case 'P':
			pieza = creaPieza("Peon",c);
			break;
		case 'R':
			pieza = creaPieza("Rey",c);
			break;
		case 'A':
			pieza = creaPieza("Alfil",c);
			break;
		case 'D':
			pieza = creaPieza("Dama",c);
			break;
		case 'C':
			pieza = creaPieza("Caballo",c);
			break;
		case 'T':
			pieza = creaPieza("Torre",c);
			break;
		default:
			throw new ExcepcionPiezaDesconocida(p);
		}
		return pieza;
	}
}
