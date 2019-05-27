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
import systems.tech247.hr.HrsUsers;
import systems.tech247.api.ReloadableQueryCapability;
import systems.tech247.securitypannels.UserEditorTopComponent;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;
import systems.tech247.util.NodeRefreshProvider;

/**
 *
 * @author WKigenyi
 */
public class ChildFactoryUsers extends ChildFactory<Object> implements LookupListener {
    
    QueryUsers query;
    Boolean add;
    
    Lookup.Result<RefreshUsersEvent> rslt = NodeRefreshProvider.getInstance().getLookup().lookupResult(RefreshUsersEvent.class);
    
    public ChildFactoryUsers(QueryUsers query,Boolean add){
        this.query = query;
        rslt.addLookupListener(this);
        this.add=add;
       
    }
    
    @Override
    protected boolean createKeys(List<Object> toPopulate) {
        
        
//        toPopulate.add(new AddTool(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                TopComponent tc = new UserEditorTopComponent();
//                tc.open();
//                tc.requestActive();
//            }
//        }));
        
        //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        
        
        
        
        
        for(HrsUsers trans: query.getList()){
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
            if(key instanceof HrsUsers){
            node = new NodeUser((HrsUsers)key);    
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
        Lookup.Result<RefreshUsersEvent> rslt = (Lookup.Result<RefreshUsersEvent>)ev.getSource();
        for(RefreshUsersEvent r: rslt.allInstances()){
            refresh(true);
        }
    }
}
