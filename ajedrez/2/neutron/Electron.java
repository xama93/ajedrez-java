package neutron;

import modelo.Color;
/**
 * Clase Electron
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Electron extends PiezaNeutron {
	/**
     * Variables:
     * <br>color :Color de la pieza
     */
	private Color color;
	/**
	 * Constructor con 1 parametro
	 * <br>Crea un electron del color dado
	 * @param c :Color de la nueva pieza
	 */
	public Electron(Color c){
		super();
		color=c;
	}
	@Override
	public boolean isValida() {
		return (color==Color.BLANCO || color==Color.NEGRO);
	}
	/**
	 * Get color
	 * @return color :Color de la pieza
	 */
	public Color getColor() {
		return color;
	}
	@Override
	public String toString() {
		if(color==Color.BLANCO) return "E";
		else if(color==Color.NEGRO) return "e";
		else if(color==Color.NULO) return "";
		return null;
	}
}
