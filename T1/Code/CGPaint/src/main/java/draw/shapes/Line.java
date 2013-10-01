package draw.shapes;
/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

import java.awt.Color;

/**
 * The <code>Line</code> is used to model a {@link Color}ed line segment.
 * 
 * 
 * @author Carlos Guedes
 *
 */
public interface Line {

	/**
	 * Returns the {@link Color} of line segment
	 * 
	 * @return the color of the segment
	 */
	public abstract Color getColor();

	/**
	 * Returns the initial <code>x</code> (device) coordinate
	 * 
	 * @return initial x device coordinate
	 */
	public abstract int getX0();

	/**
	 * Returns the final <code>x</code> (device) coordinate
	 * 
	 * @return final x device coordinate
	 */
	public abstract int getX1();

	/**
	 * Returns the initial <code>y</code> (device) coordinate
	 * 
	 * @return initial y device coordinate
	 */
	public abstract int getY0();

	/**
	 * Returns the final <code>y</code> (device) coordinate
	 * 
	 * @return final y device coordinate
	 */
	public abstract int getY1();

}