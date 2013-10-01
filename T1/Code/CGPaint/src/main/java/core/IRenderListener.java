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
 * Interface of a render listener
 * 
 *  Listeners are entities that want to render pixels on the scene.
 *  When is time to render, the method render will be called, and
 *  {@link IRenderListener} provides operations to render on the scene.
 * 
 * @author Carlos Guedes
 *
 */
public interface IRenderListener
{
	/**
	 * This method is called when is time to the listener render on
	 * the raster device.
	 * 
	 * @param renderContext contains methods to render into the scene
	 */
	void render(IRenderContext renderContext);
}

