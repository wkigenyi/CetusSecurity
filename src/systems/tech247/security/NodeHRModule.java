/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author WKigenyi
 */
public class NodeHRModule extends BeanNode<HRModule> {
    
    HRModule bean;
    
    public NodeHRModule(HRModule bean) throws IntrospectionException{
        super(bean,Children.create(new ChildFactoryHRPolicyCategory(bean), true),Lookups.singleton(bean));
        setIconBaseWithExtension("systems/tech247/util/icons/javabean.png");
        setDisplayName(bean.getModule().getModuleCode()+"--"+bean.getModule().getModuleName());
        this.bean=bean;
    }

    @Override
    public Action getPreferredAction() {
        //return super.getPreferredAction(); //To change body of generated methods, choose Tools | Templates.
        final Node node = this;
        Action action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
            
        };
        return action;
    }
    
    
}
