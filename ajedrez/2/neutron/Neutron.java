package neutron;
/**
 * Clase Neutron
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class Neutron extends PiezaNeutron {
	/**
	 * Constructor sin parametros
	 */
	public Neutron(){
		super();
	}
	@Override
	public boolean isValida() {
		return true;
	}
	@Override
	public String toString() {
		return "N";
	}
}
