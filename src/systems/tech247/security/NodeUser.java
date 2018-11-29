/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.HrsUsers;
import systems.tech247.securitypannels.UserEditorTopComponent;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Admin
 */
public class NodeUser extends AbstractNode{
    private final InstanceContent instanceContent;
        
        public NodeUser(HrsUsers emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeUser (InstanceContent ic, final HrsUsers user){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {     
                    TopComponent tc = new UserEditorTopComponent(user);
                    tc.open();
                    tc.requestActive();
                }
            });
            
            
            instanceContent.add(user);
            setIconBaseWithExtension("systems/tech247/util/icons/person.png");
            setDisplayName(user.getUserName());
        }
    
}
