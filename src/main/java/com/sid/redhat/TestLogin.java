/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sid.redhat;
import org.jboss.security.auth.spi.SimpleServerLoginModule;

/**
 *
 * @author sidde
 */
public class TestLogin extends SimpleServerLoginModule{
    private boolean state;
    
    @Override
    protected boolean validatePassword(String inputPassword, String expectedPassword){
        state = false;
        if(inputPassword.equals(expectedPassword)){
            state = true;
        }
        return state;
    }
    
}
