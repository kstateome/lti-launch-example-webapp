package com.lti.launch.service;

import edu.ksu.lti.launch.service.OauthTokenService;

import java.util.HashMap;
import java.util.Map;

/**
 * This is just an example implementation, these configurations can come from a datasource
 */
public class OauthTokenServiceImpl implements OauthTokenService {
    public Map<String, String> tokens;

    public OauthTokenServiceImpl() {
        tokens = new HashMap<>();
    }

    @Override
    public String getRefreshToken(String userId) {
        return tokens.get(userId);
    }

    @Override
    public String storeToken(String userId, String refreshToken) {
        tokens.put(userId, refreshToken);
        return refreshToken;
    }

    @Override
    public String updateToken(String userId, String refreshToken) {
        tokens.put(userId, refreshToken);
        return refreshToken;
    }
}
