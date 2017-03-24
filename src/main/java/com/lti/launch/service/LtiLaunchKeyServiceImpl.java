package com.lti.launch.service;

import edu.ksu.lti.launch.service.LtiLaunchKeyService;

import java.util.HashMap;
import java.util.Map;


/**
 * This service is able to retrieve the application's shared secret if given
 * the key that was used to configure the LTI application in Canvas. The secret
 * is used to sign and verify the LTI launch request coming from the user's browser.
 * <p>
 * This could come from a database or an application properties file.
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
