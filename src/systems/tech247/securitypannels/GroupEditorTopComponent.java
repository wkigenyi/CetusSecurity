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
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.HrsGroups;
import systems.tech247.security.RefreshGroupsEvent;
import systems.tech247.util.NodeRefreshProvider;


@ConvertAsProperties(
        dtd = "-//systems.tech247.securitypannels//GroupEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "GroupEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false,roles={"Security"})
@ActionID(category = "Window", id = "systems.tech247.securitypannels.GroupEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_GroupEditorAction",
//        preferredID = "GroupEditorTopComponent"
//)
@NbBundle.Messages({
    "CTL_GroupEditorAction=Group Editor",
    "CTL_GroupEditorTopComponent=Group Editor",
    "HINT_GroupEditorTopComponent="
})





public final class GroupEditorTopComponent extends TopComponent{

    private final InstanceContent content = new InstanceContent();
    private HrsGroups user;
    String groupName="";
    String description="";
    Boolean selfaccess=false;
    HrsGroups updateable;
    
    
    public GroupEditorTopComponent(){
        this(null);
    }
    
    public GroupEditorTopComponent(HrsGroups user) {
        initComponents();
        setName(Bundle.CTL_GroupEditorTopComponent());
        setToolTipText(Bundle.CTL_GroupEditorTopComponent());
        
        this.user = user;
        
        if(null!=user){
            updateable = DataAccess.entityManager.find(HrsGroups.class, user.getGroupID());
        }
        
        fillFields();
        
        jtGroupName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                groupName = jtGroupName.getText();
                try{
                    updateable.setGroupName(groupName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                groupName = jtGroupName.getText();
                try{
                    updateable.setGroupName(groupName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                groupName = jtGroupName.getText();
                try{
                    updateable.setGroupName(groupName);
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
        jtGroupName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jcbSelfAccess = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(GroupEditorTopComponent.class, "GroupEditorTopComponent.jLabel6.text")); // NOI18N

        jtGroupName.setText(org.openide.util.NbBundle.getMessage(GroupEditorTopComponent.class, "GroupEditorTopComponent.jtGroupName.text")); // NOI18N

        jtaDescription.setColumns(20);
        jtaDescription.setRows(5);
        jScrollPane1.setViewportView(jtaDescription);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(GroupEditorTopComponent.class, "GroupEditorTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbSelfAccess, org.openide.util.NbBundle.getMessage(GroupEditorTopComponent.class, "GroupEditorTopComponent.jcbSelfAccess.text")); // NOI18N
        jcbSelfAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSelfAccessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbSelfAccess)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(37, 37, 37)
                            .addComponent(jtGroupName))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbSelfAccess)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbSelfAccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSelfAccessActionPerformed
        selfaccess= jcbSelfAccess.isSelected();
        try{
            updateable.setIsSelfAccessGroup(true);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbSelfAccessActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbSelfAccess;
    private javax.swing.JTextField jtGroupName;
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
        super.componentOpened(); //To change body of generated methods, choose Tools | Templates.
    }
    
    void fillFields(){
        
        if(null !=  user){
            jtGroupName.setText(user.getGroupName());
            jtaDescription.setText(user.getDescription());
            jcbSelfAccess.setSelected(user.getIsSelfAccessGroup());
//            jcbPasswordChangeOnNextLogin.setSelected(user.getMustChangePwd());
//            jcbPasswordNeverExpires.setSelected(user.getPwdNeverExp());
//            jcbUserLocked.setSelected(user.getAccLocked());
//            jcbAccountDisabled.setSelected(user.getAccDisabled());
        }
        
        
        
    }
    
    private class GroupSavable extends AbstractSavable{
        
        GroupSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            return "User Group";
        }
        
        GroupEditorTopComponent tc(){
            return GroupEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().content.remove(this);
            unregister();
            //New Employee
            if(null==user){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[hrsGroups]\n" +
"           ([GroupName]\n" +
"           ,[Description]\n" +
"           ,[IsSelfAccessGroup])\n" +
"     VALUES\n" +
"           (?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1,groupName);
            query.setParameter(2,description);
            query.setParameter(3,selfaccess);
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Updating
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            NodeRefreshProvider.content.set(Arrays.asList(new RefreshGroupsEvent()), null);
            
            
            
            
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
                    StatusDisplayer.getDefault().setStatusText("Group Name must be atleast 6 characters");
                }else{            
                    if(getLookup().lookup(GroupSavable.class)==null){
                           content.add(new GroupSavable());
                    }
                }
                
            }else{
                if(updateable.getGroupName().length()>=6){
                    if(getLookup().lookup(GroupSavable.class)==null){
                           content.add(new GroupSavable());
                    }
                }else{
                    StatusDisplayer.getDefault().setStatusText("Group Name must be atleast 6 characters");
                }
            }
            
        
        
            
        
    }
    
    

    

    
    
    
    
    

    
        
        
        
}
