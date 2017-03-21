package com.lti.launch.service;

import edu.ksu.lti.launch.service.ConfigService;

import java.util.HashMap;
import java.util.Map;

/**
 * This is just an example implementation, these configurations can come from a datasource or property files
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
