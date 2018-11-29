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

import systems.tech247.hr.HrsPolicyCategories;
import systems.tech247.util.NodeRefreshProvider;

/**
 *
 * @author WKigenyi
 */
public class ChildFactoryHRPolicyCategory extends ChildFactory<PolicyCategory> implements LookupListener {
    
    Lookup.Result<RefreshPolicyCategoryEvent> rslt = NodeRefreshProvider.getInstance().getLookup().lookupResult(RefreshPolicyCategoryEvent.class);
    private final int moduleID;
    private final int groupID;
    public ChildFactoryHRPolicyCategory(HRModule bean){
        this.moduleID = bean.getModule().getModuleID();
        this.groupID = bean.getGroupID();
        rslt.addLookupListener(this);
    }
    
    @Override
    protected boolean createKeys(List<PolicyCategory> toPopulate) {
        List<HrsPolicyCategories> list = DataAccess.getHRPolicyCategoriesByModuleID(moduleID,groupID);
        for(HrsPolicyCategories category : list){
            toPopulate.add(new PolicyCategory(category, groupID));
            
        }
        return true;
    }
    
    @Override
    protected Node createNodeForKey(PolicyCategory key) {
        
        Node node = null;
        try{
            node = new NodeHRPolicyCategory(key);
        }catch(IntrospectionException ex){
            ex.printStackTrace();
        }
        return node;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<RefreshPolicyCategoryEvent> rslt = (Lookup.Result<RefreshPolicyCategoryEvent>)ev.getSource();
        for(RefreshPolicyCategoryEvent rpce:rslt.allInstances()){
            refresh(true);
        }
    }
    
    
    
}
