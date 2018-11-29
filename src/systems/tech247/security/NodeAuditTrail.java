/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.text.SimpleDateFormat;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.hr.HrsAuditTrail;

/**
 *
 * @author Admin
 */
public class NodeAuditTrail extends AbstractNode{
    private final InstanceContent instanceContent;
        
        public NodeAuditTrail(HrsAuditTrail emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeAuditTrail (InstanceContent ic, final HrsAuditTrail user){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            
            
            
            
            instanceContent.add(user);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(user.getUserName()+" -> "+user.getActionDesc());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
            setShortDescription(sdf.format(user.getActionDate()));
        }
    
}
