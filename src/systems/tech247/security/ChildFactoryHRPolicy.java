/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import systems.tech247.dbaccess.DataAccess;

import systems.tech247.hr.HrsPolicies;
import systems.tech247.util.NodeRefreshProvider;


/**
 *
 * @author WKigenyi
 */
public class ChildFactoryHRPolicy extends ChildFactory<PolicyValue> implements LookupListener{
    Lookup.Result<RefreshPolicyEvent> rslt = NodeRefreshProvider.getInstance().getLookup().lookupResult(RefreshPolicyEvent.class);
            
    
    private int policyCategoryID;
    private int groupID;
    public ChildFactoryHRPolicy(PolicyCategory bean){
        this.policyCategoryID = bean.getCategory().getPolicyCategoryID();
        this.groupID = bean.getGroupID();
        rslt.addLookupListener(this);
    }
   
    @Override
    protected boolean createKeys(List<PolicyValue> toPopulate) {
        
        List<HrsPolicies> list = DataAccess.getHRPolicyByCategoryID(policyCategoryID,groupID);
        for(HrsPolicies policy : list){
            
                    
            toPopulate.add(new PolicyValue(groupID, policy,DataAccess.validatePolicy(policy, groupID)));
        
            
            
        }
        return true;
    }
    
    @Override
    protected Node createNodeForKey(PolicyValue key) {
        
        Node node = null;
        try{
            node = new NodeHRPolicy(key);
        }catch(IntrospectionException ex){
            ex.printStackTrace();
        }
        return node;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<RefreshPolicyEvent> rslt = (Lookup.Result<RefreshPolicyEvent>)ev.getSource();
        for(RefreshPolicyEvent rpe:rslt.allInstances()){
            refresh(true);
        }
    }
    
    

    
    
}
