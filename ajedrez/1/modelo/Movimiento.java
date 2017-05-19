package modelo;
/**
 * Clase movimiento
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public abstract class Movimiento {
	/**
	 * Variables:
	 * <br>Coordenadas[2] :Vector de dos coordenadas que equivale a un movimiento
	 */
	private Coordenada coordenadas[]= new Coordenada[2];
	/**
	 * Constructor por parametros
	 * <br>Se da por sentado que las coordenadas son correctas
	 * @param co :Coordenadas del origen
	 * @param cd :Coordenadas del destino
	 */
	public Movimiento(Coordenada co,Coordenada cd){
		coordenadas[0]=new Coordenada(co);
		coordenadas[1]=new Coordenada(cd);
	}
	/**
	 * Get coordenada origen
	 * @return coordenadas[0] :Coordenada origen del movimiento
	 */
	public Coordenada getCoordenadaOrigen() {
		return coordenadas[0];
	}
	/**
	 * Get coordenada destino
	 * @return coordenadas[1] :Coordenada destino del movimiento
	 */
	public Coordenada getCoordenadaDestino() {
		return coordenadas[1];
	}
}
