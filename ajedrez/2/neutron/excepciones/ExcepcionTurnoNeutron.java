package neutron.excepciones;

import neutron.PiezaNeutron;
import modelo.IMovimiento;
import modelo.excepciones.ExcepcionMovimiento;
/**
 * Excepcion ExcepcionTurnoNeutron
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class ExcepcionTurnoNeutron extends ExcepcionMovimiento {
	/**
	 * Variables:
	 * <br>Variable estatica constante de la clase Exception
	 */
	private static final long serialVersionUID = 3354888359302140329L;
	private PiezaNeutron pieza;
	/**
	 * Constructor por parametros mov
	 * @param m :Movimiento causante del error
	 * @param p :Pieza que se ha intentado mover
	 */
	public ExcepcionTurnoNeutron(IMovimiento m,PiezaNeutron p) {
		super(m);
		pieza=p;
	}
	@Override
	public String getMessage() {
		if(pieza!=null) {
			if(pieza.toString()!="N")
				return "Excepcion: es el turno del electron y se intenta mover un neutron";
			return "Excepcion: es el turno del neutron y se intenta mover un electron";
		}
		return "Excepcion: es el turno del contrario o del neutron";
	}

}
