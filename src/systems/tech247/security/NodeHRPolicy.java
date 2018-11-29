/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.BooleanEditor;

import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.HrsGroups;
import systems.tech247.hr.HrsPolicies;
import systems.tech247.securitypannels.PolicyEditorTopComponent;
import systems.tech247.util.CapEditable;

/**
 *
 * @author WKigenyi
 */
public class NodeHRPolicy extends AbstractNode{
    InstanceContent content;
    
    public NodeHRPolicy(PolicyValue bean) throws IntrospectionException{
        this(bean,new InstanceContent());
    }
    
    public NodeHRPolicy(final PolicyValue bean,InstanceContent ic) throws IntrospectionException{
        super(Children.LEAF,new AbstractLookup(ic));
        content=ic;
        content.add(bean);
        content.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new PolicyEditorTopComponent(bean.getPolicy());
                tc.open();
                tc.requestActive();
                        
            }
        });
        setIconBaseWithExtension("systems/tech247/util/icons/policy.png");
        setDisplayName(bean.getPolicy().getDescription());
    }
    
    
//    @Override
//    public Action[] getActions(boolean context){
//        Action editPolicy = new AbstractAction("Edit"){
//            @Override
//            public void actionPerformed(ActionEvent e){
//                
//                
//                TopComponent tc = WindowManager.getDefault().findTopComponent("QuotationEditorTopComponent");
//                tc.open();
//                tc.requestActive();
//           }
//        };
//        return new Action[]{editPolicy};
//    }
    
    @Override
    protected Sheet createSheet(){
        Sheet s = super.createSheet();
        Sheet.Set basic = Sheet.createPropertiesSet();
        basic.setDisplayName("Basic Info");
        final PolicyValue bean = getLookup().lookup(PolicyValue.class);
        
            final PropertySupport.Reflection test;
            Property isAssigned;
            
        try {
            test = new PropertySupport.Reflection(bean, Boolean.class, "assigned");
            test.setPropertyEditorClass(BooleanEditor.class);
            basic.put(test);
            
            isAssigned = new PropertySupport(
                    "isAssigned", 
                    Boolean.class, 
                    "Right Assigned", 
                    "Is this right assigned to the selected Group?", true, true) {
                @Override
                public Boolean getValue() throws IllegalAccessException, InvocationTargetException {
                    return bean.getAssigned();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
                @Override
                public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                    if(bean.getGroupID()==0){
                    StatusDisplayer.getDefault().setStatusText("Select a group to assign this right to.");
                    }else{
                        
                        
                        int groupID = bean.getGroupID();
                        HrsGroups grp = DataAccess.getEntityManager().find(HrsGroups.class, groupID);
                        
                        HrsPolicies policyID = bean.getPolicy();
                        boolean assign = (Boolean)val;
                        //Save the Policy
                        
                        
                        DataAccess.savePolicy(policyID,grp, assign);
                        test.setValue(val);
                        
                    }
                }
            };
            basic.put(isAssigned);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
            
        
            
            
            
            
            
        
        
        s.put(basic);
        
        
        return s;
    }
    
    
    
}
