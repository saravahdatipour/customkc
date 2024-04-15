package com.inventage.keycloak.disclosureauthenticator.infrastructure.authenticator;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.keycloak.Config;
import org.keycloak.authentication.*;
import org.keycloak.authentication.authenticators.directgrant.ValidatePassword;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialProvider;
import org.keycloak.credential.PasswordCredentialProvider;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.events.Errors;
import org.keycloak.jose.jwk.JWK;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.credential.PasswordUserCredentialModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.services.messages.Messages;
import org.keycloak.services.validation.Validation;

import java.net.URI;


public class DisclosureAuthenticator implements Authenticator  {
    private static final Logger LOG = Logger.getLogger(DisclosureAuthenticator.class);
    public static final String DISCLOSURE_FORM = "disclosure-form.ftl";


    // TODO: write authenticate logic
    @Override
    public void authenticate(AuthenticationFlowContext context) {
        // 1. This method is called first.
        // It checks if the current request meets the requirements of the authenticator.
        // If not, it may prompt the user for additional information or actions.
        LOG.warnf("authenticate");


        Response challenge = context.form()

                .createForm(DISCLOSURE_FORM);

        context.challenge(challenge);

    }


    @Override
    public boolean requiresUser() {
        // 2. requiresUser(): After the initial authentication check,
        // this method determines if the authenticator requires an identified user to proceed.
        // This helps the authentication flow decide if user identification steps are needed.
        return false;
    }




    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        //3.configuredFor(KeycloakSession session, RealmModel realm, UserModel user):
        // If the authenticator requires a user, this method checks whether the authenticator is configured correctly
        // for the specific user in question.
        return true;
    }


    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void action(AuthenticationFlowContext context) {
        LOG.warnf("action");
        MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
        String username = formData.getFirst("username");
        String password = formData.getFirst("password");

        //Validate form

        if (Validation.isBlank(username) || Validation.isBlank((password))) {
            //Form is empty somewhere
            context.getEvent().error("Username is missing");
            Response challenge = context.form()
                    .createForm(DISCLOSURE_FORM);

            context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS, challenge);
            return;
        }

        //find user
        UserModel user = context.getSession().users().getUserByUsername(context.getRealm(), username);
        if (user == null) {
            // no user matched
            context.getEvent().error(Errors.USER_NOT_FOUND);
            Response challenge = context.form()
                    .setError(Messages.INVALID_USER)
                    .createForm(DISCLOSURE_FORM);
            context.failureChallenge(AuthenticationFlowError.INVALID_USER, challenge);
            return;
        }
        CredentialInput input = UserCredentialModel.password(password);
        boolean isValid = user.credentialManager().isValid(input);

        if (isValid) {
            // Password is valid
            context.setUser(user);
            context.success();
        } else {
            // Password is invalid
            context.getEvent().user(user).error(Errors.INVALID_USER_CREDENTIALS);
            Response challenge = context.form()
                    .setError(Messages.INVALID_PASSWORD)
                    .createForm(DISCLOSURE_FORM);
            context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS, challenge);
        }

    }



    @Override
    public void close() {

    }
}