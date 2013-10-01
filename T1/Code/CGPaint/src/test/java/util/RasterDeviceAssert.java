package util;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RasterDeviceAssert {

	private static Pattern patternFull = Pattern.compile("(\\s*-?\\d+\\s+-?\\d+\\s*)(\\s*,\\s*-?\\d+\\s+-?\\d+\\s*)*");
	private static Pattern pattern = Pattern.compile("(-?\\d+)\\s+(-?\\d+)");

	public static void assertPixels(
			final TestRasterDevice device,
			final String expectedPixels,
			final Color expectedColor)
	{
		if(!patternFull.matcher(expectedPixels).matches()) {
			fail("Invalid format for expected pixels: " + expectedPixels + ". It should be groups of two digits separated by a coma (e.g. 12 -3, 4 4, -1 0)");
		}

		Matcher matcher = pattern.matcher(expectedPixels);

		while(matcher.find()) {
			int x = Integer.parseInt(matcher.group(1));
			int y = Integer.parseInt(matcher.group(2));
			Color color = device.getPixel(x, y);
			if(!expectedColor.equals(color)) {
				fail(MessageFormat.format(
						"At point ({0}, {1}) color should be {2} instead of {3}",
						x, y, expectedColor, color));
			}
		}
	}

}
