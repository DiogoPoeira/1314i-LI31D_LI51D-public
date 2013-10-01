import static util.RasterDeviceAssert.assertPixels;

import java.awt.Color;

import org.junit.Test;

import util.TestRasterDevice;
import draw.algorithms.BresenhamLineAlgorithm;
import draw.shapes.Line;
import draw.shapes.TwoPointsLine;


public class TestDrawHorizontalLine {

	@Test
	public void should_draw_straight_horizontal_line() {
		// Arrange
		TestRasterDevice device = new TestRasterDevice();
		BresenhamLineAlgorithm la = new BresenhamLineAlgorithm(device);
		Line line = new TwoPointsLine(0, 0, 5, 0, Color.RED);
		la.add(line);

		// Act
		device.render();

		// Assert
		assertPixels(device, "0  1, 1  1, 2  1, 3  1, 4  1, 5  1", device.getBackColor());
		assertPixels(device, "0  0, 1  0, 2  0, 3  0, 4  0, 5  0", Color.RED);
		assertPixels(device, "0 -1, 1 -1, 2 -1, 3 -1, 4 -1, 5 -1", device.getBackColor());
	}
}