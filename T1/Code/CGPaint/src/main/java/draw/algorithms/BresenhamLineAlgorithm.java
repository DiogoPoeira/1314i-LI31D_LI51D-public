package draw.algorithms;
/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import core.IRasterDevice;
import core.IRenderContext;
import core.IRenderListener;
import draw.shapes.Line;

/**
 * Implements the Bresenham line drawing algorithm
 * for a set of {@link Line} segments using the
 * given {@link IRasterDevice}.
 * 
 * @author Carlos Guedes
 *
 */
public class BresenhamLineAlgorithm implements IRenderListener {

	private final List<Line> lines = new LinkedList<>();

	public BresenhamLineAlgorithm(final IRasterDevice touchRasterDevice) {
		touchRasterDevice.addRenderListener(this);
	}

	public void add(final Line line) {
		lines.add(line);
	}

	@Override
	public void render(final IRenderContext renderContext) {
		for (Line lineSegment : lines) {
			int x0 = lineSegment.getX0(),
					x1 = lineSegment.getX1(),
					y0 = lineSegment.getY0(),
					y1 = lineSegment.getY1();
			renderLine(renderContext, x0, x1, y0, y1, lineSegment.getColor());
		}
	}

	public void renderLine(final IRenderContext renderContext, final int x0, final int x1, final int y0, final int y1, final Color color)
	{
		/*/ This draws a transparent rectangle!
		final Color BG_COLOR = new Color(.91f, .91f, .91f, 0.5f);
		for(int x = x0; x <= x1; ++x) {
			for (int y = y0; y <= y1; y++) {
				renderContext.setPixel(x, y, BG_COLOR);
			}
		}
		// */


		int e = 0;
		int dx = x1 - x0;
		int dy = y1 - y0;
		int y = y0;
		for (int x = x0; x <= x1; ++x) {
			renderContext.setPixel(x, y, color);
			e += dy;
			if (2 * e >= dx) {
				++y;
				e -= dx;
			}
		}

	}

}