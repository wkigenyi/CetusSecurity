/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.securitypannels;

import java.io.IOException;
import java.util.Arrays;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.HrsPolicies;
import systems.tech247.hr.HrsPolicyCategories;
import systems.tech247.menus.AppModulesTopComponent;
import systems.tech247.security.PolicyCategory;
import systems.tech247.security.RefreshPolicyEvent;
import systems.tech247.util.NodeRefreshProvider;


@ConvertAsProperties(
        dtd = "-//systems.tech247.security//PolicyEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "PolicyEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false,roles={"Security"})
@ActionID(category = "Window", id = "systems.tech247.security.GroupEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_PolicyEditorAction",
        preferredID = "PolicyEditorTopComponent"
)
@NbBundle.Messages({
    "CTL_PolicyEditorAction=Policy Editor",
    "CTL_PolicyEditorTopComponent=Policy Editor",
    "HINT_PolicyEditorTopComponent="
})





public final class PolicyEditorTopComponent extends TopComponent implements LookupListener{
    TopComponent tc= new AppModulesTopComponent(Boolean.FALSE);
    Lookup.Result<PolicyCategory> rslt;
    private final InstanceContent content = new InstanceContent();
    private HrsPolicies policy;
    String groupName="";
    String description="";
    HrsPolicyCategories category;
    HrsPolicies updateable;
    
    
    public PolicyEditorTopComponent(){
        this(null);
    }
    
    public PolicyEditorTopComponent(HrsPolicies policy) {
        initComponents();
        setName(Bundle.CTL_PolicyEditorTopComponent());
        setToolTipText(Bundle.CTL_PolicyEditorTopComponent());
        
        rslt = tc.getLookup().lookupResult(PolicyCategory.class);
        
        this.policy = policy;
        
        if(null!=policy){
            updateable = DataAccess.entityManager.find(HrsPolicies.class, policy.getPolicyID());
        }
        
        fillFields();
        
        jtPolicyName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                groupName = jtPolicyName.getText();
                try{
                    updateable.setPolicyName(groupName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                groupName = jtPolicyName.getText();
                try{
                    updateable.setPolicyName(groupName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                groupName = jtPolicyName.getText();
                try{
                    updateable.setPolicyName(groupName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jtaDescription.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                description = jtaDescription.getText();
                
                try{
                    updateable.setDescription(description);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                description = jtaDescription.getText();
                try{
                    updateable.setDescription(description);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                description = jtaDescription.getText();
                try{
                    updateable.setDescription(description);
                }catch(NullPointerException ex){
                    
                }
                modify();
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

        jLabel6 = new javax.swing.JLabel();
        jtPolicyName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtPolicyCategory = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PolicyEditorTopComponent.class, "PolicyEditorTopComponent.jLabel6.text")); // NOI18N

        jtPolicyName.setText(org.openide.util.NbBundle.getMessage(PolicyEditorTopComponent.class, "PolicyEditorTopComponent.jtPolicyName.text")); // NOI18N

        jtaDescription.setColumns(20);
        jtaDescription.setRows(5);
        jScrollPane1.setViewportView(jtaDescription);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PolicyEditorTopComponent.class, "PolicyEditorTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(PolicyEditorTopComponent.class, "PolicyEditorTopComponent.jLabel7.text")); // NOI18N

        jtPolicyCategory.setText(org.openide.util.NbBundle.getMessage(PolicyEditorTopComponent.class, "PolicyEditorTopComponent.jtPolicyCategory.text")); // NOI18N
        jtPolicyCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtPolicyCategoryKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtPolicyCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                .addComponent(jtPolicyName)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel2))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtPolicyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtPolicyCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtPolicyCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPolicyCategoryKeyPressed
        DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Select A Policy Category"));
    }//GEN-LAST:event_jtPolicyCategoryKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtPolicyCategory;
    private javax.swing.JTextField jtPolicyName;
    private javax.swing.JTextArea jtaDescription;
    // End of variables declaration//GEN-END:variables



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
    protected void componentClosed() {
        super.componentClosed(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void componentOpened() {
        rslt.addLookupListener(this);
    }
    
    void fillFields(){
        
        if(null !=  policy){
            jtPolicyName.setText(policy.getPolicyName());
            jtaDescription.setText(policy.getDescription());
            HrsPolicyCategories hpc = DataAccess.entityManager.find(HrsPolicyCategories.class, policy.getPolicyCategoryID());
            jtPolicyCategory.setText(hpc.getModuleID()+" "+hpc.getCategoryName());
                
        }
        
        
        
    }
    
    private class GroupSavable extends AbstractSavable{
        
        GroupSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            return "Policy";
        }
        
        PolicyEditorTopComponent tc(){
            return PolicyEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().content.remove(this);
            unregister();
            //New Employee
            if(null==policy){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[hrsPolicies]\n" +
"           ([PolicyName]\n" +
"           ,[Description]\n" +
"           ,[PolicyCategoryID])\n" +
"     VALUES\n" +
"           (?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1,groupName);
            query.setParameter(2,description);
            query.setParameter(3,category.getPolicyCategoryID());
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Updating
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            NodeRefreshProvider.content.set(Arrays.asList(new RefreshPolicyEvent()), null);
            
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof GroupSavable){
                GroupSavable e = (GroupSavable)o;
                return tc() == e.tc();
            }
            return false;        
        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
        public void modify(){
        
        
            if(updateable==null){
                if(groupName.length()<6 ){
                    StatusDisplayer.getDefault().setStatusText("Policy Name must be atleast 6 characters");
                }else if(null==category){
                    StatusDisplayer.getDefault().setStatusText("Select A Category");
                }else if(description.length()<6){
                    StatusDisplayer.getDefault().setStatusText("Proper Description is required");
                }else{            
                    if(getLookup().lookup(GroupSavable.class)==null){
                           content.add(new GroupSavable());
                    }
                }
                
            }else{
                if(updateable.getPolicyName().length()>=6){
                    if(getLookup().lookup(GroupSavable.class)==null){
                           content.add(new GroupSavable());
                    }
                }else{
                    StatusDisplayer.getDefault().setStatusText("Policy Name must be atleast 6 characters");
                }
            }
            
        
        
            
        
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<PolicyCategory> rslt =(Lookup.Result<PolicyCategory>)ev.getSource();
        for(PolicyCategory cat: rslt.allInstances()){
            category = cat.getCategory();
            jtPolicyCategory.setText(category.getModuleID()+" "+category.getCategoryName());
            try{
                updateable.setPolicyCategoryID(category.getPolicyCategoryID());
            }catch(NullPointerException ex){
                
            }
            modify();
        }
    }
    
    
    

    

    
    
    
    
    

    
        
        
        
}
