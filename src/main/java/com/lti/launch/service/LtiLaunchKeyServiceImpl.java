package com.lti.launch.service;

import edu.ksu.lti.launch.service.LtiLaunchKeyService;

import java.util.HashMap;
import java.util.Map;


/**
 * This is just an example implementation, these configuration can come from a datasource and property files
 */
public class LtiLaunchKeyServiceImpl implements LtiLaunchKeyService {
    private Map<String, String> secretKeys;

    public LtiLaunchKeyServiceImpl(){
        secretKeys = new HashMap<>();
    }

    public void setSecretForKey(String key, String secret) {
        secretKeys.put(key, secret);
    }

    @Override
    public String findSecretForKey(String key) {
        return secretKeys.get(key);
    }
}
