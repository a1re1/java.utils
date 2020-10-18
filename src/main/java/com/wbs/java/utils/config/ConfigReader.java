package com.wbs.java.utils.config;

import com.wbs.simplog.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    private static final Log LOG = new Log(ConfigReader.class);

    public static Config readConfig(String filePath, String configName) {
        File tmp = new File(filePath);
        if (!tmp.exists()) {
            LOG.info("{}", tmp.mkdirs()
                    ? "Created " + filePath + " folder because it did not already exist"
                    : "Could not create directory: " + filePath
            );
        }

        Config properties = new Config();

        File config = new File(filePath + configName);
        if (!tmp.exists()) {
            LOG.warn("No config file exists, using defaults.");
        } else {
            try {
                BufferedReader in = new BufferedReader(new FileReader(config));
                String line = in.readLine();
                line = line.trim();
                while (line != null) {
                    // skip comment lines
                    if (!(line.startsWith("#") || line.equals(""))) {
                        String[] removeComments = line.split("#");

                        if (removeComments.length > 0) {
                            String body = removeComments[0];
                            int firstEqual = body.indexOf("=");
                            if (firstEqual == -1) {
                                properties.put(body, "");
                                LOG.info("Set property: {}", body);
                            } else {
                                String property = body.substring(0, firstEqual);
                                String value = body.substring(firstEqual + 1);
                                LOG.info("Loaded property: {} with value: {}", property, value);
                                properties.put(property, value);
                            }
                        }
                    }
                    line = in.readLine();
                }
                in.close();
            } catch (IOException e) {
                LOG.error(
                        "Something went wrong when reading in config file. {}",
                        e
                );
            }
        }

        return properties;
    }
}
