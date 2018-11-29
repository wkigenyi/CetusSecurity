/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;


import systems.tech247.hr.HrsHRModules;


/**
 *
 * @author WKigenyi
 */
public class HRModule {
    private HrsHRModules module;
    private int groupID;
    
    public HRModule(HrsHRModules module, int groupID){
        this.groupID = groupID;
        this.module = module;
    }

    /**
     * @return the module
     */
    public HrsHRModules getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(HrsHRModules module) {
        this.module = module;
    }

    /**
     * @return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
