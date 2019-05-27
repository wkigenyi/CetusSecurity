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
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.hr.HrsGroupPolicies;
import systems.tech247.hr.HrsGroups;
import systems.tech247.securitypannels.GroupEditorTopComponent;
import systems.tech247.securitypannels.UserEditorTopComponent;
import systems.tech247.util.CapCreatable;

/**
 *
 * @author WKigenyi
 */
public class FactorySecurityReports extends ChildFactory<SetupItem> {
    
    
    
    
    public FactorySecurityReports(){
        
    }


    
    
    
    
    @Override
    protected boolean createKeys(List<SetupItem> toPopulate) {
                toPopulate.add(new SetupItem("Audit Trail",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO: Prepare the Audit Trail Report
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
