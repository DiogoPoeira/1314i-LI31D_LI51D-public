/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

package core;

import java.awt.Color;

/**
 * Interface of a render context
 * 
 *  This interface contains the operations used by {@link IRenderListener}s
 *  to set pixel(s) in the raster device.
 * 
 * @author Carlos Guedes
 *
 */
public interface IRenderContext {

	/**
	 * Sets the pixel in the (x, y) <b>device's coordinates</b>
	 * with the given {@link Color}.
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	void setPixel(int x, int y, Color color);
}