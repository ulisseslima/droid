package com.dvlcube.util;

import static com.dvlcube.util.Cuber.$f;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @since 21/06/2013
 * @author wonka
 */
public class CubeImage {

	public BufferedImage image;
	public Class<?> origin;
	public String path;

	public CubeImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @param aClass
	 *            class to load the resource from.
	 * @param path
	 *            path to resource.
	 * @since 27/06/2013
	 * @author wonka
	 */
	public CubeImage(Class<?> aClass, String path) {
		try {
			this.path = path;
			origin = aClass;
			BufferedImage image = ImageIO.read(aClass.getResourceAsStream(path));
			this.image = image;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param path
	 *            path to file.
	 * @since 26/06/2013
	 * @author wonka
	 */
	public CubeImage(String path) {
		try {
			this.path = path;
			BufferedImage image = ImageIO.read($f(path).file);
			this.image = image;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int h() {
		return image.getHeight();
	}

	/**
	 * @return image pixels.
	 * @since 26/06/2013
	 * @author wonka
	 */
	public int[] pixels() {
		return ImageUtils.pixels(image);
	}

	public byte[] pixelsb() {
		return ImageUtils.pixelsb(image);
	}

	public Object pixelsg() {
		return ImageUtils.pixelsg(image);
	}

	public int[] pixelsi() {
		return ImageUtils.pixelsi(image);
	}

	public int w() {
		return image.getWidth();
	}

	/**
	 * @param pixels
	 *            pixels to override.
	 * @since 26/06/2013
	 * @author wonka
	 */
	public void write(int[] pixels) {
		if (path == null) {
			throw new IllegalArgumentException("image doesn't exist in the disk yet");
		}

		ImageUtils.write(pixels, new Dimension(image.getWidth(), image.getHeight()), path);
	}

	/**
	 * @param pixels
	 *            pixels to override.
	 * @param path
	 *            desired file path.
	 * @since 26/06/2013
	 * @author wonka
	 */
	public void write(int[] pixels, String path) {
		this.path = path;
		write(pixels);
	}
}
