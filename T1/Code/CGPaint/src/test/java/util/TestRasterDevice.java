package util;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;

import core.IRenderContext;
import core.IRenderListener;
import core.ITouchListener;
import core.ITouchRasterDevice;


public class TestRasterDevice implements ITouchRasterDevice, IRenderContext {
	private static final Color BG_COLOR = Color.WHITE;
	private static final int HEIGHT = 100;
	private static final int WIDTH = 100;
	private IRenderListener renderListener;

	@Override
	public Component getComponent() { return null; }

	@Override
	public void addRenderListener(final IRenderListener renderListener) {
		this.renderListener = renderListener;
	}

	@Override
	public void addTouchListener(final ITouchListener touchListener) {
		// ignored...
	}

	public void render() {
		renderListener.render(this);
	}

	Color[][] pixels = new Color[WIDTH][HEIGHT];

	public TestRasterDevice() {
		for (Color[] pixel : pixels) {
			Arrays.fill(pixel, BG_COLOR);
		}
	}

	@Override
	public void setPixel(final int x, final int y, final Color color) {
		pixels[x+WIDTH/2][y+HEIGHT/2] = color;
	}

	public Color getPixel(final int x, final int y) {
		return pixels[x+WIDTH/2][y+HEIGHT/2];
	}

	public int getWidth() { return WIDTH;	}
	public int getHeight() { return HEIGHT; }

	public Color getBackColor() { return BG_COLOR; }
}