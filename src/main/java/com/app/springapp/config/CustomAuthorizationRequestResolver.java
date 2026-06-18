package com.app.springapp.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final DefaultOAuth2AuthorizationRequestResolver delegate;

    public CustomAuthorizationRequestResolver(ClientRegistrationRepository repo) {
        delegate = new DefaultOAuth2AuthorizationRequestResolver(repo, "/oauth2/authorization");
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        return customize(delegate.resolve(request));
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        return customize(delegate.resolve(request, clientRegistrationId));
    }

    private OAuth2AuthorizationRequest customize(OAuth2AuthorizationRequest req) {
        if (req == null) return null;
        String registrationId = (String) req.getAttributes().get(OAuth2ParameterNames.REGISTRATION_ID);
        if ("kakao".equals(registrationId)) {
            return OAuth2AuthorizationRequest.from(req)
                    .additionalParameters(params -> params.put("prompt", "login"))
                    .build();
        }
        if ("google".equals(registrationId)) {
            return OAuth2AuthorizationRequest.from(req)
                    .additionalParameters(params -> params.put("prompt", "login"))
                    .build();
        }
        if ("naver".equals(registrationId)) {
            return OAuth2AuthorizationRequest.from(req)
                    .additionalParameters(params -> params.put("auth_type", "reauthenticate"))
                    .build();
        }
        return req;
    }
}
