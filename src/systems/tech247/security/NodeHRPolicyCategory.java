/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.securitypannels.PolicyCategoryEditorTopComponent;
import systems.tech247.securitypannels.PolicyEditorTopComponent;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.CapEditable;

/**
 *
 * @author WKigenyi
 */
public class NodeHRPolicyCategory extends BeanNode<PolicyCategory> {
    
    InstanceContent content;
    public NodeHRPolicyCategory(PolicyCategory bean) throws IntrospectionException{
        this(bean,new InstanceContent());
    }
    
    
    
    public NodeHRPolicyCategory(final PolicyCategory bean, InstanceContent ic) throws IntrospectionException{
        super(bean,Children.create(new ChildFactoryHRPolicy(bean), true),new AbstractLookup(ic));
        setIconBaseWithExtension("systems/tech247/util/icons/javax.png");
        setDisplayName(bean.getCategory().getCategoryName());
        content = ic;
        ic.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new PolicyCategoryEditorTopComponent(bean.getCategory());
                tc.open();
                tc.requestActive();
            }
        });
        ic.add(new CapCreatable() {
            @Override
            public void create() {
                TopComponent tc = new PolicyEditorTopComponent();
                tc.open();
                tc.requestActive();
            }
        });
    }
    
}
