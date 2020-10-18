package com.wbs.java.utils.config;

import java.util.HashMap;

public class Config extends HashMap<String, String> {
    public String getAsStringOrDefault(String key, String defaultValue) {
        return this.get(key);
    }

    public Integer getAsIntOrDefault(String key, Integer defaultValue) {
        return Integer.parseInt(this.get(key));
    }

    public Long getAsLongOrDefault(String key, Long defaultValue) {
        return Long.parseLong(this.get(key));
    }
}
