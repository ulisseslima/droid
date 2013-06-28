package com.dvlcube.cuber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author wonka
 * @since 24/05/2013
 */
public class ImageUtils {
	private static final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
	private static final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);

	/**
	 * @param original
	 * @return binarized image.
	 * @since 21/06/2013
	 * @author wonka
	 */
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

	/**
	 * Convert R, G, B, Alpha to standard 8 bit
	 * 
	 * @param alpha
	 * @param red
	 * @param green
	 * @param blue
	 * @return standard 8 bit
	 * @since 21/06/2013
	 * @author wonka
	 */
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

	/**
	 * http://stackoverflow.com/questions/4386446/problem-using-imageio-write-jpg-file/4388542#4388542
	 * <p>
	 * I needed to make all this stuff with rgb masks and the color model because otherwise pixels wouldn't
	 * get drawn correctly.
	 * 
	 * @param pixels
	 *            the pixels.
	 * @param width
	 *            width.
	 * @param height
	 *            height.
	 * @return a buffered image created from the pixel array.
	 * @since 25/06/2013
	 * @author wonka
	 */
	public static BufferedImage draw(int[] pixels, int width, int height) {
		DataBuffer buffer = new DataBufferInt(pixels, width * height);
		WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
		BufferedImage image = new BufferedImage(RGB_OPAQUE, raster, false, null);
		return image;
	}

	/**
	 * @param input
	 * @return Return histogram of grayscale image
	 * @since 21/06/2013
	 * @author wonka
	 */
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

	public static int[] pixels(BufferedImage image) {
		return image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
	}

	public static byte[] pixelsb(BufferedImage image) {
		DataBuffer dataBuffer = image.getRaster().getDataBuffer();
		if (dataBuffer instanceof DataBufferByte) {
			return ((DataBufferByte) dataBuffer).getData();
		}
		throw new IllegalArgumentException("image is instanceof " + dataBuffer.getClass());
	}

	/**
	 * http://stackoverflow.com/questions/4386446/problem-using-imageio-write-jpg-file/4388542#4388542
	 * 
	 * @param image
	 * @return image pixels.
	 * @since 26/06/2013
	 * @author wonka
	 */
	public static Object pixelsg(BufferedImage image) {
		PixelGrabber pg = new PixelGrabber(image, 0, 0, -1, -1, true);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return pg.getPixels();
	}

	/**
	 * @param image
	 *            the image.
	 * @return image pixels.
	 * @since 21/06/2013
	 * @author wonka
	 */
	public static int[] pixelsi(BufferedImage image) {
		DataBuffer dataBuffer = image.getRaster().getDataBuffer();
		if (dataBuffer instanceof DataBufferInt) {
			return ((DataBufferInt) dataBuffer).getData();
		}
		throw new IllegalArgumentException("image is instanceof " + dataBuffer.getClass());
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

	/**
	 * The luminance method
	 * 
	 * @param original
	 * @return grayscale image.
	 * @since 21/06/2013
	 * @author wonka
	 */
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

	public static void write(int[] pixels, Dimension dimension, String file) {
		BufferedImage image = draw(pixels, dimension.width, dimension.height);
		try {
			ImageIO.write(image, "png", new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
