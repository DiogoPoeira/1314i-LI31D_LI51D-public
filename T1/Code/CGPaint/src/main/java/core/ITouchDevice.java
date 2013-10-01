/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

package core;

/**
 * Interface of a touch device
 * 
 *  An {@link ITouchDevice} fire touch events.
 *  To receive those events use the method addTouchListener.
 *
 * @author Carlos Guedes
 *
 */
public interface ITouchDevice
{
	/**
	 * Adds a {@link ITouchListener} that will be executed
	 * upon touch events in the device.
	 * 
	 * @param touchListener contains methods to react to touch events
	 */
	void addTouchListener(ITouchListener touchListener);
}
