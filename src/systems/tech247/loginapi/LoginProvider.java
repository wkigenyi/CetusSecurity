/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.loginapi;


import systems.tech247.security.LoginDetail;

/**
 *
 * @author Admin
 */
public interface LoginProvider {
    
    LoginDetail getLoginDetail();
    
   
    
}
