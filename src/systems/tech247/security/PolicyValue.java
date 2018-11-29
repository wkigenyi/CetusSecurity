/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;


import systems.tech247.hr.HrsPolicies;


/**
 *
 * @author WKigenyi
 */
public class PolicyValue{
    
    private int groupID;
    private HrsPolicies policy;
    private Boolean assigned;
    
    
    public PolicyValue(int groupID,HrsPolicies policy,Boolean assigned){
        this.groupID = groupID;
        this.policy = policy;
        this.assigned = assigned;
        
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

    /**
     * @return the policy
     */
    public HrsPolicies getPolicy() {
        return policy;
    }

    /**
     * @param policy the policy to set
     */
    public void setPolicy(HrsPolicies policy) {
        this.policy = policy;
    }

    /**
     * @return the assigned
     */
    public Boolean getAssigned() {
        return assigned;
    }

    /**
     * @param assigned the assigned to set
     */
    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    /**
     * @return the activated
     */
    

        
}
