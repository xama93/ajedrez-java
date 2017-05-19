package modelo;
/**
 * Interfaz IMovimiento
 * @author Jose Vicente Orts Romero
 * @author 48764103D
 */
public interface IMovimiento {
	/**
	 * Get coordenada origen
	 * @return coordenada :Coordenada origen del movimiento
	 */
	public Coordenada getCoordenadaOrigen();
	/**
	 * Get coordenada destino
	 * @return coordenada :Coordenada destino del movimiento
	 */
	public Coordenada getCoordenadaDestino();

}