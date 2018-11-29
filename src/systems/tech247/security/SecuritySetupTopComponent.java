/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.BorderLayout;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import systems.tech247.hr.HrsGroups;
import systems.tech247.hr.HrsUsers;
import systems.tech247.util.CetusUTL;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.security//SecuritySetup//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "SecuritySetupTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = true,roles={"Security"})
@ActionID(category = "Window", id = "systems.tech247.security.SecuritySetupTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_SecuritySetupAction",
        preferredID = "SecuritySetupTopComponent"
)
@Messages({
    "CTL_SecuritySetupAction=Security Setup",
    "CTL_SecuritySetupTopComponent=Security Setup",
    "HINT_SecuritySetupTopComponent="
})
public final class SecuritySetupTopComponent extends TopComponent implements ExplorerManager.Provider{
    
    
    HrsGroups group;
    ExplorerManager em = new ExplorerManager();
    public SecuritySetupTopComponent() {
        initComponents();
        setName(Bundle.CTL_SecuritySetupTopComponent());
        setToolTipText(Bundle.HINT_SecuritySetupTopComponent());
        BeanTreeView btv = new BeanTreeView();
        btv.setRootVisible(false);
        setLayout(new BorderLayout());
        add(btv);
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
        
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        
        loadSetup();
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
    
    void loadSetup(){
        em.setRootContext(new AbstractNode(Children.create(new FactorySecuritySetup(), true)));
    }


    
    
}