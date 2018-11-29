/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.HrsGroups;
import systems.tech247.securitypannels.GroupEditorTopComponent;
import systems.tech247.util.CapEditable;
import systems.tech247.util.NodeRefreshProvider;

/**
 *
 * @author Admin
 */
public class NodeUserGroup extends AbstractNode implements LookupListener{
        
        Lookup.Result<RefreshGroupsEvent> rslt = NodeRefreshProvider.getInstance().getLookup().lookupResult(RefreshGroupsEvent.class);
        private final InstanceContent instanceContent;
        HrsGroups group;
        
        public NodeUserGroup(HrsGroups emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeUserGroup (InstanceContent ic, final HrsGroups user){
            super(Children.LEAF, new AbstractLookup(ic));
            
            group = user;
            instanceContent = ic;
            
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {     
//                    NotifyDescriptor nd = new NotifyDescriptor(new GroupEditorTopComponent(user), "Edit Group", NotifyDescriptor.OK_CANCEL_OPTION,
//                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
//                    //nd.setNoDefaultClose(true);  
//                    DialogDisplayer.getDefault().notifyLater(nd);
                        TopComponent tc = new GroupEditorTopComponent(user);
                        tc.open();
                        tc.requestActive();
                }
            });
            
            
            instanceContent.add(user);
            setIconBaseWithExtension("systems/tech247/util/icons/users.png");
            setDisplayName(user.getGroupName());
            rslt.addLookupListener(this);
        }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<RefreshGroupsEvent> rslt = (Lookup.Result<RefreshGroupsEvent>)ev.getSource();
        for(RefreshGroupsEvent e: rslt.allInstances()){
            HrsGroups g = DataAccess.entityManager.find(HrsGroups.class, group.getGroupID());
            setDisplayName(g.getGroupName());
        }
    }
    
}
