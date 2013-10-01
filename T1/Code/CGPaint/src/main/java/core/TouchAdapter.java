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
 * Adapter for touch listeners
 * 
 *  This class should be used for touch listeners that only want to override some
 *  methods of the ITouchListener interface
 * 
 * @author Carlos
 *
 */
public class TouchAdapter implements ITouchListener
{
	@Override
	public void onMove(final int x, final int y) { }
	@Override
	public void onClick(final int x, final int y, final int button)  { }
	@Override
	public void onDrag(final int x, final int y, final int button)  { }
}

