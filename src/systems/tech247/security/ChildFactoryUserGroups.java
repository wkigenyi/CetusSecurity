/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import systems.tech247.hr.HrsGroups;
import systems.tech247.api.ReloadableQueryCapability;
import systems.tech247.securitypannels.GroupEditorTopComponent;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;
import systems.tech247.util.NodeRefreshProvider;

/**
 *
 * @author WKigenyi
 */
public class ChildFactoryUserGroups extends ChildFactory<Object> implements LookupListener{
    
    QueryUserGroups query;
    Boolean add;
    Lookup.Result<RefreshGroupsEvent> rslt = NodeRefreshProvider.getInstance().getLookup().lookupResult(RefreshGroupsEvent.class);
    public ChildFactoryUserGroups(QueryUserGroups query,Boolean add){
        this.query = query;
        this.add = add;
        rslt.addLookupListener(this);
    }
    
    @Override
    protected boolean createKeys(List<Object> toPopulate) {
        
        if(add){
            toPopulate.add(new AddTool(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new GroupEditorTopComponent();
                    tc.open();
                    tc.requestActive();
                }
            }));
        }
        
        //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        
        
        
        
        
        for(HrsGroups trans: query.getList()){
            //if(trans.getPayrollCodeID().getShowBalanceInPayslip()){
                toPopulate.add(trans);
            //}
         
         
        }
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key) {
        
        Node node =  null;
        try {
            if(key instanceof HrsGroups){
                node = new NodeUserGroup((HrsGroups)key);     
            }else if(key instanceof AddTool){
                node = new NodeAddTool((AddTool)key);
            }
            
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<RefreshGroupsEvent> rslt = (Lookup.Result<RefreshGroupsEvent>)ev.getSource();
        for(RefreshGroupsEvent r: rslt.allInstances()){
            refresh(true);
        }
    }
}
