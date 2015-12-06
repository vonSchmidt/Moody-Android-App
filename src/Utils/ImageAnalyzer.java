package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


public class ImageAnalyzer {

	private Map<Integer, Integer> pixelMap = new HashMap<Integer, Integer>();
	
	public void scan (File file) throws Exception {
		ImageInputStream is = ImageIO.createImageInputStream(file);
		Iterator<?> iter = ImageIO.getImageReaders(is);

		if (!iter.hasNext())
		{
			System.out.println("Cannot load the specified file "+ file);
			return;
		}
		ImageReader imageReader = (ImageReader)iter.next();
		imageReader.setInput(is);

		BufferedImage image = imageReader.read(0);

		int height = image.getHeight();
		int width = image.getWidth();

		for(int i=0; i < width ; i++)
		{
			for(int j=0; j < height ; j++)
			{
				int rgb = image.getRGB(i, j);
				int[] rgbArr = getRGBArr(rgb);
				if (!isGray(rgbArr)) {
					Integer counter = (Integer) pixelMap.get(rgb);
					if (counter == null)
						counter = 0;
					counter++;
					pixelMap.put(rgb, counter);
				}
			}
		}
		String colorHex = getMostFrequentColor();
		System.out.println(colorHex);
	}


	public String getMostFrequentColor() {
		List<Object> list = new LinkedList<Object>(pixelMap.entrySet());
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		Map.Entry me = (Map.Entry)list.get(list.size()-1);
		int[] rgb= getRGBArr((Integer)me.getKey());
		return (
				"#"
				+ String.format("%02X", rgb[0])
				+ String.format("%02X", rgb[1])
				+ String.format("%02X", rgb[2])
				);
	}

	private static int[] getRGBArr(int pixel) {
		//int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return new int[]{red, green, blue};

	}
	private static boolean isGray(int[] rgbArr) {
		int rgDiff = rgbArr[0] - rgbArr[1];
		int rbDiff = rgbArr[0] - rgbArr[2];
		// Filter out black, white and grays...... (tolerance within 10 pixels)
		int tolerance = 30;
		if (rgDiff > tolerance || rgDiff < -tolerance)
			if (rbDiff > tolerance || rbDiff < -tolerance) {
				return false;
			}
		return true;
	}
}