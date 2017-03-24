package com.lti.launch.service;

import edu.ksu.lti.launch.service.ConfigService;

import java.util.HashMap;
import java.util.Map;

/**
 * This service is able to look up arbitrary config values stored as a
 * key/value pair.
 * <p>
 * These values could come from a database or application properties file
 */
public class ConfigServiceImpl implements ConfigService {
    private final Map<String,String> properties;

    public ConfigServiceImpl() {
        properties = new HashMap<>();
    }

    @Override
    public String getConfigValue(String key) {
        return properties.get(key);
    }

    public void setConfigValue(String key, String value) {
        properties.put(key, value);
    }
}
