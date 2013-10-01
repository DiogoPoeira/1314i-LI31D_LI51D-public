/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

package core;

import java.awt.Component;

/**
 * Interface of a raster device
 * 
 *  A raster device has a related awt component, and generate render events
 * 
 * @author Carlos Guedes
 *
 */
public interface IRasterDevice
{
	Component getComponent();
	void addRenderListener(IRenderListener renderListener);
}