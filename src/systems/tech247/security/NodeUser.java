/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.nodes.Sheet.Set;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;
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

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final HrsUsers user = getLookup().lookup(HrsUsers.class);
        
        Property name = new PropertySupport("name", String.class,"NAME", "NAME", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                Employees emp = DataAccess.getEmployeeByID(user.getEmployeeID());
                return emp.getSurName()+" "+emp.getOtherNames();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
                
        Property role = new PropertySupport("role", String.class,"ROLE", "ROLE", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                
                return user.getGroupID().getGroupName();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
                
        Property must = new PropertySupport("must", Boolean.class,"MUST", "MUST", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                
                return user.getMustChangePwd();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
                
                Property locked = new PropertySupport("locked", Boolean.class,"Locked", "locked", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                
                return user.getAccLocked();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
                        
                        
                Property lockoutDate = new PropertySupport("lockdate", String.class,"Date", "Date", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try{
                    return sdf.format(user.getLockoutDate());
                }catch(NullPointerException ex){
                    return "";
                }
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };      
        set.put(lockoutDate);
        set.put(locked);
        set.put(must);
        set.put(role);
        set.put(name);
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
        
        
    
}
