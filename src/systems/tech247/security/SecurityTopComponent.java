/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.StatusDisplayer;
import org.openide.explorer.ExplorerUtils;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.ProxyLookup;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.menus.SecurityOptionsTopComponent;
import systems.tech247.securitypannels.HRSAuditTrailPanel;
import systems.tech247.securitypannels.HRSModulesPanel;
import systems.tech247.util.ChangeEditorPanel;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.security//Security//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "SecurityTopComponent",
        iconBase = "systems/tech247/util/icons/settings.png", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true, roles={"Security"})
//@ActionID(category = "System", id = "systems.tech247.security.SecurityTopComponent")
//@ActionReference(path = "Menu/System" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_SecurityAction",
        
        preferredID = "SecurityTopComponent"
)
@Messages({
    "CTL_SecurityAction=Security",
    "CTL_SecurityTopComponent=Security",
    "HINT_SecurityTopComponent= "
})
public final class SecurityTopComponent extends TopComponent implements TreeSelectionListener{

    
    Lookup lookup;
    
    List<Integer> list;
    
    JTree tree;
    DefaultMutableTreeNode selectedNode;
    public SecurityTopComponent() {
        initComponents();
        setName(Bundle.CTL_SecurityTopComponent());
        setToolTipText(Bundle.HINT_SecurityTopComponent());
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        lookup = new AbstractLookup(UtilitySecurity.editorSecurityIC);
        final DefaultMutableTreeNode top = new DefaultMutableTreeNode("Security Information");
        panelHolder.setLayout(new BorderLayout());
        tree = new JTree(top);
        createNodes(top);      
        tree.setModel(new DefaultTreeModel(top));
        treeHolder.setLayout(new BorderLayout());
        treeHolder.add(tree);
        expandAllNodes(tree, 0, tree.getRowCount());
        tree.addTreeSelectionListener(this);
        try{
            tree.setSelectionPath(new TreePath(selectedNode.getPath()));
        }catch(Exception ex){
            
        }
        
        
        
        
        
        
        
       
        
        
       
        
        
        //Associated lookups
       
        
        
        //Lookup users =  ExplorerUtils.createLookup(UtilitySecurity.emSCRUsers, getActionMap());
        
        Lookup userGroups =  ExplorerUtils.createLookup(UtilitySecurity.emSCRAuditTrail, getActionMap());
        //Lookup auditTrail =  ExplorerUtils.createLookup(UtilitySecurity.emSCRGroups, getActionMap());
        ProxyLookup mergedLookup = new ProxyLookup(lookup,userGroups);
        associateLookup(mergedLookup);
        
        

    }
    
    private void createNodes(DefaultMutableTreeNode root){
        
        
        
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode detail = null;
        
        
        
        
        
        
        
        
        
        //Employee Payroll Info
        category = new DefaultMutableTreeNode("User Information");
        root.add(category);
        
        if(list.contains(5)){
        detail = new DefaultMutableTreeNode(new ChangeEditorPanel("Rights Matrix", new HRSModulesPanel()));
        category.add(detail);
        }
        if(list.contains(7)){
        //detail = new DefaultMutableTreeNode(new ChangeEditorPanel("Security Options", new SecurityOptionsPanel()));
        category.add(detail);
        }
        
        
        
        
        
        
        
       
        
        // Reports
        category = new DefaultMutableTreeNode("System Reports");
        root.add(category);
        detail = new DefaultMutableTreeNode( new ChangeEditorPanel("Audit Trail", new HRSAuditTrailPanel()));
        category.add(detail);
        detail = new DefaultMutableTreeNode( new ChangeEditorPanel("Logged On Users", new JPanel()));
        category.add(detail);
        
        detail = new DefaultMutableTreeNode( new ChangeEditorPanel("Registered System Users", new JPanel()));
        category.add(detail);
        detail = new DefaultMutableTreeNode( new ChangeEditorPanel("Registered User Groups", new JPanel()));
        category.add(detail);
        
        
        detail = new DefaultMutableTreeNode( new ChangeEditorPanel("Assigned Group Rights", new JPanel()));
        category.add(detail);
        detail = new DefaultMutableTreeNode( new ChangeEditorPanel("Expired Accounts", new JPanel()));
        category.add(detail);
        detail = new DefaultMutableTreeNode( new ChangeEditorPanel("Locked Out Accounts", new JPanel()));
        category.add(detail);
        
        
        
        
        
        
        
                
        
        
        
        
        
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHolder = new javax.swing.JPanel();
        treeHolder = new javax.swing.JPanel();

        panelHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelHolderLayout = new javax.swing.GroupLayout(panelHolder);
        panelHolder.setLayout(panelHolderLayout);
        panelHolderLayout.setHorizontalGroup(
            panelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        panelHolderLayout.setVerticalGroup(
            panelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        treeHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout treeHolderLayout = new javax.swing.GroupLayout(treeHolder);
        treeHolder.setLayout(treeHolderLayout);
        treeHolderLayout.setHorizontalGroup(
            treeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );
        treeHolderLayout.setVerticalGroup(
            treeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(treeHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(treeHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelHolder;
    private javax.swing.JPanel treeHolder;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        
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
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if(node == null){
            //Do nothing 
        }
        
        Object nodeInfo = node.getUserObject();
        if(node.isLeaf()){
            ChangeEditorPanel panel = (ChangeEditorPanel)nodeInfo;
            panelHolder.removeAll();
            panelHolder.add(panel.getPanel());
            panelHolder.setPreferredSize(panel.getPanel().getPreferredSize());
            panelHolder.revalidate();
            
            repaint();
           
        }else{
            panelHolder.removeAll();
            panelHolder.revalidate();
            repaint();
            
        }
  
    }
    private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
    for(int i=startingIndex;i<rowCount;++i){
        tree.expandRow(i);
    }

    if(tree.getRowCount()!=rowCount){
        expandAllNodes(tree, rowCount, tree.getRowCount());
    }
}

    
}
