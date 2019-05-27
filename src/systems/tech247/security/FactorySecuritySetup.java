/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import systems.tech247.util.SetupItem;
import systems.tech247.util.NodeSetupItem;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.hr.HrsGroupPolicies;
import systems.tech247.hr.HrsGroups;

/**
 *
 * @author WKigenyi
 */
public class FactorySecuritySetup extends ChildFactory<SetupItem> {
    
    
    
    List policies = new ArrayList<>();
    public FactorySecuritySetup(){
        
        
        HrsGroups group = UtilitySecurity.getInstance().getLoginDetail().getUser().getGroupID();
        
        
        for(HrsGroupPolicies p:group.getHrsGroupPoliciesCollection()){
            if(p.getPolModify()){
                policies.add(p.getPolicyID().getPolicyID());
            }
        }
    }


    
    
    
    
    @Override
    protected boolean createKeys(List<SetupItem> toPopulate) {
                toPopulate.add(new SetupItem("Company Information",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("SecurityOptionsTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
        
        
        
                
        
        if(policies.contains(4)) //Viewing Users
            toPopulate.add(new SetupItem("Users",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("UsersTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
        
        if(policies.contains(3)) //Viewing Groups
            toPopulate.add(new SetupItem("User Groups",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("RolesTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));


       
        toPopulate.add(new SetupItem("Group Rights Assignment",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("AppModulesTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
        toPopulate.add(new SetupItem("Password Settings",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("SecurityOptionsTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
                
                
        
        return true;
    }
    
    @Override
    protected Node createNodeForKey(SetupItem key) {
        
        Node node =  null;
        try {
            
            node = new NodeSetupItem(key);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
