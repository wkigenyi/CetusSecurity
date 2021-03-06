/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.menus;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;
import systems.tech247.hr.HrsGroups;
import systems.tech247.security.ChildFactoryHRModule;



/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.viewer//AppModules//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "AppModulesTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.viewer.AppModulesTopComponent")
@Messages({
    "CTL_AppModulesAction=Application Rights/Authorizations",
    "CTL_AppModulesTopComponent=Application Rights",
    "HINT_AppModulesTopComponent="
})
public final class AppModulesTopComponent extends TopComponent
                                            implements ExplorerManager.Provider,
                                                        LookupListener
                                                        {
    TopComponent rolesTc = WindowManager.getDefault().findTopComponent("RolesTopComponent");
    ExplorerManager em = new ExplorerManager();
    private int groupID;
    Lookup.Result<HrsGroups> result=rolesTc.getLookup().lookupResult(HrsGroups.class);
    Boolean select;
    
    public AppModulesTopComponent(){
        this(true);
    }
    
    
    
    public AppModulesTopComponent(Boolean select) {
        initComponents();
        setName(Bundle.CTL_AppModulesTopComponent());
        setToolTipText(Bundle.HINT_AppModulesTopComponent());
        this.select = select;
        //If we are not selecting
        //1. Disable the group selector
        jtGroupSelect.setEnabled(select);
        jtGroupSelect.setVisible(select);
        jlSelectGroupLabel.setVisible(select);
        
        jpView.setLayout(new BorderLayout());
        
        
        
        OutlineView ov = new OutlineView("Module | Right Category | Right");
        if(select){
            ov.addPropertyColumn("isAssigned","Right Assigned ?");
        }
        
        ov.getOutline().setRootVisible(false);
        
        jpView.add(ov,BorderLayout.CENTER);
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
        em.setRootContext(new AbstractNode(Children.create(new ChildFactoryHRModule(groupID), true)));
        
        jtGroupSelect.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                DialogDisplayer.getDefault().notify(new DialogDescriptor(rolesTc, "Select A Group"));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtGroupSelect.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DialogDisplayer.getDefault().notify(new DialogDescriptor(rolesTc, "Select A Group"));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpView = new javax.swing.JPanel();
        jlSelectGroupLabel = new javax.swing.JLabel();
        jtGroupSelect = new javax.swing.JTextField();

        jpView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jlSelectGroupLabel, org.openide.util.NbBundle.getMessage(AppModulesTopComponent.class, "AppModulesTopComponent.jlSelectGroupLabel.text")); // NOI18N

        jtGroupSelect.setText(org.openide.util.NbBundle.getMessage(AppModulesTopComponent.class, "AppModulesTopComponent.jtGroupSelect.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlSelectGroupLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtGroupSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSelectGroupLabel)
                    .addComponent(jtGroupSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlSelectGroupLabel;
    private javax.swing.JPanel jpView;
    private javax.swing.JTextField jtGroupSelect;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening

        
        
        resultChanged(new LookupEvent(result));
        result.addLookupListener(this);
        
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
    public ExplorerManager getExplorerManager(){
        return em;
    }
    
    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<HrsGroups> r = (Lookup.Result<HrsGroups>)ev.getSource();
        
        
            for(HrsGroups role : r.allInstances()){
                jtGroupSelect.setText(role.getGroupName());
                groupID = role.getGroupID();
                load(groupID);
            }
        
    }
    
    void load(int groupID){
        em.setRootContext(new AbstractNode(Children.create(new ChildFactoryHRModule(groupID), true)));
    }
    
    
}
