package com.dvlcube.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author wonka
 * @since 24/05/2013
 */
public class ImageUtils {
	public static BufferedImage binarize(BufferedImage original) {

		int red;
		int newPixel;

		int threshold = otsuTreshold(original);

		BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

		for (int i = 0; i < original.getWidth(); i++) {
			for (int j = 0; j < original.getHeight(); j++) {
				// Get pixels
				red = new Color(original.getRGB(i, j)).getRed();
				int alpha = new Color(original.getRGB(i, j)).getAlpha();
				if (red > threshold) {
					newPixel = 255;
				} else {
					newPixel = 0;
				}

				newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
				binarized.setRGB(i, j, newPixel);
			}
		}
		return binarized;
	}

	// Convert R, G, B, Alpha to standard 8 bit
	public static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;

		return newPixel;
	}

	// Return histogram of grayscale image
	public static int[] imageHistogram(BufferedImage input) {
		int[] histogram = new int[256];

		for (int i = 0; i < histogram.length; i++) {
			histogram[i] = 0;
		}

		for (int i = 0; i < input.getWidth(); i++) {
			for (int j = 0; j < input.getHeight(); j++) {
				int red = new Color(input.getRGB(i, j)).getRed();
				histogram[red]++;
			}
		}

		return histogram;
	}

	// Get binary threshold using Otsu's method
	public static int otsuTreshold(BufferedImage original) {
		int[] histogram = imageHistogram(original);
		int total = original.getHeight() * original.getWidth();

		float sum = 0;
		for (int i = 0; i < 256; i++) {
			sum += i * histogram[i];
		}

		float sumB = 0;
		int wB = 0;
		int wF = 0;

		float varMax = 0;
		int threshold = 0;

		for (int i = 0; i < 256; i++) {
			wB += histogram[i];
			if (wB == 0) {
				continue;
			}
			wF = total - wB;

			if (wF == 0) {
				break;
			}

			sumB += i * histogram[i];
			float mB = sumB / wB;
			float mF = (sum - sumB) / wF;

			float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

			if (varBetween > varMax) {
				varMax = varBetween;
				threshold = i;
			}
		}
		return threshold;
	}

	/**
	 * @param image
	 * @param w
	 *            new width
	 * @param h
	 *            new height
	 * @return a resized version of the image.
	 * @author wonka
	 * @since 24/05/2013
	 */
	public static BufferedImage resize(BufferedImage image, int w, int h) {
		BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, w, h, null);
		g.dispose();
		return resizedImage;
	}

	/**
	 * @param image
	 * @param percent
	 *            percent of the size desired.
	 * @return
	 * @author wonka
	 * @since 24/05/2013
	 */
	public static BufferedImage scale(BufferedImage image, int percent) {
		// original height / original width x new width = new height
		int width = image.getWidth();
		int height = image.getHeight();
		int newWidth = width * percent / 100;
		int newHeight = (int) ((double) height / width * newWidth);
		return resize(image, newWidth, newHeight);
	}

	/**
	 * @param image
	 * @return The image, ascii format.
	 * @author wonka
	 * @since 24/05/2013
	 */
	public static String toAscii(BufferedImage image) {
		StringBuilder builder = new StringBuilder();

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int red = new Color(image.getRGB(x, y)).getRed();

				if (red >= 0 && red <= 15) {
					builder.append("██");
				} else if (red > 15 && red <= 75) {
					builder.append("▓▓");
				} else if (red > 75 && red <= 135) {
					builder.append("▒▒");
				} else if (red > 135 && red <= 195) {
					builder.append("░░");
				} else if (red > 195 && red <= 255) {
					builder.append("  ");
				} else if (red < 0 || red > 255) {
					builder.append("oo");
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * @param fileName
	 *            file name.
	 * @return the image, ascii format
	 * @author wonka
	 * @since 24/05/2013
	 */
	public static String toAscii(String fileName) {
		File file = new File(fileName);
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			return toAscii(image);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * @param fileName
	 * @param scale
	 *            scale factor (in percent)
	 * @return the ascii representation.
	 * @author wonka
	 * @since 24/05/2013
	 */
	public static String toAscii(String fileName, int scale) {
		File file = new File(fileName);
		BufferedImage image;
		try {
			image = ImageIO.read(file);
			return toAscii(scale(image, scale));
		} catch (IOException e) {
			return null;
		}
	}

	// The luminance method
	public static BufferedImage toGray(BufferedImage original) {
		int alpha, red, green, blue;
		int newPixel;

		BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
		for (int i = 0; i < original.getWidth(); i++) {
			for (int j = 0; j < original.getHeight(); j++) {

				// Get pixels by R, G, B
				alpha = new Color(original.getRGB(i, j)).getAlpha();
				red = new Color(original.getRGB(i, j)).getRed();
				green = new Color(original.getRGB(i, j)).getGreen();
				blue = new Color(original.getRGB(i, j)).getBlue();

				red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
				// Return back to original format
				newPixel = colorToRGB(alpha, red, red, red);

				// Write pixels into image
				lum.setRGB(i, j, newPixel);
			}
		}
		return lum;
	}
}
