/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;


import systems.tech247.hr.HrsPolicyCategories;


/**
 *
 * @author WKigenyi
 */
public class PolicyCategory {
    private HrsPolicyCategories category;
    private int groupID;
    
    public PolicyCategory(HrsPolicyCategories category, int groupID){
        this.groupID = groupID;
        this.category = category;
    }

    /**
     * @return the module
     */
    

    /**
     * @param module the module to set
     */
    

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

    /**
     * @return the category
     */
    public HrsPolicyCategories getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(HrsPolicyCategories category) {
        this.category = category;
    }
}
