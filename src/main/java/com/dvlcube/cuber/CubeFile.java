package com.dvlcube.cuber;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @since 25/06/2013
 * @author wonka
 */
public class CubeFile {
	public File file;

	public CubeFile(String path) {
		this(path, true);
	}

	/**
	 * @param path
	 * @since 25/06/2013
	 * @author wonka
	 */
	public CubeFile(String path, boolean create) {
		file = new File(path);
		if (!file.isFile()) {
			if (create) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				throw new IllegalArgumentException("'" + path + "' is not a file");
			}
		}
	}

	public boolean isDir() {
		return file.isDirectory();
	}
}
