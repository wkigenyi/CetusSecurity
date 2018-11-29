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
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.HrsAuditTrail;
import systems.tech247.api.ReloadableQueryCapability;



/**
 *
 * @author WKigenyi
 */
public class ChildFactoryAuditTrail extends ChildFactory<HrsAuditTrail> {
    
    QueryAuditTrail query;
    public ChildFactoryAuditTrail(String sql){
        query = new QueryAuditTrail();
        query.setSqlString(sql);
    }
    
    @Override
    protected boolean createKeys(List<HrsAuditTrail> toPopulate) {
        //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        
        for(HrsAuditTrail tier: query.getList()){
            
            toPopulate.add(tier);
        }
        return true;
        
        
    }
    
    @Override
    protected Node createNodeForKey(HrsAuditTrail key) {
        
        Node node = null;
        try{
            node = new NodeAuditTrail(key);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return node;
    }
    
}
