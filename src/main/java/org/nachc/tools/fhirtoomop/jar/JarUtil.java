package org.nachc.tools.fhirtoomop.jar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JarUtil {

	public static List<String> getResources(String path) {
		try {
			return new JarUtil().getResourceFiles(path);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public List<String> getResourceFiles(String path) throws IOException {
		List<String> filenames = new ArrayList<>();
		try (InputStream in = getResourceAsStream(path); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String resource;
			while ((resource = br.readLine()) != null) {
				resource = path + "/" + resource;
				filenames.add(resource);
			}
		}
		return filenames;
	}

	private InputStream getResourceAsStream(String resource) {
		final InputStream in = getContextClassLoader().getResourceAsStream(resource);
		return in == null ? getClass().getResourceAsStream(resource) : in;
	}

	private ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

}
