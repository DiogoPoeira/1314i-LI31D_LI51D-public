package scene;
/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

import java.awt.Color;

import core.IRasterDevice;
import core.IRenderContext;
import core.IRenderListener;

/**
 * {@link Smiley} is a super simple sample scene that
 * renders a smile in the scene with flashing eyes.
 * 
 * @author Carlos Guedes
 *
 */
public class Smiley implements IRenderListener {

	private final int cx;
	private final int cy;
	final private int flashingFrequency;
	int flashCount = 0;

	public Smiley(final IRasterDevice touchRasterDevice) {
		this(touchRasterDevice, 0, 0, 10);
	}

	public Smiley(final IRasterDevice touchRasterDevice, final int cx, final int cy, final int flashingFrequency) {
		this.cx = cx;
		this.cy = cy;
		this.flashingFrequency = flashingFrequency;
		touchRasterDevice.addRenderListener(this);
	}

	@Override
	public void render(final IRenderContext renderContext)
	{
		drawEye(  renderContext, -5, 5);
		drawEye(  renderContext,  5, 5);
		drawNose( renderContext,  0,  0);
		drawMouth(renderContext,  0, -5);
		++flashCount;
	}

	private void drawEye(final IRenderContext renderContext, final int x, final int y)
	{
		renderContext.setPixel(cx+x-1, cy+y+1, Color.yellow);
		renderContext.setPixel(cx+x  , cy+y+1, Color.yellow);
		renderContext.setPixel(cx+x+1, cy+y+1, Color.yellow);

		Color eyeColor = flashCount % flashingFrequency == 0 ? Color.RED : Color.GREEN;
		renderContext.setPixel(cx+x  , cy+y  , eyeColor);

		renderContext.setPixel(cx+x-1, cy+y-1, Color.yellow);
		renderContext.setPixel(cx+x  , cy+y-1, Color.yellow);
		renderContext.setPixel(cx+x+1, cy+y-1, Color.yellow);
	}

	private void drawNose(final IRenderContext renderContext, final int x, final int y) {
		renderContext.setPixel(cx+x, cy+y, Color.red);
	}

	private void drawMouth(final IRenderContext renderContext, final int x, final int y)
	{
		renderContext.setPixel(cx+x-3, cy+y+1, Color.green);
		renderContext.setPixel(cx+x-2, cy+y  , Color.green.darker());
		renderContext.setPixel(cx+x-1, cy+y  , Color.green.darker());
		renderContext.setPixel(cx+x  , cy+y  , Color.green.darker());
		renderContext.setPixel(cx+x+1, cy+y  , Color.green.darker());
		renderContext.setPixel(cx+x+2, cy+y  , Color.green.darker());
		renderContext.setPixel(cx+x+3, cy+y+1, Color.green);
	}
}