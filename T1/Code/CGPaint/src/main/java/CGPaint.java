/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import scene.PositionTracker;
import scene.Smiley;
import core.TouchRasterDevice;
import draw.algorithms.BresenhamLineAlgorithm;
import draw.shapes.TwoPointsLine;

/**
 * CGPaint entry point
 * 
 * This version contains a sample scene with two {@link Smiley}s
 * and two {@link TwoPointsLine}s rendered with the {@link BresenhamLineAlgorithm}.
 * 
 * <p>
 * <b>NOTE:</b> This code should be changed until the final
 *              version of the application.
 * </p>
 * 
 * @author Carlos Guedes
 *
 */
public class CGPaint {

	public static void main(final String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("CGPaint (Sample)");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800, 600);

				TouchRasterDevice touchRasterDevice = new TouchRasterDevice(10);

				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(touchRasterDevice.getComponent());
				frame.getContentPane().add(new PositionTracker(touchRasterDevice), BorderLayout.SOUTH);

				frame.setVisible(true);

				new Smiley(touchRasterDevice, -20,  10, 5);
				new Smiley(touchRasterDevice, -20, -10, 7);

				BresenhamLineAlgorithm line = new BresenhamLineAlgorithm(touchRasterDevice);
				line.add(new TwoPointsLine(5,  5, 30, 10, Color.MAGENTA));
				line.add(new TwoPointsLine(5,  1, 31,  3, Color.PINK));
			}
		});

	}

}
