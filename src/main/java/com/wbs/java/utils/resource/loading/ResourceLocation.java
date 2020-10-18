package com.wbs.java.utils.resource.loading;

import java.io.InputStream;
import java.net.URL;

public interface ResourceLocation {
	InputStream getResourceAsStream(String ref);
	URL getResource(String ref);
}
