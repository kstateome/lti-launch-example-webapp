package com.lti.launch.service;

import edu.ksu.lti.launch.service.OauthTokenService;

import java.util.HashMap;
import java.util.Map;

/**
 * Service to manage a user's OAuth token used to access the Canvas API.
 * Typically you would probably want to persist them to a database so that
 * your application doesn't have to re-authorize every time the server is
 * restarted.
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
