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

import systems.tech247.hr.HrsHRModules;


/**
 *
 * @author WKigenyi
 */
public class ChildFactoryHRModule extends ChildFactory<HRModule> {
    
    int groupID;
    public ChildFactoryHRModule(int groupID){
        this.groupID = groupID;
    }
    
    @Override
    protected boolean createKeys(List<HRModule> toPopulate) {
        //Get all Modules and then combine with the groupID to get the rights
        List<HrsHRModules> list = DataAccess.getHRModules(0);
        for(HrsHRModules module : list){
            toPopulate.add(new HRModule(module, groupID));
            
        }
        return true;
    }
    
    @Override
    protected Node createNodeForKey(HRModule key) {
        
        Node node = null;
        try{
            node = new NodeHRModule(key);
        }catch(IntrospectionException ex){
            ex.printStackTrace();
        }
        return node;
    }
    
}
