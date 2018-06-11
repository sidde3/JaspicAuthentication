/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sid.redhat;

import io.undertow.security.api.AuthenticationMechanism;
import static io.undertow.security.api.AuthenticationMechanism.AuthenticationMechanismOutcome.AUTHENTICATED;
import java.util.Map;

import javax.security.auth.Subject;

import javax.security.auth.message.AuthException;
import javax.security.auth.message.AuthStatus;
import javax.security.auth.message.MessageInfo;
import javax.security.auth.message.MessagePolicy;
import javax.security.auth.message.module.ServerAuthModule;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.undertow.security.api.SecurityContext;
import io.undertow.security.idm.Account;
import io.undertow.security.idm.PasswordCredential;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.handlers.ServletRequestContext;
import java.util.List;
import javax.security.auth.callback.CallbackHandler;
import org.wildfly.extension.undertow.security.jaspi.JASPICAuthenticationMechanism;

/**
 *
 * @author sidde
 */
public class JaspicIPAuthenticatorLogin implements ServerAuthModule {

    private static org.jboss.logging.Logger log = org.jboss.logging.Logger.getLogger(JaspicIPAuthenticatorLogin.class);
    private CallbackHandler handler;
    private Class<?>[] supportedMessageTypes = new Class[]{HttpServletRequest.class, HttpServletResponse.class};
    private final String securityDomain;

    public JaspicIPAuthenticatorLogin(String securityDomain) {
        this.securityDomain = securityDomain;
    }

    @Override
    public void initialize(MessagePolicy requestPolicy, MessagePolicy responsePolicy, CallbackHandler handler, Map options) throws AuthException {
        this.handler = handler;
    }

    @Override
    public Class[] getSupportedMessageTypes() {
        return supportedMessageTypes;
    }

    @Override
    public AuthStatus validateRequest(MessageInfo messageInfo, Subject clientSubject, Subject serviceSubject) throws AuthException {
        SecurityContext securityContext = (SecurityContext) messageInfo.getMap().get(JASPICAuthenticationMechanism.SECURITY_CONTEXT_ATTACHMENT_KEY);
        log.info(securityContext.getMechanismName());
        Account account = securityContext.getIdentityManager().verify("jboss",new PasswordCredential("RedHat1!".toCharArray()));
        log.info("Account: "+account);
        log.info("Authentication Status: "+securityContext.isAuthenticated());
        if (account != null) {
            return AuthStatus.SUCCESS;
            
        }else{
            return AuthStatus.SEND_FAILURE;
        }
    }

    @Override
    public AuthStatus secureResponse(MessageInfo messageInfo, Subject serviceSubject) throws AuthException {
        return AuthStatus.SEND_SUCCESS;
    }

    @Override
    public void cleanSubject(MessageInfo messageInfo, Subject subject) throws AuthException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
