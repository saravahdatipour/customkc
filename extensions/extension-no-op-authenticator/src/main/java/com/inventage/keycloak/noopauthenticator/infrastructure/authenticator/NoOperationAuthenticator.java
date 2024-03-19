package com.inventage.keycloak.noopauthenticator.infrastructure.authenticator;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;


public class NoOperationAuthenticator implements Authenticator {

    private static final Logger LOG = Logger.getLogger(NoOperationAuthenticator.class);

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
        LOG.warnf("requiresUser");
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        LOG.debugf("configuredFor");
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        LOG.debugf("setRequiredActions");
        //NOP
    }

    @Override
    public void close() {
        LOG.debugf("close");
        //NOP
    }
}
