package a;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;

public class Operations {
	/**
	 * RGB to grey sclae converter
	 *
	 * @param bimg
	 *            buffered image
	 * @return buffered image converted to greyscale
	 */
	public BufferedImage getGreyscaleImage(BufferedImage bimg) {
		for (int x = 0; x < bimg.getWidth(); ++x)
			for (int y = 0; y < bimg.getHeight(); ++y) {
				int rgb = bimg.getRGB(x, y);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);

				int grayLevel = (r + g + b) / 3;
				int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
				bimg.setRGB(x, y, gray);
			}
		return bimg;
	}

	public BufferedImage getredImage(BufferedImage bimg) {
		for (int x = 0; x < bimg.getWidth(); ++x)
			for (int y = 0; y < bimg.getHeight(); ++y) {
				int rgb = bimg.getRGB(x, y);
				int a=(rgb >> 24) & 0xFF;
				int r = (rgb >> 16) & 0xFF;
			
				 rgb = (a << 24) + (r << 16) + (0<<8)+0;
				bimg.setRGB(x, y, rgb);
			}
		return bimg;
	}
	
	public BufferedImage getGreenImage(BufferedImage bimg) {
		for (int x = 0; x < bimg.getWidth(); ++x)
			for (int y = 0; y < bimg.getHeight(); ++y) {
				int rgb = bimg.getRGB(x, y);
				int a=(rgb >> 24) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
			
				 rgb = (a << 24) + (0 << 16) + (g<<8)+0;
				bimg.setRGB(x, y, rgb);
			}
		return bimg;
	}
	
	public BufferedImage getBlueImage(BufferedImage bimg) {
		for (int x = 0; x < bimg.getWidth(); ++x)
			for (int y = 0; y < bimg.getHeight(); ++y) {
				int rgb = bimg.getRGB(x, y);
				int a=(rgb >> 24) & 0xFF;
				int b = (rgb & 0xFF);
			
				 rgb = (a << 24) + (0 << 16) + (0<<8)+b;
				bimg.setRGB(x, y, rgb);
			}
		return bimg;
	}

	public double[] RGBtoHSV(double r, double g, double b) {
        
		double h, s, v;

		double min, max, delta;

		min = Math.min(Math.min(r, g), b);
		max = Math.max(Math.max(r, g), b);

		// V
		v = max;

		delta = max - min;

		// S
		if (max != 0)
			s = delta / max;
		else {
			s = 0;
			h = -1;
			return new double[] { h, s, v };
		}

		// H
		if (r == max)
			h = (g - b) / delta; // between yellow & magenta
		else if (g == max)
			h = 2 + (b - r) / delta; // between cyan & yellow
		else
			h = 4 + (r - g) / delta; // between magenta & cyan

		h *= 60; // degrees

		if (h < 0)
			h += 360;

		return new double[] { h, s, v };

	}

}
