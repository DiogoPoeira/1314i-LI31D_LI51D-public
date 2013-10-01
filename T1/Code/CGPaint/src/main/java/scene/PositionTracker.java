package scene;
/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

import java.text.MessageFormat;

import javax.swing.JLabel;

import core.ITouchDevice;
import core.ITouchListener;
import core.TouchAdapter;

/**
 * A Simple mouse position tracker that uses a {@link JLabel}
 * to display the current device coordinates of mouse.
 * 
 * <p>
 * It registers a {@link ITouchListener} in the {@link ITouchDevice}
 * and observes the changes in the <code>onMouse</code> event.
 * </p>
 * 
 * @author Carlos Guedes
 *
 */
public class PositionTracker extends JLabel {

	private static final long serialVersionUID = -5173565266515888566L;

	public PositionTracker(final ITouchDevice touchRasterDevice) {

		touchRasterDevice.addTouchListener(new TouchAdapter() {
			@Override
			public void onMove(final int x, final int y) {
				setText(MessageFormat.format("({0}, {1})", x, y));
			}
		});
	}

}
