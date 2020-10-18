package com.wbs.java.utils.resource.loading;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class ResourceLoader {

	private static final ArrayList<ResourceLocation> locations = new ArrayList<>();
	
	static {
		locations.add(new ClasspathLocation());
		locations.add(new FileSystemLocation(new File(".")));
	}
	
	public static InputStream getResourceAsStream(String ref) {
		InputStream in = null;

		for (ResourceLocation location : locations) {
			in = location.getResourceAsStream(ref);
			if (in != null) {
				break;
			}
		}
		
		if (in == null) {
			throw new RuntimeException("Resource not found: "+ref);
		}

		return new BufferedInputStream(in);
	}
	
}
