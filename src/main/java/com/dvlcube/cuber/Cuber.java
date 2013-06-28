package com.dvlcube.cuber;

import java.awt.image.BufferedImage;

/**
 * 
 * @author wonka
 * @since 14/04/2013
 */
public class Cuber {
	public static CubeImage $(BufferedImage image) {
		return new CubeImage(image);
	}

	public static <T> CubeClass<T> $(Class<T> c) {
		return new CubeClass<T>(c);
	}

	public static Cube $(Object object) {
		return new Cube(object);
	}

	public static CubeString $(String string) {
		return new CubeString(string);
	}

	public static <T> CubeArray<T> $(T[] array) {
		return new CubeArray<T>(array);
	}

	public static CubeFile $f(String path) {
		return new CubeFile(path);
	}

	public static CubeImage $img(Class<?> origin, String path) {
		return new CubeImage(origin, path);
	}

	public static CubeImage $img(String path) {
		return new CubeImage(path);
	}
}
