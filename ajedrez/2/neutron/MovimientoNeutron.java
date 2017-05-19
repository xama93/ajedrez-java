package neutron;

import modelo.Coordenada;
import modelo.IMovimiento;
/**
 * Clase MovimientoNeutron
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public class MovimientoNeutron implements IMovimiento {
	/**
     * Variables:
     * <br>coordenadaOrigen :Coordenada origen del neutron
     */
	private Coordenada coordenadaOrigen;
	/**
	 * coordenadaDestino :Coordenada destino del neutron
	 */
	private Coordenada coordenadaDestino;
	/**
	 * coordenadaOrigenElectron :Coordenada origen del electron
	 */
	private Coordenada coordenadaOrigenElectron;
	/**
	 * coordenadaDestinoElectron :Coordenada destino del electron
	 */
	private Coordenada coordenadaDestinoElectron;
	/**
	 * Constructor de 4 parametro
	 * @param con :coordenada origen neutron
	 * @param cdn :coordenada destino neutron
	 * @param coe :coordenada origen electron
	 * @param cde :coordenada destino electron
	 */
	public MovimientoNeutron(Coordenada con, Coordenada cdn, Coordenada coe, Coordenada cde){
		coordenadaOrigen=new Coordenada(con);
		coordenadaDestino=new Coordenada(cdn);
		coordenadaOrigenElectron=new Coordenada(coe);
		coordenadaDestinoElectron=new Coordenada(cde);
	}
	/**
	 * Constructor de 2 parametros
	 * @param co :coordenada origen
	 * @param cd :coordenada destino
	 */
	public MovimientoNeutron(Coordenada co,Coordenada cd){
		coordenadaOrigen=new Coordenada(co);
		coordenadaDestino=new Coordenada(cd);
		coordenadaOrigenElectron=new Coordenada(co);
		coordenadaDestinoElectron=new Coordenada(cd);
	}
	@Override
	public Coordenada getCoordenadaOrigen() {
		return coordenadaOrigen;
	}
	@Override
	public Coordenada getCoordenadaDestino() {
		return coordenadaDestino;
	}
	/**
	 * @return el coordenadaOrigenElectron
	 */
	public Coordenada getCoordenadaOrigenElectron() {
		return coordenadaOrigenElectron;
	}
	/**
	 * @return el coordenadaDestinoElectron
	 */
	public Coordenada getCoordenadaDestinoElectron() {
		return coordenadaDestinoElectron;
	}
}
