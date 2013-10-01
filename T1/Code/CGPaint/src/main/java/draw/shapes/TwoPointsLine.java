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
 * A {@link Line} segment defined with two points,
 * the first (x0, y0) and the last (x1, y1).
 * 
 * @author Carlos Guedes
 *
 */
public class TwoPointsLine implements Line {

	private final int x0;
	private final int y0;
	private final int x1;
	private final int y1;
	private Color color = Color.WHITE;

	public TwoPointsLine(final int x0, final int y0, final int x1, final int y1, final Color color) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.color = color;
	}

	@Override
	public Color getColor() { return color;	}

	@Override
	public int getX0() { return x0; }
	@Override
	public int getX1() { return x1; }
	@Override
	public int getY0() { return y0; }
	@Override
	public int getY1() { return y1; }

}