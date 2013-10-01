/**********************************************************************************
 * Instituto Superior de Engenharia de Lisboa
 * Área Departamental de Engenharia de Electrónica e Telecomunicações e de Computadores
 * Licenciatura em Engenharia Informática e de Computadores
 * Computação Gráfica
 *
 * (c) Carlos Guedes - 2013
 **********************************************************************************/

package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

/**
 * 2D graphics device
 * 
 *  This type represents a 2d raster-based graphics
 *  device with touch capabilities.
 * 
 *  <p>
 *  It fires a render event in all registered {@link IRenderListener}s
 *  with a frame rate of 40fps.
 *  </p>
 * 
 *  <p>
 *  It contains two debug functionalities:
 *  <ul>
 *  	<li>Update of grid size upon mouse wheel scroll
 *  	<li>Enable/disable of debug mode with key 'd'
 *  </ul>
 *  </p>
 * 
 * @author Carlos Guedes
 *
 */
public class TouchRasterDevice extends Canvas implements IRasterDevice, ITouchDevice, IRenderContext {

	private static final long serialVersionUID = -1583834384008011722L;
	private boolean initialized = false;

	private final List<IRenderListener> renderListeners = new LinkedList<IRenderListener>();
	private final List<ITouchListener> touchListeners = new LinkedList<ITouchListener>();

	private int pixelSize;

	private final Point mousePosition = new Point();
	private Graphics currentGraphics;
	protected boolean showDebug = true;

	public TouchRasterDevice(final int pixelSize) {
		this.pixelSize = pixelSize;
	}

	public TouchRasterDevice() {
		this(10);
	}

	@Override
	public Component getComponent() { return this; }

	@Override
	public void setPixel(final int x, final int y, final Color color) {
		Point pos = deviceToReal(new Point(x, y));
		currentGraphics.setColor(color);
		currentGraphics.fillRect(pos.x, pos.y, pixelSize, pixelSize);
	}

