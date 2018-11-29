/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import systems.tech247.hr.HrsUsers;

/**
 *
 * @author Admin
 */
public class LoginDetail {
    
    private final HrsUsers user;
    private final String workstation;
    

    public LoginDetail(HrsUsers user, String workstation) {
        this.user = user;
        this.workstation = workstation;
     
    }

    /**
     * @return the user
     */
    public HrsUsers getUser() {
        return user;
    }

    /**
     * @return the workstation
     */
    public String getWorkstation() {
        return workstation;
    }


    
    
    
}
