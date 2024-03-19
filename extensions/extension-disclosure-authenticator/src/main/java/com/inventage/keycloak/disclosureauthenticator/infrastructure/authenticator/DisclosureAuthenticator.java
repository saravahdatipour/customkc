package com.inventage.keycloak.disclosureauthenticator.infrastructure.authenticator;

import org.keycloak.Config;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.*;
import org.keycloak.provider.ProviderConfigProperty;
import org.jboss.logging.Logger;

import java.util.List;


public class DisclosureAuthenticator implements Authenticator  {
    private static final Logger LOG = Logger.getLogger(DisclosureAuthenticator.class);

    @Override
    public void authenticate(AuthenticationFlowContext authenticationFlowContext) {
        LOG.warnf("authenticate");
        authenticationFlowContext.success();
    }

    @Override
    public void action(AuthenticationFlowContext authenticationFlowContext) {
        LOG.warnf("action");
        authenticationFlowContext.success();
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}