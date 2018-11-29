/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import systems.tech247.util.SetupItem;
import systems.tech247.util.NodeSetupItem;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.hr.HrsGroupPolicies;
import systems.tech247.hr.HrsGroups;
import systems.tech247.securitypannels.GroupEditorTopComponent;
import systems.tech247.securitypannels.UserEditorTopComponent;
import systems.tech247.util.CapCreatable;

/**
 *
 * @author WKigenyi
 */
public class FactorySecuritySetup extends ChildFactory<SetupItem> {
    
    
    
    List policies = new ArrayList<>();
    public FactorySecuritySetup(){
        
        
        HrsGroups group = UtilitySecurity.getInstance().getLoginDetail().getUser().getGroupID();
        
        
        for(HrsGroupPolicies p:group.getHrsGroupPoliciesCollection()){
            if(p.getPolModify()){
                policies.add(p.getPolicyID().getPolicyID());
            }
        }
    }


    
    
    
    
    @Override
    protected boolean createKeys(List<SetupItem> toPopulate) {
                toPopulate.add(new SetupItem("Company Information",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("SecurityOptionsTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
        
        
        
                InstanceContent icp = new InstanceContent();
                icp.add(new CapCreatable() {
                    @Override
                    public void create() {
                        TopComponent tc = new UserEditorTopComponent();
                        tc.open();
                        tc.requestActive();
                    }
                });
        QueryUsers query= new QueryUsers();
        query.setSqlString("SELECT h FROM HrsUsers h");
        if(policies.contains(4)) //Viewing Users
            toPopulate.add(new SetupItem("Users",Children.create(new ChildFactoryUsers(query,true), true),icp));
        InstanceContent icpG = new InstanceContent();
                icp.add(new CapCreatable() {
                    @Override
                    public void create() {
                        TopComponent tc = new GroupEditorTopComponent();
                        tc.open();
                        tc.requestActive();
                    }
                });
        QueryUserGroups queryg= new QueryUserGroups();
        queryg.setSqlString("SELECT h FROM HrsGroups h");
        if(policies.contains(3))
            toPopulate.add(new SetupItem("User Groups",Children.create(new ChildFactoryUserGroups(queryg,true), true) ,icpG));
        toPopulate.add(new SetupItem("Group Rights Assignment",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("AppModulesTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
        toPopulate.add(new SetupItem("Password Settings",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("SecurityOptionsTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
                toPopulate.add(new SetupItem("Database Server Settings",new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TopComponent tc = WindowManager.getDefault().findTopComponent("SecurityOptionsTopComponent");
                        tc.open();
                        tc.requestActive();
                    }
                }));
                toPopulate.add(new SetupItem("Reports", Children.create(new FactorySecurityReports(), true)));
        //toPopulate.add(new SetupItem("Payrolls",Children.create(new FactoryPayroll(Boolean.TRUE), true)));
        //toPopulate.add(new SetupItem("Statutory Rates Setup",Children.create(new FactoryStatutoryRatesSetup(), true)));
        //toPopulate.add(new SetupItem("Match Departments TO SUN"));
        /*toPopulate.add(new SetupItem("Salary Calculator",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent salaryTc = WindowManager.getDefault().findTopComponent("SalaryCalculatorTopComponent");
                salaryTc.open();
            }
        }));*/
        
        
        return true;
    }
    
    @Override
    protected Node createNodeForKey(SetupItem key) {
        
        Node node =  null;
        try {
            
            node = new NodeSetupItem(key);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