	private void initialize()
	{
		if(initialized) throw new java.lang.IllegalStateException("The raster device is already initialized");

		final UpdateLoop updateLoop = new UpdateLoop(new RenderCallback() {
			@Override
			public void render(final Graphics g)
			{
				currentGraphics = g;
				paint(g);
				for (IRenderListener renderListener : renderListeners) {
					renderListener.render(TouchRasterDevice.this);
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(final MouseEvent me) {
				Point devicePoint = realToDevice(me.getPoint());
				mousePosition.setLocation( devicePoint );
				for (ITouchListener touchListener : touchListeners) {
					touchListener.onMove(devicePoint.x, devicePoint.y);
				}
			}
			@Override
			public void mouseDragged(final MouseEvent me) {
				Point devicePoint = realToDevice(me.getPoint());
				mousePosition.setLocation( devicePoint );
				for (ITouchListener touchListener : touchListeners) {
					touchListener.onDrag(devicePoint.x, devicePoint.y, me.getButton());
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent me) {
				Point devicePoint = realToDevice(me.getPoint());
				for (ITouchListener touchListener : touchListeners) {
					touchListener.onClick(devicePoint.x, devicePoint.y, me.getButton());
				}
			}
		});

		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(final MouseWheelEvent e) {
				pixelSize -= e.getWheelRotation();
				if(pixelSize <= 0) pixelSize = 1;
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(final KeyEvent e) {
				super.keyTyped(e);
				if(Character.toLowerCase( e.getKeyChar() ) == 'd') {
					showDebug = !showDebug;
				}
			}
		});

		this.requestFocus();

		final Thread updateThread = new Thread(updateLoop);
		updateThread.setPriority(Thread.MIN_PRIORITY);
		updateThread.start();
		initialized = true;
	}


	/**
	 * Real axis                        Device axis
	 * 
	 *  O═══════>---------------+       +-----------------------+
	 *  ║       X               |       |        Y ^            |
	 *  ║                       |       |          ║            |
	 *  ║                       |       |          ║            |
	 *  v Y                     |       |          O═══════>    |
	 *  |                       |       |                  X    |
	 *  |                       |       |                       |
	 *  |                       |       |                       |
	 *  +-----------------------+       +-----------------------+
	 */
	protected Point realToDevice(final Point realPoint) {
		Point devicePoint = new Point(realPoint);

		devicePoint.x = devicePoint.x * 1;
		devicePoint.y = devicePoint.y * -1;

		devicePoint.x = (int)(devicePoint.x * (1.0 / pixelSize));
		devicePoint.y = (int)(devicePoint.y * (1.0 / pixelSize));

		devicePoint.x = devicePoint.x - (getWidth() / pixelSize) / 2;
		devicePoint.y = devicePoint.y + (getHeight() / pixelSize) / 2;

		return devicePoint;
	}

	protected Point deviceToReal(final Point devicePoint) {
		Point realPoint = new Point(devicePoint);

		realPoint.x = realPoint.x + (getWidth() / pixelSize) / 2;
		realPoint.y = realPoint.y - (getHeight() / pixelSize) / 2;

		realPoint.x = realPoint.x * pixelSize;
		realPoint.y = realPoint.y * pixelSize;

		realPoint.x = realPoint.x * 1;
		realPoint.y = realPoint.y * -1;

		return realPoint;
	}

	@Override
	public void addTouchListener(final ITouchListener touchListener) {
		touchListeners.add(touchListener);
	}
	@Override
	public void addRenderListener(final IRenderListener renderListener) {
		renderListeners.add(renderListener);
	}

	@Override
	public void paint(final Graphics g) {
		if(!initialized) initialize();
		currentGraphics = g;

		if(showDebug)
		{
			if(pixelSize >= 6) {
				drawGrid(g);
			}
			drawAxis(g);
		}

		// Draw pixel in the mouse position
		g.setColor(Color.LIGHT_GRAY);
		Point devicePoint = deviceToReal(mousePosition);
		g.fillRect(devicePoint.x, devicePoint.y, pixelSize, pixelSize);
	}

	private void drawGrid(final Graphics g)
	{
		// Draw squares (pixels)
		g.setColor(Color.lightGray);
		for (int y = 0; y < getHeight(); y += pixelSize)
		{
			for (int x = 0; x < getWidth(); x += pixelSize)
			{
				g.drawRect(x, y, pixelSize, pixelSize);
			}
		}
	}

	private void drawAxis(final Graphics g)
	{
		Point realCenter = deviceToReal(new Point(0, 0));
		// xx
		g.drawLine(realCenter.x+pixelSize/2,           realCenter.y+pixelSize-pixelSize/2,
				realCenter.x+pixelSize/2+getWidth()/4, realCenter.y+pixelSize-pixelSize/2);
		// yy
		g.drawLine(realCenter.x+pixelSize/2,           realCenter.y+pixelSize-pixelSize/2,
				realCenter.x+pixelSize/2,              realCenter.y+pixelSize-getHeight()/4);


		// axis pixels
		setPixel(0, 0, Color.black);
		setPixel(1, 0, Color.red.darker().darker());
		setPixel(2, 0, Color.red.darker());
		setPixel(3, 0, Color.red);

		setPixel(0, 1, Color.green.darker().darker());
		setPixel(0, 2, Color.green.darker());
		setPixel(0, 3, Color.green);
		// */
	}

	private class UpdateLoop implements Runnable
	{
		private static final int FRAME_DELAY = 40; // 40ms. implies 25fps (1000/40) = 25

		boolean isRunning;
		long cycleTime;
		private final RenderCallback renderCallback;

		public UpdateLoop(final RenderCallback renderCallback) {
			this.renderCallback = renderCallback;
			isRunning = true;
		}

		@Override
		public void run() {
			cycleTime = System.currentTimeMillis();
			createBufferStrategy(2);
			final BufferStrategy strategy = getBufferStrategy();

			// Application Loop
			while (isRunning) {
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							updateGUI(strategy);
						}
					});
				} catch (InvocationTargetException e) {
					System.exit(1);
				} catch (InterruptedException e) {
					System.exit(1);
				}
				synchFramerate();
			}
		}


		private void updateGUI(final BufferStrategy strategy) {
			Graphics g = strategy.getDrawGraphics();

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());

			g.setColor(Color.BLACK);
			renderCallback.render(g);

			g.dispose();
			strategy.show();
		}

		private void synchFramerate() {
			cycleTime = cycleTime + FRAME_DELAY;
			long difference = cycleTime - System.currentTimeMillis();
			try {
				Thread.sleep(Math.max(0, difference));
			}
			catch(InterruptedException e) {
				isRunning = false;
			}
		}

	}

}

interface RenderCallback
{
	void render(Graphics graphics);
}

